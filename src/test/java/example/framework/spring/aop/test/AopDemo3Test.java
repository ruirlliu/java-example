package example.framework.spring.aop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AopDemo3Test {

	@Autowired
	private AopDemo3 aopDemo3;

	@Test
	public void test() {
		Map<String, String> map = new HashMap<>();
		map.put("test1", "test1");
		map.put("test2", "test2");
		Entity entity = new Entity();
		entity.setP1(89);
		entity.setP2(125);
		entity.setP3("p3");
		int test = aopDemo3.testParameter(14, "test", map, entity);

	}

}