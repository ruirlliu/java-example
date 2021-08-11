package example.framework.spring.aop.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AopDemo1Test {

	@Autowired
	private AopDemo1 demo1;

	@Autowired
	private AopDemo2 demo2;

	@Test
	public void testLogAround() {
		System.out.println("##################");
		int sum1 = demo1.sum(1, 2);
		System.out.println("AopDemo1 sum is : " + sum1);
		System.out.println("***************");
		int sum2 = demo2.sum(1, 3);
		System.out.println("AopDemo2 sum is : " + sum2);
		int i = demo2.sumWithOut(1, 4);
		System.out.println("AopDemo2 static sum is : " + i);
		System.out.println("##################");
	}

}