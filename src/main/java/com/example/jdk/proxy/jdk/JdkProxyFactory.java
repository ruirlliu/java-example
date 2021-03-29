package com.example.jdk.proxy.jdk;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lr
 * @date 2020/11/3
 */
public class JdkProxyFactory {

    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), // 目标类的类加载
                target.getClass().getInterfaces(),  // 代理需要实现的接口，可指定多个
                new HelloInvocationHandler(target)   // 代理对象对应的自定义 InvocationHandler
        );
    }

    @SuppressWarnings("unchecked")
    public static <T> T getProxyUsingInterface(Class<T> pInterface) {
        if (!pInterface.isInterface()) {
            throw new RuntimeException("不是接口");
        }
        return (T)  Proxy.newProxyInstance(
                pInterface.getClassLoader(), // 目标类的类加载
                new Class[]{pInterface},  // 代理需要实现的接口，可指定多个
                new MyInvocationHandler());

    }

    static class MyInvocationHandler implements InvocationHandler, Serializable {


        private static final long serialVersionUID = -5316064304309397135L;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return loadMethodHandle(method).invoke();
        }
    }

    private static MyMethodInvoker loadMethodHandle(Method method) {
        // load
        MyMethodInvoker myMethodInvoker = map.get(method);
        if (myMethodInvoker != null) {
            return myMethodInvoker;
        } else {
            return map.computeIfAbsent(method, m -> new MyMethodInvoker(m.getName()));
        }
    }


    private static final Map<Method, MyMethodInvoker> map = new HashMap<>();

    static class MyMethodInvoker {

        String str;

        public MyMethodInvoker(String str) {
            this.str = str;
        }

        public Object invoke() {
            System.out.println("invoke method: " + str);
            return str;
        }

    }

}
