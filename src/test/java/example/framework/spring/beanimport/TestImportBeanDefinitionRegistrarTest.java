package example.framework.spring.beanimport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TestImportBeanDefinitionRegistrarTest {

	@Autowired
	BeanFactory beanFactory;

	@Test
	public void test() {
		TestImportBean bean = beanFactory.getBean(TestImportBean.class);
		bean.identify();
	}

}