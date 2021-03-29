package com.example.jdk.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author lr
 * @date 2020/10/27
 */
public class SolveABAByAtomicMarkableReference {
    private static AtomicMarkableReference<Integer> atomicMarkableReference = new AtomicMarkableReference<>(100, false);

    public static void main(String[] args) {


        Thread refT1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicMarkableReference.compareAndSet(100, 101, atomicMarkableReference.isMarked(), !atomicMarkableReference.isMarked());
            atomicMarkableReference.compareAndSet(101, 100, atomicMarkableReference.isMarked(), !atomicMarkableReference.isMarked());
        });

        Thread refT2 = new Thread(() -> {
            boolean marked = atomicMarkableReference.isMarked();
            System.out.println("ff "+ marked);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean c3 = atomicMarkableReference.compareAndSet(100, 101, marked, !marked);
            System.out.println(c3); // 返回true,实际应该返回false
            System.out.println(marked);
        });

        refT1.start();
        refT2.start();
    }

}
