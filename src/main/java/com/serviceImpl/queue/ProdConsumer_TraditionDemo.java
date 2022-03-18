package main.java.com.serviceImpl.queue;

/**
 * @author louwei
 * @ClassName: ProdConsumer_TraditionDemo
 * @Description: 生产者消费者模式
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程下：
 * 1、线程 操做(方法) 资源类
 * 2、判断 干活 通知
 * 3、防止虚假唤醒
 * <p>
 * sync -> lock
 * wait -> await
 * notify -> signal
 * 题一: 一个初始值为0的变量，两个线程对其交替操作，一个减一个加，进行五轮
 */

//资源类
class ShareData {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public void increment() throws Exception {
        lock.lock();
        try {
            //1、判断
            while (number != 0) {
                //等待，不能生产
                condition.await();
            }
            //2、干活
            number++;
            System.out.println(Thread.currentThread().getName() + " --> " + number);

            //3、通知唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            //1、判断
            while (number == 0) {
                //等待，不能生产
                condition.await();
            }
            //2、干活
            number--;
            System.out.println(Thread.currentThread().getName() + " --> " + number);

            //3、通知唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static class ProdConsumer_TraditionDemo {

        public static void main(String[] args) {
            ShareData shareData = new ShareData();

            new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    try {
                        // 加
                        shareData.increment();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "生产").start();

            new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    try {
                        //减
                        shareData.decrement();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "消费").start();
        }
    }

}
