package example.framework.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * @author lr
 * @date 2021/4/7
 */
@Component
public class BeanPostProcessorImplTest implements BeanPostProcessor, PriorityOrdered {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (check(bean))
            System.out.println(BeanPostProcessorImplTest.class.getSimpleName() + " :: " + bean.getClass().getSimpleName() + "#postProcessBeforeInitialization");
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (check(bean))
            System.out.println(BeanPostProcessorImplTest.class.getSimpleName() + " :: " + bean.getClass().getSimpleName() + "#postProcessAfterInitialization");
        return bean;
    }

    private boolean check(Object bean) {
        return bean instanceof InitializingBeanImplTest || bean instanceof InitializingBeanImplTest02;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
