package com.example.rpc;

import java.io.IOException;

/**
 * @author lr
 * @date 2020/12/7
 */
public interface Server {

    void start() throws IOException;

    void register(Class<?> serviceInterface, Class<?> impl);

    boolean isRunning();

    int getPort();

    void stop();
}
