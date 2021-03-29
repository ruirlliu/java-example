package com.example.framework.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author lr
 * @date 2020/12/8
 */
@ServerEndpoint(value = "/test")
@Component
@Slf4j
public class TestWebSocket {

    @OnOpen
    public void open(Session session) {
        System.out.println("open...");
    }

    @OnMessage
    public void message(Session session, String msg) throws IOException {
        System.out.println("message...");
        System.out.println(msg);
        session.getBasicRemote().sendText("original111 copy that: " + msg);
    }

    @OnError
    public void error(Session session, Throwable throwable) {
        System.out.println("error...");
        throwable.printStackTrace();
    }
}
