package main.java.com.serviceImpl;

import main.java.com.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * @author louwei
 * @ClassName: CountDownLatchDemo
 * @Description: CountDownLatch: 做减法
 */

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        //for (int i = 1; i <= 6; i++) {
        //    new Thread(() -> {
        //        System.out.println("倒计时开始: " + Thread.currentThread().getName());
        //        countDownLatch.countDown();
        //    }, CountryEnum.countryEnumForEach(i).getRetMessage()).start();
        //}
        //countDownLatch.await();
        //System.out.println(Thread.currentThread().getName() + "\t 发射");


        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "  结束");
                countDownLatch.countDown();
            }, CountryEnum.countryEnumForEach(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "  统一");
    }
}
