package example.framework.spring;

import example.framework.spring.aware.AliasAware;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lr
 * @date 2021/3/23
 */
@Component("testAwareComponentName,m1,m2")
@Order(Ordered.LOWEST_PRECEDENCE)
public class AwareComponent implements BeanNameAware, ApplicationContextAware, AliasAware {

    private String beanName;

    private ApplicationContext context;

    private String[] alias;

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    public  String getBeanName() {
        return beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public String[] getAlias() {
        return alias;
    }
}
