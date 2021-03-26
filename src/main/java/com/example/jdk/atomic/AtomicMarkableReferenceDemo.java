package com.example.jdk.atomic;

import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author liurui
 * @date 2020/10/27
 */
public class AtomicMarkableReferenceDemo {


    private static final boolean DEFAULT_MARK_FLAG = false;
    private static AtomicMarkableReference<Integer> amr = new AtomicMarkableReference<>(100, DEFAULT_MARK_FLAG);

    public static void main(String[] args) {
        Thread refT2 = new Thread(() -> {
            final boolean marked = amr.isMarked();
            final Integer reference = amr.getReference();
            System.out.println("refT2 currentValue=" + amr.getReference() + ", currentMark=" + marked);

            // 这段目的：模拟处理其他业务花费的时间
            safeSleep(300);

            // 别的线程操作时，根据匹配之前的（值、mark）和最新的（值、mark），如果同值,且 mark 不为默认值就是执行过了
            if (reference.equals(amr.getReference())
                    && amr.isMarked() == DEFAULT_MARK_FLAG) { // fixed: 最新的 mark 为 默认值时才进行 cas

                System.out.println("refT2 currentValue=" + amr.getReference() + ", currentMark=" + amr.isMarked()
                        + ", oldValue=" + reference + ", oldMark=" + marked);
                boolean casResult = amr.compareAndSet(reference, reference + 1, DEFAULT_MARK_FLAG, !DEFAULT_MARK_FLAG);
                System.out.println("refT2 cas result --------> " + casResult);
            }
            System.out.println("refT2 ending ...");
        });

        refT2.start();
        // 这段目的：为了让 refT2 线程先跑起来
        safeSleep(100);

        Thread refT1 = new Thread(() -> {
            // 同个线程操作时设置 mark 为 默认值的取反，且之后不要改变

            final Integer reference = (Integer) amr.getReference();
            System.out.println("refT1 currentValue=" + reference + ", currentMark=" + amr.isMarked());
            boolean casResult = amr.compareAndSet(reference, reference + 1, DEFAULT_MARK_FLAG, !DEFAULT_MARK_FLAG);
            System.out.println("refT1 cas result --------> " + casResult);

            final Integer reference2 = (Integer) amr.getReference();
            final boolean marked = amr.isMarked();
            System.out.println("refT1 currentValue=" + reference2 + ", currentMark=" + marked);
            boolean casResult2 = amr.compareAndSet(reference2, reference2 - 1, marked, marked);
            System.out.println("refT1 cas2 result --------> " + casResult2);

            System.out.println("refT1 currentValue=" + amr.getReference() + ", currentMark=" + amr.isMarked());
            System.out.println("refT1 ending ...");
        });

        refT1.start();
    }

    private static void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
