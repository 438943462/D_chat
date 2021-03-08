package com.djh.DingChat;

import com.djh.DingChat.client.WebsocketClient;
import com.djh.DingChat.client.event.LoginEvent;
import com.djh.DingChat.common.action.ActionIdEnum;
import com.djh.DingChat.common.event.EventPool;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner
{
    private WebsocketClient client;

    public static void main( String[] args )
    {
        new SpringApplication(ClientApplication.class).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.registeEvent();
        this.connect();
        //this.handleCommand();
    }

    private void handleCommand() {
    }

    private void connect() {
        URI uri = URI.create("ws://127.0.0.1:8081/chat");
        this.client = new WebsocketClient(uri);
        this.client.connect();
    }

    private void registeEvent() {
        EventPool.getInstance().registe(ActionIdEnum.ACTION_LOGIN_RESP.getAction(), new LoginEvent());
    }
}
