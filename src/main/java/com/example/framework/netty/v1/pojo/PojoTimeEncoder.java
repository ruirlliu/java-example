package com.example.framework.netty.v1.pojo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author lr
 * @date 2021/1/28
 */
public class PojoTimeEncoder extends ChannelOutboundHandlerAdapter {


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        UnixTime m = (UnixTime) msg;
        ByteBuf buffer = ctx.alloc().buffer(4);
        buffer.writeInt((int)m.getValue());
        ctx.write(buffer, promise);
    }


}
