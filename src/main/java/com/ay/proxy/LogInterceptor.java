package com.ay.proxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 描述：日志拦截（切面）
 *
 * @author ZQS
 * @create 2020/03/09
 */
@Aspect //切面
@Component //组件
@Slf4j
public class LogInterceptor {


//    @Before(value = "execution(* com.ay.controller.*.*(..))")
//    public void before() {
//        String str = "进入方法时的时间为：" + System.currentTimeMillis();
//        log.info(str);
//        System.out.println(str);
//    }
//
//    @After(value = "execution(* com.ay.controller.*.*(..))")
//    public void after() {
//        String str = "退出方法时的时间为：" + System.currentTimeMillis();
//        log.info(str);
//        System.out.println(str);
//    }
//
    @Before("pointcut()")
    public void before() {
        String str = "进入方法前";
        log.info(str);
    }

    @Before("pointcut()")
    public void after() {
        String str = "执行完方法后";
        log.info(str);
    }

    @Pointcut("execution(* com.ay.controller.*.*(..))")
    public void pointcut() {
    }
}
