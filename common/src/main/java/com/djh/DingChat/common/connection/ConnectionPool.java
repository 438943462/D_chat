package com.djh.DingChat.common.connection;



import io.netty.channel.Channel;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionPool {

    //单例模式 饿汉式 @Service注解不好拿到对象
    private static final ConnectionPool INSTANCE = new ConnectionPool();

    private ConnectionPool(){
        this.users = new ConcurrentHashMap<>();
        this.channels = new ConcurrentHashMap<>();

    }

    public static ConnectionPool getInstance(){
        return INSTANCE;
    }

    /**
     * userId => channelId,channelId...
     */
    private ConcurrentHashMap<Long, HashSet<String>> users;
    /**
     * channelId => Channel
     */
    private ConcurrentHashMap<String, Channel> channels;
    /**
     * channelId => userId
     */
    private ConcurrentHashMap<String,Long> userIds;

    public void add(final Long userId,final Channel channel ){
        if (null == userId){
            System.out.println("userId is empty");
            return;
        }
        if (null == channel){
            System.out.println("channel is empty");
            return;
        }

        HashSet<String> channelId = users.get(userId);
        if (CollectionUtils.isEmpty(channelId)){
            channelId = new HashSet<>();
        }
        channelId.add(channel.id().toString());
        users.put(userId,channelId);
        channels.put(channel.id().asLongText(),channel);
        userIds.put(channel.id().asLongText(),userId);

    }

    public void removeByChannelId(final String channelId){
        if (null == channelId || channelId.isEmpty()){
            System.out.println("channelId is empty");
            return;
        }
        channels.remove(channelId);
        Long userId = userIds.get(channelId);

        if(null != userId){
            users.remove(userId);
            userIds.remove(userId);
        }
    }

    public void removeByUserId(final Long userId){
        if (null == userId){
            System.out.println("userId is empty");
            return;
        }
        HashSet<String> channelIds = users.get(userId);
        if(!CollectionUtils.isEmpty(channelIds)){
            users.remove(userId);
            channelIds.stream().forEach(channelId ->{
                userIds.remove(channelId);
                channels.remove(channelId);
            });
        }

    }

}
