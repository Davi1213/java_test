package main.java.com.serviceImpl;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author louwei
 * @ClassName: SemaphoreDemo
 * Semaphore ：(有减就会有增，有增就会有减)
 * 信号量主要有两个目的：
 * 1、用于多个线程共享资源的互斥使用
 * 2、用于并发线程数的控制
 * 如：争车位
 */

public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "号车 抢到车位");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("三秒后，" + Thread.currentThread().getName() + "号车离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //三秒后释放车位
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }

    }
}
