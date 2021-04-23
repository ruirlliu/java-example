package example.framework.spring.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * 普通bean 的名称不能以 & 符号开头
 * @author lr
 * @date 2021/4/22
 */
//@Component(BeanFactory.FACTORY_BEAN_PREFIX + "beanNameTest")
public class BeanNameTest implements BeanNameAware {

    private String beanName;

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }
}
