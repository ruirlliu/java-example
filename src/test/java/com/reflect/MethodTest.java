package com.reflect;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author lr
 * @date 2021/3/17
 */
public class MethodTest {

    interface Test01 {
        void a();

        default void b() {

        }
    }
    class Test02 implements Test01 {

        @Override
        public void a() {

        }

//        @Override
//        public void b() {
//
//        }
    }

    @Test
    public void test() {
        for (Method method : Test02.class.getMethods()) {
            System.out.println(method.getName() + " is default: " + method.isDefault());
        }
    }

}
