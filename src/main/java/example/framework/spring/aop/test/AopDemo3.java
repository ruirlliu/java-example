
package example.framework.spring.aop.test;

import example.framework.spring.aop.LogAround;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class AopDemo3 {

	@LogAround
	public int testParameter(Integer i, String str, Map<String, String> params, Entity entity) {
		return 1;
	}

}
