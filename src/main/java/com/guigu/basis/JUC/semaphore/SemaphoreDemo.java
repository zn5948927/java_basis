package com.guigu.basis.JUC.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @program： com.guigu.basis.JUC.semaphore
 * @description:
 * @author:
 * @create: 2021-01-13 16:59
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        //模拟3个停车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    //暂停一会线程
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t停车3秒后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }

            }, String.valueOf(i)).start();

        }
    }

}
