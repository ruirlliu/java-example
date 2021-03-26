package com.example.netty.v3.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liurui
 * @date 2021/2/1
 */
public class SimpleChatServer {

    private final int port;

    public SimpleChatServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {

        // NioEventLoopGroup 是用来处理 I/O 操作的多线程事件循环器，Netty 提供了许多不同的 EventLoopGroup 的实现用来处理不同的传输
        // 这个例子中我们实现了一个服务端的应用，因此会有2个 NioEventLoopGroup 会被使用

        // 第一个经常被叫做‘boss’，用来接收进来的连接。第二个经常被叫做‘worker’，用来处理已经被接收的连接
        // 一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上

        // 如何知道多少个线程已经被使用，如何映射到已经创建的 Channel上都需要依赖于 EventLoopGroup 的实现，并且可以通过构造函数来配置他们的关系。
        EventLoopGroup boseGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // ServerBootstrap 是一个启动 NIO 服务的辅助启动类。
            // 你可以在这个服务中直接使用 Channel，但是这会是一个复杂的处理过程，在很多情况下你并不需要这样做。
            ServerBootstrap b = new ServerBootstrap();
            b.group(boseGroup, workerGroup)
                    // 指定使用 NioServerSocketChannel 类来举例说明一个新的 Channel 如何接收进来的连接。
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new SimpleChatServerInitializer())
                    // option() 是提供给NioServerSocketChannel 用来接收进来的连接。
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // childOption() 是提供给由父管道 ServerChannel 接收到的连接，在这个例子中也是 NioServerSocketChannel。
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            System.out.println("SimpleChatServer 启动了");

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();

            //等待服务器 socket 关闭
            // 这个例子中，这不会发生，但可以优雅的关闭服务器
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            boseGroup.shutdownGracefully();
            System.out.println("SimpleChatServer 关闭了");
        }
    }

    public static void main(String[] args) throws Exception {

        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new SimpleChatServer(port).run();

    }
}
