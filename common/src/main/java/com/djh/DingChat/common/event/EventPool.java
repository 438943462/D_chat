package com.djh.DingChat.common.event;

import java.util.concurrent.ConcurrentHashMap;

public class EventPool {

    private final static EventPool INSTANCE=new EventPool();

    private EventPool(){

    }
    public static EventPool getInstance(){
        return INSTANCE;
    }

    private ConcurrentHashMap<String,IEvent> events;

    public void registe(final String key, final IEvent handler){
        if(null == key || key.isEmpty()){
            System.out.println("key is empty");
            return;
        }
        if(null == handler ){
            System.out.println("handler is empty");
            return;
        }
        events.put(key,handler);
    }

    public IEvent find (final String key){
        if (null == key || key.isEmpty()){
            System.out.println("key is empty");
            return null;
        }
        return events.get(key);
    }
}
