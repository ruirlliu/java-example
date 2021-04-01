package example.framework.spring;

import example.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lr
 * @date 2021/3/31
 */
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class AwareComponentTest {

    @Autowired
    private AwareComponent awareComponent;

    @Test
    public void testAware() {
        String beanName = awareComponent.getBeanName();
        System.out.println(beanName);
    }
}