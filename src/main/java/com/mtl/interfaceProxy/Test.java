package com.mtl.interfaceProxy;

import com.mtl.itf.Service;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 说明:测试类
 *
 * @作者 莫天龙
 * @时间 2019/04/29 17:51
 */
public class Test {
    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("app*.xml");
        Service service = applicationContext.getBean(Service.class);
        service.test("123");
        applicationContext.close();
    }
}
