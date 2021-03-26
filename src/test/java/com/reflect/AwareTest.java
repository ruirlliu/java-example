package com.reflect;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author lr
 * @date 2021/3/23
 */
@Component
public class AwareTest implements BeanFactoryAware, BeanNameAware {

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

    static BeanFactory getBeanFactory() {
        return factory;
    }

    static String getBeanName() {
        return beanName;
    }

}
