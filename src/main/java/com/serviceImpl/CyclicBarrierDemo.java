package main.java.com.serviceImpl;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author louwei
 * @ClassName: CyclicBarrierDemo
 * @Description: CyclicBarrier: 做加法
 */

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("---------人员已到位,会议开始---");
        });

        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            System.out.println(Thread.currentThread().getName() + " 请入座等待 ");
            new Thread(() -> {
                try {
                    // 先到先等
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();

        }
    }


}
