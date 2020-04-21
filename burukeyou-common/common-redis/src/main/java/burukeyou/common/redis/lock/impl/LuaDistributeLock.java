package burukeyou.common.redis.lock.impl;

import burukeyou.common.redis.lock.ILock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LuaDistributeLock implements ILock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private String lockPrefix = "DIS_LOCK"; //

    private int lockMaxExistTimes = 20;

    private DefaultRedisScript<Long> lockScript; // 锁脚本

    private DefaultRedisScript<Long> unlockScript; // 解锁脚本

    // 持有锁的线程id，通过UUID生成全局唯一ID  锁的value存储此值
    private ThreadLocal<String> threadKeyId = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return UUID.randomUUID().toString();
        }
    };

    public LuaDistributeLock(String lockPrefix, int time){
        this.lockPrefix = lockPrefix;
        this.lockMaxExistTimes = time;

        // init
        init();
    }

    public void init() {
        // Lock script
        lockScript = new DefaultRedisScript<>();
        lockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lock.lua")));
        lockScript.setResultType(Long.class);

        // unlock script
        unlockScript = new DefaultRedisScript<>();
        unlockScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("unlock.lua")));
        unlockScript.setResultType(Long.class);
    }

    @Override
    public void lock(String lock){
        Assert.notNull(lock, "lock can't be null!");
        String lockKey = new StringBuilder().append(lockPrefix).append(lock).toString();
        while(true){
            List<String> keyList = new ArrayList<>();
            keyList.add(lockKey);
            keyList.add(threadKeyId.get());

            /**
             *  keyList 对应 在lock.lua 的KEYS[1]，KEYS[2]。。。来接收
             *  ttl 对应 在lock.lua 的 ARGV[1]，ARGV[2]，ARGV[3]。。。来接收
             */
            String ttlTIme = String.valueOf(lockMaxExistTimes * 1000);
            if(redisTemplate.execute(lockScript, keyList, ttlTIme) > 0){
                break;
            }else{
                try {
                    // 短暂休眠，nano避免出现活锁
                    Thread.sleep(10, (int)(Math.random() * 500));
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }


    /**
     * 释放锁，同时要考虑当前锁是否为自己所有，
     * 以下情况会导致当前线程失去锁：线程执行的时间超过超时的时间，导致此锁被其它线程拿走;
     * 此时用户不可以释放锁
     */
    @Override
    public void unlock(final String lock) {
        final String lockKey = new StringBuilder().append(lockPrefix).append(lock).toString();
        List<String> keyList = new ArrayList<String>();
        keyList.add(lockKey);
        keyList.add(threadKeyId.get());
        redisTemplate.execute(unlockScript, keyList);
    }
}

