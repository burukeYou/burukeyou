package burukeyou.im.server.entity.bo;

import io.netty.channel.Channel;

import java.util.HashMap;

public class UserChanelMap {
    // todo update to redis
    public static HashMap<String, Channel> user_channelMap = new HashMap<>();

    public static Channel getChanleById(String id){
        return user_channelMap.get(id);
    }


    public static Channel put(String key,Channel channel){
        return user_channelMap.put(key,channel);
    }

}
