package com.mybatis;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.plugin.Plugin;

import java.lang.reflect.Method;

/**
 * @author lr
 * @date 2021/3/18
 */
public class PluginTest {


    public static void main(String[] args) throws NoSuchMethodException {
        SimpleExecutor executor = new SimpleExecutor(null, null);
        Object wrap = Plugin.wrap(executor, new PageInterceptor());
        System.out.println(wrap.getClass());

        Method test = PluginTest.class.getMethod("test", Integer.class, String.class);
        System.out.println(test);
        PluginTest.class.getMethod("test", String.class, Integer.class);
    }


    public void test() {

    }

    public void test(String str, Integer i) {

    }
}
