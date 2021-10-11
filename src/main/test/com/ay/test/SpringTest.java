package com.ay.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author ZQS
 * @date 2020/02/10
 */
@Service
public class SpringTest {

    static int a = 10;

    static {
        a += 5;
    }

    @Test
    public void testSpring() {

        //获取运用上下文
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringTest springTest = (SpringTest) applicationContext.getBean("springTest");
        System.out.println(a);
        String[] aa = applicationContext.getBeanDefinitionNames();
        springTest.syaHello();
    }

    static {
        a += 6;
    }

    public void syaHello() {
        System.out.println("hello ay");
    }
}
