package com.mtl.interfaceProxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * 说明:接口代理对象配置类
 * BeanDefinitionRegistryPostProcessor允许在常规的BeanFactoryPostProcessor检测开始之前注册更多的bean定义。
 *
 * @作者 莫天龙
 * @时间 2019/04/29 17:43
 */
public class InterfaceProxyconfigure implements BeanDefinitionRegistryPostProcessor {
    private String basePackge;

    public void setBasePackge(String basePackge) {
        this.basePackge = basePackge;
    }

    /**
     * 在应用程序上下文的标准初始化之后修改其内部bean定义注册表。所有常规bean定义都已加载，但还没有实例化bean。这允许在下一个后处理阶段开始之前添加更多的bean定义
     * @param registry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        InterfaceScanner scanner=new InterfaceScanner(registry);
        scanner.scan(basePackge);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
