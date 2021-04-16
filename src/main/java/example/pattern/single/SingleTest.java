package example.pattern.single;

import org.springframework.web.client.RestTemplate;

/**
 * 单例模式的进化
 * http://www.voidcn.com/article/p-cljmhult-zg.html
 * @author liurui
 * @date 2021/4/14
 */
public class SingleTest {

    // 1、饥饿模式，类初始化就创建对象，性能较低。
    public static class HungryModeSingle {

        private static final RestTemplate template = new RestTemplate();

        public static RestTemplate get() {
            return template;
        }

    }

    // 2、懒汉模式，延迟创建，但是不是线程安全。
    public static class LazyModeSingle {

        private static RestTemplate template;

        public static RestTemplate get() {
            if (template == null) {
                template = new RestTemplate();
            }
            return template ;
        }

    }

    // 3、线程安全，使用 synchronized 关键字。
    // 但是锁住了方法，高并发下访问，会降低并发
    public static class LazyThreadSafeModeSingle {

        private static RestTemplate template;

        public static synchronized RestTemplate get() {
            if (template == null) {
                template = new RestTemplate();
            }
            return template ;
        }
    }

    //

    /**
     * 4、使用 Double-check 双重检查。
     * 先判断是否为空。若为空，加锁，进行对象的初始化。可以降低synchronized 性能，提高并发。
     * 这个开源中都会使用这种方式，spring，jdk中也会有。但是有一个问题，指定重排序的问题。
     * 对象的创建 类加载检查、分配内存、初始化零值、设置对象头、执行init方法
     * 这里面会有个重排序导致的问题，
     *    伪代码:
     *     memory = allocate();   //1：分配对象的内存空间
     *     ctorInstance(memory);  //2：初始化对象
     *     instance = memory;     //3：设置instance指向刚分配的内存地址
     *
     *    伪代码中的2和3之间，可能会被重排序，变成
     *     memory = allocate();   //1：分配对象的内存空间
     *     instance = memory;     //3：设置instance指向刚分配的内存地址
     *                        //注意，此时对象还没有被初始化！
     *     ctorInstance(memory);  //2：初始化对象
     *
     * 这样如果发生重排序，线程B 判断不为null，进行返回。但是此时对象还没有被线程A 初始化。
     * 继续进行更改
     */
    public static class LazyDoubleCheckModeSingle {

        private static RestTemplate template;

        public static RestTemplate get() {
            if (template == null) {
                synchronized (LazyDoubleCheckVolatileModeSingle.class) {
                    if (template == null) {
                        template = new RestTemplate();
                    }
                }
            }
            return template ;
        }
    }


    /**
     * 5、利用 volatile 关键字。
     * volatile 的语义
     * 1、保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
     * 2、禁止进行指令重排序。
     * https://www.cnblogs.com/igoodful/p/9473491.html
     */
    public static class LazyDoubleCheckVolatileModeSingle {

        private volatile static RestTemplate template;

        public static RestTemplate get() {
            if (template == null) {
                synchronized (LazyDoubleCheckVolatileModeSingle.class) {
                    if (template == null) {
                        template = new RestTemplate();
                    }
                }
            }
            return template ;
        }
    }


    /**
     * 6、基于类初始化的解决方案
     * JVM在类的初始化阶段（即在Class被加载后，且被线程使用之前），会执行类的初始化。
     * 在执行类的初始化期间，JVM会去获取一个锁。这个锁可以同步多个线程对同一个类的初始化。
     *
     * 基于这个特性，可以实现另一种线程安全的延迟初始化方案（这个方案被称之为Initialization On Demand Holder idiom）
     */
    public static class InstanceFactory {
        private static class InstanceHolder {
            public static RestTemplate instance = new RestTemplate();
        }

        public static RestTemplate getInstance() {
            return InstanceHolder.instance ;  //这里将导致InstanceHolder类被初始化
        }
    }


}
