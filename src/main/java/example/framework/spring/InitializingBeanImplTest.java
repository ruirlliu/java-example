package example.framework.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 测试执行顺序
 * @author lr
 * @date 2021/4/7
 */

/**
 * 构造函数 -> BeanPostProcessor#postProcessBeforeInitialization -> @PostConstruct -> InitializingBean#afterPropertiesSet -> BeanPostProcessor#postProcessAfterInitialization
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 1000)
//@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class InitializingBeanImplTest implements InitializingBean {

    // 构造函数
    public InitializingBeanImplTest() {
        System.out.println(InitializingBeanImplTest.class.getSimpleName() + "#Constructor");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println(InitializingBeanImplTest.class.getSimpleName() + " :: " + InitializingBean.class.getSimpleName() + "#afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println(InitializingBeanImplTest.class.getSimpleName() + " :: " + PostConstruct.class.getSimpleName());
    }
}
