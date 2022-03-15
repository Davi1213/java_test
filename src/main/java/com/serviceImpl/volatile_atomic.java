package main.java.com.serviceImpl;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author louwei
 * @ClassName: volatile_atomic
 * @Description:
 */
/* 原子性:
      不可分割，完整性，即，某个线程正在做某个具体的业务时，中间不可加塞或者被分割。
      需要整体完整性，要么同时成功，要么同时失败

   --> 可以使用AtomicInteger类来保证原子性, 或 synchronized
 */


public class volatile_atomic {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        //try{
        //    TimeUnit.SECONDS.sleep(5);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        /* ------->  为什么是大于2呢？ <-------*/
        // 因为后台默认存在两个线程,一个是main线程,一个是GC线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t finally number value : " + myData.atomicInteger);

    }


}
