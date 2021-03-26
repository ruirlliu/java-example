package com.example.mvc.ws;

import cn.hutool.core.collection.CollUtil;
import org.java_websocket.client.WebSocketClient;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liurui
 * @date 2020/12/8
 */
@ServerEndpoint("/webshell1/namespaces/{namespace}/pods/{pod}/containers/{container}/terminal")
@Component("WebShellDispatcher1")
public class WebShellDispatcher {

    private final Map<Session, WebSocketClient> cache = new ConcurrentHashMap<>();


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

        MyWebSocketClient myWebSocketClient = new MyWebSocketClient(URI.create("ws://123.207.136.134:9010/ajaxchattest"));
        myWebSocketClient.connect();
        cache.put(session, myWebSocketClient);
    }


    @OnClose
    public void close(Session session) {
        cache.remove(session);
    }


    @OnMessage
    public void sendMessage(Session session, String message) {
        WebSocketClient webSocketClient = cache.get(session);
        webSocketClient.send(message);
    }

    @OnError
    public void error(Session session, Throwable throwable) {
        WebSocketClient webSocketClient = cache.remove(session);
        throwable.printStackTrace();
    }





}
