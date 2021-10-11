package com.ay.test.threadPoolDemo;

import com.ay.test.threadDemo.TheadDeadLock;

import java.util.concurrent.*;

public class WorkerPool {
    public static void main(String args[]) throws InterruptedException {

        RejectedExecutionHandlerImpl rejectedImpl = new RejectedExecutionHandlerImpl();

        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3), threadFactory, rejectedImpl);

        MyMonitorThread myMonitorThread = new MyMonitorThread(executorPool, 3);
        Thread thread = new Thread(myMonitorThread);
        thread.start();
        for (int i = 0; i < 10; i++) {
            executorPool.execute(new WorkerThread("cmd " + i));
        }
        Thread.sleep(300000);
        executorPool.shutdown();
        Thread.sleep(5000);
        myMonitorThread.shutdown();

    }
}
