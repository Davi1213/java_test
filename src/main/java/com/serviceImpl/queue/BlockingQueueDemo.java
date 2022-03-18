package main.java.com.serviceImpl.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.add("aa");
        blockingQueue.add("bb");
        blockingQueue.remove();
        //判断是否为空,为空则抛出异常 否则返回队首的值
        blockingQueue.element();
    }
}
