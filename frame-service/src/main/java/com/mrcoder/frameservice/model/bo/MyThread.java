package com.mrcoder.frameservice.model.bo;

public class MyThread implements Runnable {

    //对象锁
    private Object lock;

    public MyThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                System.out.println("开始---" + Thread.currentThread().getName() + "---wait time = " + System.currentTimeMillis());
                lock.wait();
                System.out.println("结束---" + Thread.currentThread().getName() + "---wait time = " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

