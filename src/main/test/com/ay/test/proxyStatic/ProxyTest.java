package com.ay.test.proxyStatic;

/**
 * 描述：静态代理
 *
 * @author ZQS
 * @create 2020/03/09
 */
public class ProxyTest {
    public static void main(String[] args) {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.opreation();
    }
}

/**
 * 描述：抽象主题类
 */
abstract class Subject {

    abstract void opreation();
}

/**
 * 描述：真实主题类
 */
class RealSubject extends Subject {

    void opreation() {
        System.out.println("opreation .....");
    }
}

/**
 * 描述：代理类
 */
class Proxy extends Subject {

    private Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    void opreation() {

        //前置处理
        this.preOpreation();

        //具体操作
        subject.opreation();

        //后置处理
        this.postOpreation();
    }

    void preOpreation() {
        System.out.println("pre operation......");
    }

    void postOpreation() {
        System.out.println("post operation......");
    }

}

