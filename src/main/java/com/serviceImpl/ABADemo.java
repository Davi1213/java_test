package main.java.com.serviceImpl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author louwei
 * @ClassName: AtomicReferenceDemo_2
 * @Description:
 */
/* 避免 ABA 问题 : 原子引用 + 新改版本号 (类似时间戳) */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        // "ABA" 复现:
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            try {
                // 等待一秒,保证t1线程完成一次ABA操作
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2022) + "\t" + atomicReference.get());
        }, "t2").start();

        try {
            // 等待一秒,保证t1线程完成一次ABA操作
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------以下是ABA问题的解决方式-----------------");

        new Thread(() -> {
            // "ABA" 复现:
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号: " + stamp);
            try {
                // 等待一秒,保证t1线程完成一次ABA操作
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第二次版本号: " + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第三次版本号: " + atomicStampedReference.getStamp());
        }, "t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第一次版本号: " + stamp);
            try {
                // 等待一秒,保证F1线程完成一次ABA操作
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isSucess = atomicStampedReference.compareAndSet(100, 2022, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t修改成功否: " + isSucess + "\t当前最新版本号: " + atomicStampedReference.getStamp());
            Integer result = atomicStampedReference.getReference();
            System.out.println(Thread.currentThread().getName() + "\t当前实际最新值: " + result);
        }, "t4").start();


    }
}
