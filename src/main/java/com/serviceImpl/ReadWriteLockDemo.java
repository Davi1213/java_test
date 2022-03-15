package main.java.com.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author louwei
 * @ClassName: ReadWriteLockDemo
 * @Description: 【--- 重写锁 ---】
 * 多个线程同时读一个资源类，没有任何问题；故而为了满足并发量，读取资源类应该可以同时进行。
 * 但是若有一个线程想去写共享资源类，就不应该再有其他线程可以对该资源类进行重写
 * 小总结：
 * 读-读：可以共享
 * 读-写：不可以共享
 * 写-写：不可以共享
 */

public class ReadWriteLockDemo {

    private volatile Map<String, Object> map = new HashMap<>();

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println("线程" + Thread.currentThread().getName() + "\t 正在写入");
            try {
                //模拟网络拥堵
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println("线程" + Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println("线程" + Thread.currentThread().getName() + "\t 正在读取");
            try {
                //模拟网络拥堵
                TimeUnit.MICROSECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object value = map.get(key);
            System.out.println("线程" + Thread.currentThread().getName() + "\t 读取完成  :" + value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }

    public static void main(String[] args) {

        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();

        //写值
        for (int i = 1; i <= 5; i++) {
            final int finalI = i;
            new Thread(() -> {
                readWriteLockDemo.put(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }

        //读值
        for (int i = 1; i <= 5; i++) {
            final int finalI = i;
            new Thread(() -> {
                readWriteLockDemo.get(finalI + "");
            }, String.valueOf(i)).start();
        }
    }

}
