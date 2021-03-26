package com.example.framework.mybatis;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lr
 * @date 2021/3/15
 */
public class TestBeanWrapper {

    public static void main(String[] args) {
        Set<String> factoryPropertyNames = Stream
                .of(new BeanWrapperImpl(SqlSessionFactoryBean.class).getPropertyDescriptors()).map(PropertyDescriptor::getName)
                .collect(Collectors.toSet());

        System.out.println(factoryPropertyNames);


        PropertyDescriptor[] propertyDescriptors = new BeanWrapperImpl(SqlSessionFactoryBean.class).getPropertyDescriptors();
        System.out.println(propertyDescriptors);

    }

}
