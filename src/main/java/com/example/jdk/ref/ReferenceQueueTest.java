package com.example.jdk.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 当我们想检测一个对象是否被回收了，那么我们就可以采用 Reference1 + ReferenceQueue，大概需要几个步骤：
 * 1 创建一个引用队列 queue
 * 2 创建 Reference 对象，并关联引用队列 queue
 * 3 在 reference 被回收的时候，Reference 会被添加到 queue 中
 *
 * 总结: Reference 和引用队列 ReferenceQueue 联合使用时，如果 Reference 持有的对象被垃圾回收，Java 虚拟机就会把这个弱引用加入到与之关联的引用队列中。
 *
 * 通过添加 vm 参数 -XX:+PrintGCTimeStamps -XX:+PrintGCDetails
 */
public class ReferenceQueueTest {

    public static void main(String[] args)  {


        /*
          其内部提供2个构造函数，一个带queue，一个不带queue。
          其中queue的意义在于，我们可以在外部对这个queue进行监控。即如果有对象即将被回收，那么相应的reference对象就会被放到这个queue里。我们拿到reference，就可以再作一些事务。
          而如果不带的话，就只有不断地轮询reference对象，通过判断里面的get是否返回null( phantomReference对象不能这样作，其get始终返回null，因此它只有带queue的构造函数 )。
          这两种方法均有相应的使用场景，取决于实际的应用。如weakHashMap中就选择去查询queue的数据，来判定是否有对象将被回收。而ThreadLocalMap，则采用判断get()是否为null来作处理。

          如果我们在创建一个引用对象时，指定了ReferenceQueue，那么当引用对象指向的对象达到合适的状态（根据引用类型不同而不同）时，GC 会把引用对象本身添加到这个队列中，方便我们处理它，
          因为 “引用对象指向的对象 GC 会自动清理，但是引用对象本身也是对象（是对象就占用一定资源），所以需要我们自己清理。”


          链接：https://www.jianshu.com/p/f86d3a43eec5
         */

        // 创建一个引用队列
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        /*
        引用类型 | 被回收时间    |  取得目标对象方式	| 垃圾回收条件   | 是否可能内存泄漏  | 用途
        强引用   | 从来不会      |  直接调用	    | 不回收	       | 可能            | 对象的一般状态
        软引用	| 内存不足时    |                | 视内存情况回收  | 不可能          | 对象缓存
        弱引用	| jvm垃圾回收时 | 通过get()方法	| 永远回收	   | 不可能          | 对象缓存
        虚引用	| 未知         | 无法取得	        | 不回收	       | 可能            | 未知
         */



//        System.out.println("soft.....");
//        soft(queue);
//        System.out.println("weak.....");
//        weak(queue);
        System.out.println("phantom......");
        phantom(queue);

    }




    /**
     * 如果一个对象只有软引用，则在内存充足的情况下是不会回收此对象的，但是，在内部不足即将要抛出OOM异常时就会回收此对象来解决内存不足的问题。
     * 运行结果均为：true。
     *
     * 这也就说明了当内存充足的时候一个对象只有软引用也不会被JVM回收。
     * @param queue
     * https://blog.csdn.net/gdutxiaoxu/article/details/80738581
     */
    static void soft(ReferenceQueue<Object> queue) {
        Object obj = new Object();
        SoftReference<Object> sf = new SoftReference<>(obj, queue);
        System.out.println("before gc : " + (sf.get()!=null));
        System.gc();
//        obj = null;
        System.out.println("before gc : " + (sf.get()!=null));
    }


    /**
     * WeakReference 基本与SoftReference 类似，只是回收的策略不同。
     *
     * 只要 GC 发现一个对象只有弱引用，则就会回收此弱引用对象。
     * 但是由于GC所在的线程优先级比较低，不会立即发现所有弱引用对象并进行回收。只要GC对它所管辖的内存区域进行扫描时发现了弱引用对象就进行回收。
     *
     * 运行结果为： true 、false
     * 在指向 obj = null 语句之前，Object对象有两条引用路径，其中一条为obj强引用类型，另一条为wr弱引用类型。
     * 此时无论如何也不会进行垃圾回收。当执行了obj = null.Object 对象就只具有弱引用，并且我们进行了显示的垃圾回收。因此此具有弱引用的对象就被GC给回收了。
     *
     * @param queue
     */
    static void weak(ReferenceQueue<Object> queue) {
        // 创建弱引用，此时状态为Active，并且Reference.pending为空，当前Reference.queue = 上面创建的queue，并且next=null
        Object obj = new Object();
        WeakReference<Object> reference = new WeakReference<>(obj, queue);
        System.out.println(reference);
        System.out.println("before gc obj: " + reference.get());
        obj = null;
        // 当GC执行后，由于是弱引用，所以回收该object对象，并且置于pending上，此时reference的状态为PENDING
        System.gc();

        /* ReferenceHandler从pending中取下该元素，并且将该元素放入到queue中，此时Reference状态为ENQUEUED，Reference1.queue = ReferenceQueue.ENQUEUED */

        /* 当从queue里面取出该元素，则变为INACTIVE，Reference1.queue = ReferenceQueue.NULL */
//        Reference<?> reference1 = queue.remove();
//        System.out.println(reference1);
        System.out.println("after gc obj: " + reference.get());
    }


    /**
     * PhantomReference，即虚引用，虚引用并不会影响对象的生命周期。
     * 虚引用的作用为：跟踪垃圾回收器收集对象这一活动的情况。
     *
     * 当GC一旦发现了虚引用对象，则会将PhantomReference对象插入ReferenceQueue队列，而此时PhantomReference对象并没有被垃圾回收器回收，而是要等到ReferenceQueue被你真正的处理后才会被回收。
     *
     * 注意：PhantomReference必须要和ReferenceQueue联合使用，SoftReference和WeakReference可以选择和ReferenceQueue联合使用也可以不选择，这使他们的区别之一。
     *
     * 运行结果:null null 回收
     *
     * 根据例子有两点需要说明：
     *
     * 1 PhantomReference的get方法无论在上面情况下都是返回null。这个在PhantomReference源码中可以看到。
     *
     * 2 在代码中，如果obj被置为null，当GC发现虚引用，GC会将把 PhantomReference 对象pr加入到队列ReferenceQueue中，
     * 注意此时pr所指向的对象并没有被回收，在我们现实的调用了 rq.poll() 返回 Reference 对象之后当GC第二次发现虚引用，而此时 JVM 将虚引用pr插入到队列 rq 会插入失败，此时 GC 才会对虚引用对象进行回收。
     *
     * @param queue
     */
    static void phantom(ReferenceQueue<Object> queue) {

        Object obj = new Object();
        PhantomReference<Object> pr = new PhantomReference<>(obj, queue);
        System.out.println(pr.get());
        obj = null;
        System.gc();
        System.out.println(pr.get());
        Reference<Object> r = (Reference<Object>) queue.poll();
        if(r!=null){
            System.out.println("回收");
        }

    }


}
