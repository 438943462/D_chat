package com.djh.DingChat.server.server;

import com.djh.DingChat.server.server.WebsocketServer;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class ApplicationEventListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        WebsocketServer server = new WebsocketServer("/chat");
        server.start((short) 8081);
    }
}
