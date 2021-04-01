package example.framework.netty.v1.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * DISCARD协议,是一种丢弃了所有接受到的数据，并不做有任何的响应的协议。
 * @author lr
 * @date 2021/1/25
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


    // 每当从客户端收到新的数据时，这个方法会在收到消息时被调用
    // 打印消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(((ByteBuf)msg).toString(StandardCharsets.UTF_8));
        ctx.write(msg);
        ctx.flush();
//        ctx.writeAndFlush(msg);
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}
