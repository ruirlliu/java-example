package com.example.framework.mvc.ws3;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liurui
 * @date 2020/12/8
 */
@ServerEndpoint("/webshell3/namespaces/{namespace}/pods/{pod}/containers/{container}/terminal")
@Component("WebShellDispatcher3")
@Slf4j
public class WebShellDispatcher {

    private final Map<Session, Session> cache = new ConcurrentHashMap<>();


    @OnOpen
    public void open(Session session,
                     @PathParam("namespace") String namespace,
                     @PathParam("pod") String pod,
                     @PathParam("container") String container) {
        Map<String, List<String>> parameterMap = session.getRequestParameterMap();
        String token = null;
        String scheme = null;
        String url = null;
        List<String> tokenValues = parameterMap.get("token");
        if (CollUtil.isNotEmpty(tokenValues)) {
            token = tokenValues.get(0);
        }
        List<String> schemeValues = parameterMap.get("scheme");
        if (CollUtil.isNotEmpty(tokenValues)) {
            scheme = schemeValues.get(0);
        }
        List<String> urlValues = parameterMap.get("url");
        if (CollUtil.isNotEmpty(urlValues)) {
            url = urlValues.get(0);
        }

        String wsScheme = "https".equals(scheme) ? "wss" : "ws";
        String add = wsScheme + "://" + url + "/ws/namespaces/" + namespace +
                "/pods/" + pod + "/containers/" + container + "/terminal/?token=" + token;
        WebSocketContainer socketContainer = ContainerProvider.getWebSocketContainer();
        try {
            Session session1 = socketContainer.connectToServer(Client.class, new URI(add));

            cache.put(session, session1);
        } catch (DeploymentException | URISyntaxException | IOException e) {
            log.error("Connection error occured!", e);
        }
    }


    @OnClose
    public void close(Session session) {
        cache.remove(session);
    }


    @OnMessage
    public void sendMessage(Session session, String message) {
        Session session1 = cache.get(session);
        try {
            session1.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void error(Session session, Throwable throwable) {
        cache.remove(session);
        throwable.printStackTrace();
    }

}
