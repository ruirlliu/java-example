package example.framework.spring.transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class HumanService01Test {

	@Autowired
	private HumanService01 service;

	@Test
	public void testUpdate() throws Exception {
		service.update();
	}





}