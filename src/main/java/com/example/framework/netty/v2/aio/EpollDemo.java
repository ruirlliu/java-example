package com.example.framework.netty.v2.aio;

import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.Native;

/**
 * @author liurui
 * @date 2021/1/29
 */
public class EpollDemo {

    public static void main(String[] args) {
        String kernelVersion = Native.KERNEL_VERSION;
        System.out.println(kernelVersion);
        EpollDemo epollDemo = new EpollDemo();
        epollDemo.test();
        Epoll.ensureAvailability();
    }

    public native int test() ;

}
