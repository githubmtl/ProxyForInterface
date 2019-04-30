package com.mtl.interfaceProxy;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 说明:代理对象的FactoryBean,继承至Spring FactoryBean,通过调用getBean获取代理对象
 *
 * @作者 莫天龙
 * @时间 2019/04/29 17:33
 */
public class ProxyFactoryBean<T> implements FactoryBean {
    //被代理的接口Class对象
    private Class<T> interfaceClass;

    public ProxyFactoryBean(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }


    @Override
    public T getObject() throws Exception {
        //通过JDK动态代理创建代理类
        return (T)Proxy.newProxyInstance(
                interfaceClass.getClassLoader(), new Class[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //实现业务逻辑,比如发起网络连接，执行远程调用，获取到结果，并返回
                        System.out.println(method.getName()+" method invoked ! param: "+ Arrays.toString(args));
                        return null;
                    }
                });
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }
}
