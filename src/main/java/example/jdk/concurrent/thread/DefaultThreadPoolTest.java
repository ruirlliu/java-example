package example.jdk.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lr
 * @date 2019/8/19
 */
public class DefaultThreadPoolTest {
    // https://blog.csdn.net/hnd978142833/article/details/80253784
    public static void main(String[] args) {
        //testCacheThreadPool();
        //testScheduledThreadPool();
        //testScheduledThreadPool2();
        testFixedThreadPool();
        //testSingleThreadPool();
    }

    /**
     * 可缓存线程池，先查看池中有没有以前建立的线程，如果有，就直接使用。
     * 如果没有，就建一个新的线程加入池中，缓存型池子通常用于执行一些生存期很短的异步型任务。
     * 线程池为无限大，当执行当前任务时上一个任务已经完成，会复用执行上一个任务的线程，而不用每次新建线程
     */
    private static void testCacheThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            try {
                //sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 打印正在执行的缓存线程信息
            cachedThreadPool.execute(() -> System.out.println(Thread.currentThread().getName() + " 正在执行"));
        }
    }

    /**
     * 创建一个可重用固定个数的线程池，以共享的无界队列方式来运行这些线程
     * 因为线程池大小为4，每个任务输出打印结果后sleep 2秒，所以每两秒打印4个结果。
     */
    private static void testFixedThreadPool() {
        int processors = Runtime.getRuntime().availableProcessors();
        // 创建一个可重用固定个数的线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(processors);
        for (int i = 0; i < 20; i++) {
            fixedThreadPool.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is running");
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 创建一个定长线程池，支持定时及周期性任务执行
     * 延迟1秒执行
     */
    private static void testScheduledThreadPool() {
        //创建一个定长线程池，支持定时及周期性任务执行——延迟执行
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(() -> System.out.println("delay 1 sec to start"), 1, TimeUnit.SECONDS);
    }

    private static void testScheduledThreadPool2() {
        //创建一个定长线程池，支持定时及周期性任务执行——定期执行
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        // 延迟 1秒后， 每 3 秒执行
        scheduledThreadPool.scheduleAtFixedRate(() -> System.out.println("延迟1秒后每3秒执行一次"),
                                                1, 3, TimeUnit.SECONDS);
    }

    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     * 依次输出结果
     */
    private static void testSingleThreadPool() {
        // 创建一个单线程化的线程池
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is running, index == " + index);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
    }
}
