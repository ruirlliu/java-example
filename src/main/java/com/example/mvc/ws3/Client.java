package com.example.mvc.ws3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import java.io.IOException;

/**
 * @author liurui
 * @date 2020/12/9
 */
@ClientEndpoint
public class Client {


    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    @OnOpen
    public void onOpen(Session session) {
        LOGGER.info("OnOpen invoked by Session '{}'.", session.getId());

        try {
            session.getBasicRemote().sendText("Hello Server!");
        } catch (IOException ex) {
            LOGGER.error("Message delivery failed.", ex);
        }
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        LOGGER.info("OnClose invoked by Session '{}'; Reason: {}.", session.getId(), closeReason.getReasonPhrase());
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        LOGGER.info("OnMessage invoked by Session '{}'; Message: {}.", session.getId(), msg);
    }

//    public static void main(String[] args) {
//
//
//        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
//        try {
//            container.connectToServer(Client.class, new URI("ws://localhost:8081/test"));
//
//        } catch (DeploymentException | URISyntaxException | IOException e) {
//            LOGGER.error("Connection error occured!", e);
//        }
//    }
}