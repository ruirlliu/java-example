package example.framework.netty.v1.time.solution;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author lr
 * @date 2021/1/28
 */
public class TimeDecoder {

    public static class ByteToMessageDecoderDemo extends ByteToMessageDecoder {

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            if (in.readableBytes() < 4) {
                return;
            }
            out.add(in.readBytes(4));
        }
    }

    public static class ReplayingDecoderDemo extends ReplayingDecoder<Void> {

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            out.add(in.readBytes(4));
        }
    }

}
