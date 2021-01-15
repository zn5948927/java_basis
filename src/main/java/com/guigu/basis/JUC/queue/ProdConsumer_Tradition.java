package com.guigu.basis.JUC.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program： com.guigu.basis.JUC.queue
 * @description:
 * @author:
 * @create: 2021-01-15 18:31
 * 题目： 一个初始值为零的变量，；两个线程对其交替操作，一个加1 一个减1 来5轮
 * 1。 线程 操作(方法) 资源量
 * 2. 判读  干活 通知、
 * 3. 防止虚假唤醒机制
 **/
//资源类
class ShareData {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //加1
    public void increment() throws Exception {
        lock.lock();
        try {
            //1 判断
            while (number != 0) {
                //等待 不能生产
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //减1
    public void decrement() throws Exception {
        lock.lock();
        try {
            //1 判断
            while (number == 0) {
                //等待 不能生产
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ProdConsumer_Tradition {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}
