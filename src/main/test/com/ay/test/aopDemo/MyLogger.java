package com.ay.test.aopDemo;

import java.lang.reflect.Method;

/**
 * 描述：日志实现类
 *
 * @author ZQS
 * @create 2020/03/09
 */
public interface MyLogger {

    /**
     * 记录进入方法时时间
     */
    void printIntoMethodTime(Method method);

    /**
     * 记录退出方法时时间
     */
    void printOutMethodTime(Method method);
}
