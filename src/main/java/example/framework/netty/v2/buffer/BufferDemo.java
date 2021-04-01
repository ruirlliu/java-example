package example.framework.netty.v2.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;

/**
 * @author lr
 * @date 2021/1/28
 */
public class BufferDemo {

    public void alloc(ByteBuffer header, ByteBuffer body) {

        ByteBuf message = Unpooled.wrappedBuffer(header, body);

        ByteBuf footer = Unpooled.buffer(4);

        ByteBuf byteBuf = Unpooled.wrappedBuffer(message, footer);


        // 一种新的动态缓冲区被创建。在内部，实际缓冲区是被“懒”创建，从而避免潜在的浪费空间。
        ByteBuf buffer = Unpooled.buffer(4);

        // 当第一个执行写尝试，内部指定内部容量 4 的缓冲区被创建
        buffer.writeByte('1');

        buffer.writeByte('2');
        buffer.writeByte('3');
        buffer.writeByte('4');

        // 当写入的字节数超过初始容量 4 时
        // 内部缓冲区自动分配具有较大的容量
        buffer.writeByte('5');



    }


}
