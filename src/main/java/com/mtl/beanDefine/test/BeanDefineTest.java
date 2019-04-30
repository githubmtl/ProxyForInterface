package com.mtl.beanDefine.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;

/**
 * 说明:测试BeanDefinitionRegistryPostProcessor接口，自定义添加bean到容器
 *
 * @作者 莫天龙
 * @时间 2019/04/30 9:29
 */
public class BeanDefineTest implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //创建beanDefinition构建器
        BeanDefinitionBuilder beanDefinitionBuilder=BeanDefinitionBuilder.genericBeanDefinition(Person.class);
        //获取到创建beanDefinition
        GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionBuilder.getBeanDefinition();
        //通过此方法可以将age属性的值设置为23,就像xml配置中的property标签
        beanDefinition.getPropertyValues().add("age", new Integer(23));
        beanDefinition.getPropertyValues().add("name", "Mike");
        //设置bean的主动注入类型为根据type注入
        beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        //最后调用注入方法
        registry.registerBeanDefinition("person", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //容器后处理器，暂不处理
    }
}
