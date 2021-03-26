package com.example.framework.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author liurui
 * @date 2020/12/9
 */
@ServerEndpoint(value = "/test/dp/{num}")
@Component
@Slf4j
public class TestWsDispatcher {


    private final OkHttpClient okHttpClient = createOkHttpClient();

    private final Map<Session, WebSocket> cache = new ConcurrentHashMap<>();


    @OnOpen
    public void open(Session session, @PathParam("num") Integer num) {
        WebSocket webSocket = cache.get(session);

        String add;
        if (num == 0) {
            add = "ws://localhost:8084/test";
        } else if (num == 1) {
            add = "ws://10.10.100.162:8080/test";
        } else if (num == 2) {
            add = "wss://www.qvdv.com/Websocket";
        }
        else  {
            add = "ws://123.207.136.134:9010/ajaxchattest";
        }
        if (webSocket == null) {
            // curl https://192.168.163.132:6443/api/v1/namespaces --cacert /etc/ssl/ca/ca.pem --key /etc/ssl/k8s/kubeadmin-key.pem --cert /etc/ssl/k8s/kubeadmin.pem
            Request request = new Request.Builder()
                    .url(add)
                    .build();
            cache.put(session, okHttpClient.newWebSocket(request, new SocketListener(session)));
        }
    }


    @OnClose
    public void close(Session session) {
        WebSocket webSocket = cache.remove(session);;
        if (webSocket != null) {
            webSocket.cancel();
        }
    }


    @OnMessage
    public void sendMessage(Session session, String message){
        WebSocket webSocket = cache.get(session);
        if (webSocket != null) {
            webSocket.send(message);
        }
        System.out.println("send: " + Thread.currentThread().getId());
    }


    @OnError
    public void error(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    static class SocketListener extends WebSocketListener {

        final Session session;
        SocketListener(Session session) {
            super();
            this.session = session;
        }


        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            log.info("onOpen response is: " + response);
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            log.info("onMessage text is: " + text);
            try {
                System.out.println("receive: " + Thread.currentThread().getId());
                this.session.getBasicRemote().sendText(text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
            log.info("onMessage bytes is: " + bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
            log.info("onClosing code is: " + code);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            webSocket.close(code, reason);
            log.info("onClosed code is: " + code);
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            log.info("onFailure t is: " + t.getMessage());
        }
    }



//    public static OkHttpClient createOkHttpClient() {
//
//        Config build = new ConfigBuilder().withCaCertFile("C:\\docker\\k8s\\102\\ca.pem")
//                .withClientKeyFile("C:\\docker\\k8s\\102\\admin-key.pem")
//                .withClientCertFile("C:\\docker\\k8s\\102\\admin.pem").build();
//
//
//        OkHttpClient httpClient = HttpClientUtils.createHttpClient(build);
//        return httpClient;
//
////        return new OkHttpClient().newBuilder().pingInterval(Duration.of(40, ChronoUnit.SECONDS)).build();
//
//
//    }



    public static OkHttpClient createOkHttpClient() {
        final X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            return new OkHttpClient().newBuilder().connectTimeout(6, TimeUnit.SECONDS)// 连接超时
                    .readTimeout(6, TimeUnit.SECONDS).writeTimeout(6, TimeUnit.SECONDS)
                    .followRedirects(true).followSslRedirects(true)
                    .retryOnConnectionFailure(true).sslSocketFactory(sslSocketFactory, trustManager)// 配置
                    .hostnameVerifier((hostname, session) -> true)
                    .build();
        } catch (Exception e) {
            log.error("创建OkHttpClient失败:{}", e);
            throw new RuntimeException(e);
        }
    }


}
