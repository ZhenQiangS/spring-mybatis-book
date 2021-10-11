package com.ay.test.reflect;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * 反射使用
 */
public class reflectClassTest {

    private static String TAG = "com.ay.test.reflect.Book";

    @Test
    public void test() {
        try {
            Class<?> book = Class.forName(TAG);
            Method method = book.getDeclaredMethod("print3");
            method.invoke(book);

            Method method2 = book.getDeclaredMethod("print2");
            // Book book1 =(Book) book.newInstance();

            //抛出异常 book 没有实例化.
            method2.invoke(book);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
