package burukeyou.common.redis.lock;

public interface ILock {

    /**
     * 获取锁
     * @param lock 锁名称
     */
    void lock(String lock);

    /**
     * 释放锁
     * @param lock 锁名称
     */
    void unlock(String lock);
}
