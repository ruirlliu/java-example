package example.jdk.concurrent.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author lr
 * @date 2019/7/10 17:58
 */
public class ThreadUtils {
    // Thread
    public static void useThreadPool(Object job) {
        ExecutorService executorService = Executors.newWorkStealingPool();
        //executorService.a
        for (int i = 0; i < 200; i++) {
            System.out.println(i);
            executorService.execute( () -> {
                    System.out.println("jjjj");
                try {
                    System.out.println(Thread.currentThread().getName());
                    //TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void main(String[] args) {
        try {
            PipedReader pr = new PipedReader();
            PipedWriter pw = new PipedWriter(pr);

            ThreadPoolExecutor executor = new ThreadPoolExecutor(
                    5,
                    7,
                    TimeUnit.MINUTES.toSeconds(2),
                    TimeUnit.MINUTES,
                    new LinkedBlockingDeque<>());

        } catch (IOException e) {
            e.printStackTrace();
        }

        useThreadPool(new Object());
    }


}
