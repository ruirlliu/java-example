package com.example.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author lr
 * @date 2021/3/23
 */
@Component("testAwareComponentName")
public class AwareComponent implements BeanFactoryAware, BeanNameAware {

    static BeanFactory factory;

    static String beanName;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        factory = beanFactory;
    }


    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    public static BeanFactory getBeanFactory() {
        return factory;
    }

    public static String getBeanName() {
        return beanName;
    }

}
