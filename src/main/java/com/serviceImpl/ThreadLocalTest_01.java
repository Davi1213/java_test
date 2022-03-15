package main.java.com.serviceImpl;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author louwei
 * @ClassName: ThreadLocalTest_01
 * @Description:
 */

public class ThreadLocalTest_01 {

    public static void main(String[] args) {
        /* ThreadLocal是一种线程变量，ThreadLocal中填充的变量属于当前线程，
           该变量对于其他线程而言是隔离的，
           ThreadLocal会在每个线程中创建一个副本变量，可以使每个线程访问自己内部的副本变量
        --> 使用场景 :
           1. 在进行对象跨层传递的时候，使用ThreadLocal可以避免多次传递，打破层次间的约束
           2. 线程之间的数据隔离
           3. 进行事务操作，用于存储线程事务信息
           4. 数据库连接, session会话管理
         */
        //新建一个ThreadLocal
        ThreadLocal local = new ThreadLocal<>();
        //创建一个随机数类
        Random random = new Random();
        //创建五条线程
        IntStream.range(0, 5).forEach(f -> {
            new Thread(() -> {
                //为每一个线程设置相应的local值
                local.set(f + "  " + random.nextInt(5));
                System.out.println("线程的值 和 local的值 分别为: " + local.get());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

}
