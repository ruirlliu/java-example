package example.framework.spring.aop;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;

/**
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2021/8/9 14:43
 * @see org.springframework.transaction.interceptor.TransactionAttributeSourcePointcut
 */
public class LogAroundPointcut extends StaticMethodMatcherPointcut {

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return AnnotatedElementUtils.hasAnnotation(targetClass, LogAround.class) ||
				AnnotatedElementUtils.hasAnnotation(method, LogAround.class);
	}

}
