package main.java.com.serviceImpl;

/**
 * @author louwei
 * @ClassName: Bing_Fa
 * @Description: 并发的三大特新
 */

/**
 * 1.【原子性(Atomicity)】 ：一个操作或多个操作, 要么全部执行完,要么都不执行
 *     关键字: synchronized
 *   综上可知，对变量的写操作不依赖于当前值才是原子级别的，在多线程环境中才可以不用考虑多并发问题
 *   比如：n=n+1 、n++就不行，n=m+1才是原子级别的
 *   实在没把握就使用synchronized关键字来代替volatile关键字。
 * 2.【可见性(Visibility)】: 当一个线程线程修改了共享变量的值，其他线程会马上知道这个修改，
 *                          当其他线程要读取这个变量的时候，最终会去"内存"中读取，而不是从缓存中读取。
 *     主要涉及 "总线lock"和 "MESI协议"
 *     关键字: volatile , synchronized , final
 *    当对非volatile变量进行读写的时候，每个线程先从内存拷贝变量到CPU缓存中，如果计算机有多个CPU，
 *    每个线程可能在不同的CPU上被处理，这意味着每个线程可以拷贝到不同的CPU cache中。
 *    而声明了volatile的变量
 * 3.【有序性(Ordering)】:
 *   虚拟机在进行代码编译的时,对那些改变顺序之后不会对最终结果造成影响的代码,
 *   虚拟机不一定会按照代码的顺序来执行, 如此一来对变量的值没有造成影响,但有可能会出现线程安全问题
 *     关键字：volatile、synchronized
 *      volatile本身就包含了禁止指令重排序的语义，
 *      而sychronized关键字是由 "一个变量在同一时刻只允许一条线程对其进行lock操作" 这条规则明确的
 *
 * 【小结】：
 *  1. synchronized关键字同时满足以上三种特性，但是volatile不满足原子性
 *  2. 在某些情况下，volatile的同步机制的性能确实要优于锁(使用synchronized关键字或java.util.concurrent包里面的锁)，
 *     因为volatile的总开销要比锁低
 *  3. 我们判断使用volatile还是加锁的唯一依据就是volatile的语义能否满足使用的场景(原子性)
 */
public class Bing_Fa {
    /* i++ 不是线程安全的: 无法保证原子性和可见性, 故我么可以使用 AtormicInteger类 */





}
