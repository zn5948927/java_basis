package com.guigu.basis.JUC.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program： com.guigu.basis.JUC.lock
 * @description:
 * @author:
 * @create: 2021-01-15 19:05
 **/
class ShareResource {
    private int number = 1;//A:1 B:2 C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void printFive() {
        lock.lock();
        try {
            //1 判断
            while (number != 1) {
                c1.await();
            }
            //2 干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3 通知
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printTen() {
        lock.lock();
        try {
            //1 判断
            while (number != 2) {
                c1.await();
            }
            //2 干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3 通知
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printFifteen() {
        lock.lock();
        try {
            //1 判断
            while (number != 3) {
                c1.await();
            }
            //2 干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3 通知
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SyncAndReentrantLockDemo {
    /**
     * 多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
     * AA打印5次，BB打印10次，CC打印15次
     * 循环10轮
     *
     * @param args
     */
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                shareResource.printFive();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                shareResource.printTen();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                shareResource.printFifteen();
            }
        }, "C").start();
    }

}

