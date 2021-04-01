package example.jdk.concurrent;

/**
 * @author lr
 * @date 2020/9/17
 */
public class SynchronizedTest {

    private static Object lock = new Object();

    static class ThreadA implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    if (i == 50) {
                        try {
//                            Thread.yield();
                            lock.wait(10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Thread A " + i);
                }
            }
        }
    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("************");
                wait();
            } catch (InterruptedException e) {
                System.out.println("************");
                e.printStackTrace();
                System.out.println("************");
            }
            synchronized (lock) {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread B " + i);
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
//        Thread.sleep(10);
        new Thread(new ThreadB()).start();
    }


}
