package example.framework.mvc.controller;

import cn.hutool.core.collection.CollUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lr
 * @date 2020/12/8
 */
@ServerEndpoint("/webshell/namespaces/{namespace}/pods/{pod}/containers/{container}/terminal")
@Component
public class WebShellDispatcher {

    private final OkHttpClient okHttpClient = createOkHttpClient();

    private final Map<Session, WebSocket> cache = new ConcurrentHashMap<>();


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
        WebSocket webSocket = cache.get(session);
        if (webSocket == null) {
            String wsScheme = "https".equals(scheme) ? "wss" : "ws";
            String add = wsScheme + "://" + url + "/ws/namespaces/" + namespace +
                    "/pods/" + pod + "/containers/" + container + "/terminal/?token=" + token;
            Request request = new Request.Builder()
                    .addHeader("Origin", scheme + "://" + url)
                    .addHeader("Host", url)
                    .url(add)
                    .build();
            cache.put(session, okHttpClient.newWebSocket(request, new SocketListener(session)));
        }
    }


    @OnClose
    public void close(Session session) {
        cache.remove(session);
        ;
    }


    @OnMessage
    public void sendMessage(Session session, String message) {
        WebSocket webSocket = cache.get(session);
        if (webSocket != null) {
            webSocket.send(message);
        }
    }

    @OnError
    public void error(Session session, Throwable throwable) {
        WebSocket webSocket = cache.remove(session);
        if (webSocket != null) {
            webSocket.cancel();
        }
        throwable.printStackTrace();
    }

    static class SocketListener extends WebSocketListener {

        private final Session session;

        SocketListener(Session session) {
            super();
            this.session = session;
        }


        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            System.out.println("webSocket Open, response is: " + response);
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            System.out.println("receive webSocket message: " + text);
            try {
                session.getBasicRemote().sendText(text);
            } catch (IOException e) {
                System.out.println("receive socket message error: " + e.toString());
                e.printStackTrace();
            }
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            try {
                webSocket.close(code, reason);
                session.close();
            } catch (IOException e) {
                System.out.println("webSocket session close error: " + e.toString());
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable throwable, Response response) {
            super.onFailure(webSocket, throwable, response);
            System.out.println("webSocket failed: " + (response != null ? response.toString() : "null"));
            throwable.printStackTrace();
        }
    }


    public static OkHttpClient createOkHttpClient() {
        OkHttpClient okHttpClient = TestWsDispatcher.createOkHttpClient();
        return okHttpClient;

//        return new OkHttpClient().newBuilder()
//                .pingInterval(40, TimeUnit.SECONDS) // 设置 PING 帧发送间隔
//                .build();


    }


}
