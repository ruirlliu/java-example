package example.framework.mvc.controller;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.junit.Test;

/**
 * @author lr
 * @date 2020/12/9
 */
@Slf4j
public class TestWsDispatcherTest {

    @Test
    public void test() {
        OkHttpClient okHttpClient = TestWsDispatcher.createOkHttpClient();

        Request request = new Request.Builder()
                .url("wss://192.168.1.82:9019/ws/namespaces/test1126/pods/testlog-v1-7c65bf74f5-vdr6p/containers/testlog/terminal/?token=313233343536373831323334353637388569295818bcc7531fb9f5cf159e2d4d7379653dcab4512ef155c9956976e509ae7e6b5826a7b2545c331f")
                .build();
        WebSocket webSocket = okHttpClient.newWebSocket(request, new SocketListener());
        webSocket.send("{\"Op\":\"stdin\",\"Data\":\"ls\r\"}");
    }

    static class SocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            log.info("onOpen response is: " + response);
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            log.info("onMessage text is: " + text);
            System.out.println("receive: " + Thread.currentThread().getId());

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
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            log.info("onFailure t is: " + t.getMessage());
        }
    }

}