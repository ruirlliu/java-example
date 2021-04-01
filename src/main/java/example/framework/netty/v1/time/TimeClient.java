package example.framework.netty.v1.time;

import example.framework.netty.v1.pojo.PojoTimeClientHandler;
import example.framework.netty.v1.pojo.PojoTimeDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author lr
 * @date 2021/1/26
 */
public class TimeClient {

    public static void main(String[] args) throws InterruptedException {

        String host = "localhost";
        int port = 8080;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new PojoTimeDecoder(), new PojoTimeClientHandler());
                        }
                    });

            // 启动客户端
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            // 等待链接关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }

}
