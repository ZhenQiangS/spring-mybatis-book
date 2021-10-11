package com.ay.test.offen;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

/**
 * 面试常问
 */
public class OffenAsked {

    @Test
    public void foreach_test() {
        List<String> list = new ArrayList<String>();
        list.add("第一个");
        list.add("第2个");
        list.add("第3个");
        list.add("第4个");
        for (String str : list) {
            System.out.println(str);
            if (str == "第3个")
                list.remove(str);
        }

        for (Iterator<String> i = list.iterator(); i.hasNext(); ) {
            String item = i.next();
            System.out.println(item);
            if (item == "第3个")
                i.remove();
        }

    }

    @Test
    public void count() {
        File file = new File("D:/test.txt");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] b = new byte[1024];
        int a = 0;
        try {
            a = inputStream.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] str = new String(b, 0, a).split("");
        int count = 0;
        for (String s :
                str) {
            if ("y" == s)
                count++;
        }
        System.out.println(count);
    }

    private int count = 0;
    private volatile int count2 = 0;

    @Test
    public void TheadSecured() {
        new Thread(new TheadTest1()).start();
        new Thread(new TheadTest2()).start();

        try {

            System.in.read();
            System.out.println(count);

        } catch (Exception i) {
            i.printStackTrace();
        }

    }

    class TheadTest1 implements Runnable {
        public void run() {
            String[] arr = new String[100000];
            for (int i = 0; i < 100000; i++) {
                count++;
//                count2++;
//                arr[i]="线程1：第 "+(i+1)+" 次 。值："+count;
            }
            System.out.println(count);
            System.out.println(count2);

//            System.out.println(arr[30000]);
//            System.out.println(arr[99919]);
        }
    }

    class TheadTest2 implements Runnable {
        public void run() {

            String[] arr = new String[200000];

            for (int i = 0; i < 200000; i++) {
                count++;
//                count2++;
//                arr[i]="线程2：第 "+(i+1)+" 次 。值："+count;
            }

            System.out.println(count);
            System.out.println(count2);
//            System.out.println(arr[10000]);
//
//            System.out.println(arr[99999]);
            System.out.println("最后结果" + count);
        }
    }

}
