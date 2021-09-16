package example.framework.spring.aop.test;

import example.framework.spring.aop.LogAround;
import org.springframework.stereotype.Component;

@Component
public class AopDemo implements IAopDemo {

//	@LogAround
	public int sum(int i1, int i2) {
		System.out.println("sum :: " + this.getClass().getName());
		return i1 + i2;
	}

//	@LogAround
	public int sum2(int i1, int i2) {
		System.out.println("sum2 :: " + this.getClass().getName());
		return sum2Inner(i1, i2);
	}

	private int sum2Inner(int i1, int i2) {
		System.out.println("sum2Inner :: " + this.getClass().getName());
		return i1 + i2;
	}



}
