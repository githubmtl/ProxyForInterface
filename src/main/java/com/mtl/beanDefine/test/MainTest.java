package com.mtl.beanDefine.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 说明:
 *
 * @作者 莫天龙
 * @时间 2019/04/30 9:33
 */
public class MainTest {
    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("test.xml");
        Person per = applicationContext.getBean(Person.class);
        System.out.println(per);
        applicationContext.close();
    }
}
