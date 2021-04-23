package example.framework.spring;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lr
 * @date 2021/4/13
 */
@Component
// 多例不会执行调用
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SmartInitializingSingletonTest implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println(SmartInitializingSingletonTest.class.getSimpleName() + "#afterSingletonsInstantiated");
    }

}
