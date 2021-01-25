package com.guigu.basis.JUC.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Title:
 * @Description: 多线程中，第3种获取多线程的方法
 * Runnable与Callable区别
 * 1.Runnable无返回值，Callable有返回值
 * 2.Runnable不抛异常，Callable会抛异常
 * 3.
 * @Author Ning
 * @Date
 * @Version 0.0.1
 * @Company:
 **/

//class MyThread implements Runnable{
//
//    @Override
//    public void run() {
//
//    }
//}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("*****come in callable");
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        Thread t1 = new Thread(futureTask, "AAA");
        t1.start();

        System.out.println("*****result"+futureTask.get());
    }
}
