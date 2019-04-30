package com.mtl.beanFactroy.test;

import com.mtl.itf.Service;
import com.mtl.itf.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 说明:
 *
 * @作者 莫天龙
 * @时间 2019/04/30 9:52
 */
public class MainTest {
    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean*.xml");
        Service bean = applicationContext.getBean(Service.class);
        bean.test("222");
        UserService userService = applicationContext.getBean(UserService.class);
        userService.save("user");
        applicationContext.close();
    }
}
