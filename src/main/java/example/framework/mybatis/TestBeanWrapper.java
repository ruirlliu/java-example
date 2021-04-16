package example.framework.mybatis;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 内省
 * @author lr
 * @date 2021/3/15
 */
public class TestBeanWrapper {

    public static void main(String[] args) throws IntrospectionException {
        testIntrospector();
    }

    static void testIntrospector() throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(SqlSessionFactoryBean.class);
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println(beanDescriptor.getName());
        System.out.println(beanDescriptor.getDisplayName());

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Method readMethod = propertyDescriptor.getReadMethod();
            System.out.println("readMethod: " + readMethod);
            Method writeMethod = propertyDescriptor.getWriteMethod();
            System.out.println("writeMethod: " + writeMethod);
        }

    }

    static void testBeanWrapper() {
        Set<String> factoryPropertyNames = Stream
                .of(new BeanWrapperImpl(SqlSessionFactoryBean.class).getPropertyDescriptors()).map(PropertyDescriptor::getName)
                .collect(Collectors.toSet());

        System.out.println(factoryPropertyNames);


        PropertyDescriptor[] propertyDescriptors = new BeanWrapperImpl(SqlSessionFactoryBean.class).getPropertyDescriptors();
        System.out.println(propertyDescriptors);
    }

}
