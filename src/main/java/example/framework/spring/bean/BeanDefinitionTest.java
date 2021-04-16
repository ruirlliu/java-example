package example.framework.spring.bean;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * @see org.springframework.context.annotation.Bean
 * @author liurui
 * @date 2021/4/9
 */
public class BeanDefinitionTest {

    private String test01;

    private String test02;

    public static void main(String[] args) {

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(BeanDefinitionTest.class);
        builder.addPropertyValue("test01", "test01");
        builder.addPropertyValue("test02", "test02");
        // @Autowired
        builder.setAutowireMode(Autowire.BY_TYPE.value());
        // @Lazy
        builder.setLazyInit(false);
        // @Primary
        builder.setPrimary(true);
        // @Scope
        builder.setScope(BeanDefinition.SCOPE_SINGLETON);
        // @Role
        builder.setRole(BeanDefinition.ROLE_APPLICATION);
        builder.setInitMethodName("test");
        // @DependsOn
        builder.addDependsOn("testAwareComponentName");

        BeanDefinition beanDefinition = builder.getBeanDefinition();

        boolean singleton = beanDefinition.isSingleton();

        boolean prototype = beanDefinition.isPrototype();

        BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(beanDefinition, "beanDefinitionTest", new String[]{"test01", "test02"});
    }

}
