package com.djh.DingChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 *
 */
@SpringBootApplication
public class ServerApplication
{
    public static void main( String[] args )
    {
        new SpringApplication(ServerApplication.class).run(args);
    }
}
