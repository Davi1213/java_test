package main.java.com.serviceImpl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author louwei
 * @ClassName: volatile_visibility
 * @Description:
 */

class MyData {
    volatile int number = 0;
    //int number = 0;

    public void addTo66() {
        this.number = 66;
    }

    public void addPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}

public class volatile_visibility {

    public static void main(String[] args) {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo66();
            System.out.println(Thread.currentThread().getName() + "\t updated number: " + myData.number);
        }, "AAA").start();

        //第二个线程
        while (myData.number == 0) {
            //System.out.println("哈哈，我胡汉三又回来了");
        }

        System.out.println(Thread.currentThread().getName() + "\t session is over, and main get number: " + myData.number);
    }

}
