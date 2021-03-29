package com.example.framework.netty.v1.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * DISCARD协议,是一种丢弃了所有接受到的数据，并不做有任何的响应的协议。
 * @author lr
 * @date 2021/1/25
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    // 完全忽略
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        // 默默地丢弃收到的数据
//        ((ByteBuf) msg).release();
//    }

    // 打印消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = ((ByteBuf) msg);
        try {
           while (in.isReadable()) {
               System.out.print((char) in.readByte());
               System.out.flush();
           }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}
