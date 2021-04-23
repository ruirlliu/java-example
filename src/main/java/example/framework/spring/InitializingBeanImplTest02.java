package example.framework.spring;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author lr
 * @date 2021/4/13
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class InitializingBeanImplTest02 implements InitializingBean {

    // 构造函数
    public InitializingBeanImplTest02() {
        System.out.println(this.getClass().getSimpleName() + "#Constructor");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println(this.getClass().getSimpleName() + " :: " + InitializingBean.class.getSimpleName() + "#afterPropertiesSet");
    }

    @PostConstruct
    public void init() {
        System.out.println(this.getClass().getSimpleName() + " :: " + PostConstruct.class.getSimpleName());
    }
}
