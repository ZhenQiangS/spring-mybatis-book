package com.ay.test;

import org.junit.Test;

public class ReferenceTest {

    @Test
    public void test() {
        Student s = new Student("第一个学生", 6);
        s.toString();

        t1(s);
        s.toString();

        t2(s);
        s.toString();
        t3(s);
        s.toString();
    }

    private Student t1(Student student) {
        student = null;
        return student;
    }

    private Student t2(Student student) {
        student.name = "名称被改变";
        return student;
    }

    private Student t3(Student student) {
        Student s = new Student("学生甲", 12);
        student = s;
        return student;
    }
}

class Student {
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String name;
    int age;

    @Override
    public String toString() {
        String s = name + "：" + age + "岁";
        System.out.println(s);
        return s;
    }
}
