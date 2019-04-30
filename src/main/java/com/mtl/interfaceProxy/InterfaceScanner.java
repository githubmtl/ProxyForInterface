package com.mtl.interfaceProxy;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Set;

/**
 * 说明:用于扫描给定包名下的接口
 *
 * @作者 莫天龙
 * @时间 2019/04/29 16:34
 */
public class InterfaceScanner extends ClassPathBeanDefinitionScanner {
    public InterfaceScanner(BeanDefinitionRegistry registry) {
        //registry是Spring的Bean注册中心
        // false表示不使用ClassPathBeanDefinitionScanner默认的TypeFilter
        // 默认的TypeFilter只会扫描带有@Service,@Controller，@Repository，@Component注解的类
        super(registry,false);
    }

    //调用父类执行扫描
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        addFilter();
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if (beanDefinitionHolders.isEmpty()){
            System.err.println("No Interface Found!");
        }else{
            //创建代理对象
            createBeanDefinition(beanDefinitionHolders);
        }
        return beanDefinitionHolders;
    }

    //只扫描顶级接口
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        AnnotationMetadata metadata = beanDefinition.getMetadata();
        return metadata.isInterface()&&metadata.isIndependent();
    }

    /**
     * 扫描所有类
     */
    private void addFilter(){
        addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });
    }

    /**
     * 为扫描到的接口创建代理对象
     * @param beanDefinitionHolders
     */
    private void createBeanDefinition(Set<BeanDefinitionHolder> beanDefinitionHolders){
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            GenericBeanDefinition beanDefinition=((GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition());
            //将bean的真实类型改变为FactoryBean
            beanDefinition.getConstructorArgumentValues().
                    addGenericArgumentValue(beanDefinition.getBeanClassName());
            beanDefinition.setBeanClass(ProxyFactoryBean.class);
            beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        }
    }
}
