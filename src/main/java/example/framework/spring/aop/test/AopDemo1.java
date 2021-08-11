package example.framework.spring.aop.test;

import example.framework.spring.aop.LogAround;
import org.springframework.stereotype.Component;

/**
 *
 * @author LR<br>
 * @date 2021/7/21 10:31
 */
@LogAround
@Component
public class AopDemo1 {

	public int sum(int i1, int i2) {
		return  i1 + i2;
	}

}
