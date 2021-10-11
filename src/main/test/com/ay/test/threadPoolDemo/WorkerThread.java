package com.ay.test.threadPoolDemo;

import sun.reflect.generics.tree.VoidDescriptor;

public class WorkerThread implements Runnable {

    private String command;

    public WorkerThread(String s) {
        command = s;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始 执行" + command);
        processCommand();
        System.out.println(Thread.currentThread().getName() + " 结束。");
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return command;
    }
}
