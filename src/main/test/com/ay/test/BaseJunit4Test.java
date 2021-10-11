package com.ay.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 描述：测试基类
 *
 * @author ZQS
 * @create 2020/2/29
 * <p>
 * RunWith：参数化运行器，用于指定JUnit运行环境，是JUnit提供给其他框架测试环境接口扩展，为了便于使用Spring的依赖注入，
 * Spring提供了SpringJUnit4ClassRunner作为JUnit测试环境。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BaseJunit4Test {
}
