package com.ay.test.threadDemo;

import org.apache.activemq.util.Suspendable;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {
    int value;
    private volatile int value2;
    private int value3;

    public AtomicInteger val = new AtomicInteger();


    public VolatileTest volatileTest;

    public static void main(String[] args) throws Exception {
        final VolatileTest volatileTest = new VolatileTest();

        Thread t1 = new Thread() {
            @Override
            public void run() {
                volatileTest.increment();
            }
        };
        t1.start();
        Thread.sleep(500);

        System.out.println("当前值：" + volatileTest.value);


        Thread t2 = new Thread() {
            @Override
            public void run() {
                volatileTest.value = 10;
                volatileTest.increment2();
            }
        };
        t2.start();
        Thread.sleep(500);

        System.out.println("当前值：" + volatileTest.value);
        System.out.println("当前值：" + volatileTest.value2);

        Thread.sleep(5020);

    }

    @Test
    public void test2() throws Exception {


        for (int i = 0; i < 1000000; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    val.incrementAndGet();
                }
            };
            t.start();
        }


        Thread.sleep(2000);
        System.out.println(val.toString());

    }

    @Test
    public void test3() throws Exception {


        for (int i = 0; i < 4; i++) {

            Thread t1 = new Thread(new MyThread());
            t1.start();
        }
        System.in.read();

    }

    public synchronized void increment() {
        for (long i = 0; i < 888880000; i++) {
            value++;
        }
    }

    public synchronized void increment2() {
        for (long i = 0; i < 888880000; i++) {
            value2++;
        }
    }

    public synchronized int increment3() {
        return value3++;
    }

    class MyThread extends Thread {

        Thread t;

        MyThread() {

        }

        MyThread(Thread t) {
            this.t = t;
        }

        @Override
        public void run() {
            synchronized (this) {
                for (int i = 0; i < 3; i++) {
                    System.out.println(i);
                }
                if (t != null)
                    t.start();
            }
        }
    }
}
