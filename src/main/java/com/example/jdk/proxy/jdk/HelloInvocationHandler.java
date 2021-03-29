package com.example.jdk.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用处理器实现类
 * 每次生成动态代理类对象时都需要指定一个实现了该接口的调用处理器对象
 * @author lr
 * @date 2020/11/3
 */
public class HelloInvocationHandler implements InvocationHandler {

    /**
     * 这个就是我们要代理的真实对象
     */
    private Object subject;

    public HelloInvocationHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 自定义操作；
        System.out.println("准备喊出：");
        Object result = method.invoke(subject, args);
        return result;
    }
}
