package com;

import example.DemoApplication;
import example.framework.mybatis.dao.TestDao;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * @author lr
 * @date 2021/3/16
 */
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class BeanTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private SqlSessionFactory factory;

    @Autowired
    private SqlSessionTemplate template;

    @Test
    public void testBeanExist() {
        Map<String, MapperFactoryBean> beansOfType = context.getBeansOfType(MapperFactoryBean.class);
//        MapperFactoryBean bean = context.getBean(MapperFactoryBean.class);
        MapperScannerConfigurer bean1 = context.getBean(MapperScannerConfigurer.class);
//        System.out.println(bean);
        System.out.println(beansOfType);
        System.out.println(bean1);

        Map<String, MapperScannerConfigurer> type = context.getBeansOfType(MapperScannerConfigurer.class);
        System.out.println(type.keySet());

        TestDao bean = context.getBean(TestDao.class);
        System.out.println(bean.toString());
    }


    @Test
    public void testField() throws IllegalAccessException {
        Configuration configuration = factory.getConfiguration();
        Field[] declaredFields = configuration.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getName().equals("loadedResources")) {
                declaredField.setAccessible(true);
                String s = declaredField.toString();
                System.out.println(s);
                // 利用反射获取值
                @SuppressWarnings("unchecked")
                Set<String> o = (Set<String>) declaredField.get(configuration);
                System.out.println("**********");
                System.out.println(o);
                o.forEach(System.out::println);
            }
        }
    }

    @Test
    public void testAware() {
//        BeanFactory beanFactory = AwareComponent.getBeanFactory();
//        String beanName = AwareComponent.getBeanName();
//        System.out.println(beanFactory);
//        System.out.println(beanFactory.getClass().getName());
//        System.out.println(beanName);

//        ObjectProvider<System> beanProvider = beanFactory.getBeanProvider(System.class);
//        System.out.println(beanProvider.getIfAvailable());
//
//        ObjectProvider<System> beanProvider1 = beanFactory.getBeanProvider(System.class);
//
//        List<String> strings = AutoConfigurationPackages.get(beanFactory);
//        System.out.println(strings);
//        boolean has = AutoConfigurationPackages.has(beanFactory);
//        System.out.println(has);
    }

}
