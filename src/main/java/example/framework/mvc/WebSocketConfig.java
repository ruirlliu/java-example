package example.framework.mvc;

import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author lr
 * @date 2020/12/8
 */

//@Configuration
//@EnableWebSocket
public class WebSocketConfig  {
//    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}

