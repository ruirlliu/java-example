package example.framework.spring;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @author lr
 * @date 2021/3/23
 */
@Component("testAwareComponentName")
public class AwareComponent implements BeanNameAware {

    private String beanName;

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    public  String getBeanName() {
        return beanName;
    }
}
