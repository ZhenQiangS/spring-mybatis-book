package com.ay.test.aopDemo;

import java.lang.reflect.Method;

/**
 * 描述：日志实现类
 */
public class MyLoggerImpl implements MyLogger {
    public void printIntoMethodTime(Method method) {
        System.out.println("进入：" + method.getName() + "方法时间：" + System.currentTimeMillis());
    }

    public void printOutMethodTime(Method method) {
        System.out.println("退出：" + method.getName() + "方法时间：" + System.currentTimeMillis());
    }
}
