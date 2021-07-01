package example.framework.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 注册别名
 * @author lr
 * @date 2021/4/19
 */
@Component
@Slf4j
public class BeanAliasAwareProcessor implements BeanFactoryAware, BeanPostProcessor {

    private ConfigurableListableBeanFactory beanFactory;

    private final AtomicInteger integer = new AtomicInteger(0);

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (beanFactory instanceof ConfigurableListableBeanFactory) {
            this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
        } else {
            log.error("BeanFactory in ApplicationContext not support alias");
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        beanFactory.registerAlias(beanName, "xxxxx-" + integer.addAndGet(1));
        return bean;
    }

}
