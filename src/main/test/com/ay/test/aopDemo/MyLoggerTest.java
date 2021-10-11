package com.ay.test.aopDemo;

import java.lang.reflect.Proxy;

public class MyLoggerTest {

    public static void main(String[] args) {

        // 实例化真实项目中业务类
        BusinessClassService businessClassService = new BusinessClassServiceImpl();

        // 日志类的 handler
        MyLoggerHandler myLoggerHandler = new MyLoggerHandler(businessClassService);

        //获取代理类对象
        BusinessClassService businessClass =
                (BusinessClassService) Proxy.newProxyInstance(businessClassService.getClass().getClassLoader(),
                        businessClassService.getClass().getInterfaces(),
                        myLoggerHandler);

        // 执行代理类方法
        businessClass.doSomeThing();
    }
}
