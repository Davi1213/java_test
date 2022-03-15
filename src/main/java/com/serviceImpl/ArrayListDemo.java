package main.java.com.serviceImpl;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * ArrayList 是线程不安全的 : "集合类不安全的问题"
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        //List<String> list = Arrays.asList("a", "b", "c");
        //list.forEach(System.out::println);

        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 9));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }


        /** 1、故障现象: “并发修改异常”
         *     java.util.ConcurrentModificationException
         *  2、导致原因:
         *  3、解决方案:
         *     (1) 使用 new Vector<>()
         *          :此方法是ArrayList<>()的前身，加了synchronized锁
         *     (2) 使用 Collections.synchronizedList(new ArrayList<>())
         *     --------------------------------------------------------
         *     (3)** Concurrent包下的 CopyOnWriteArrayList()
         *
         *  4、优化建议:
         *
         */


    }
}
