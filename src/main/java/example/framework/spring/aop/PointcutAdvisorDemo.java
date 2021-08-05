package example.framework.spring.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 描述: Aspect 由 Advice 定义增强行为， Pointcut 定义何处执行；
 * Spring 通过 Advisor 把这两个组合了起来；
 * 更具体的就是这个 PointcutAdvisor，可以很方便把 Pointcut 和 Advice 一起管理
 *
 * @author LR<br>
 * @date 2021/7/21 10:18
 * https://stackoverflow.com/questions/25092302/what-is-the-difference-between-advisor-and-aspect-in-aop
 */
public class PointcutAdvisorDemo extends AbstractPointcutAdvisor {

	private Class<? extends Annotation> annotationType;

	public PointcutAdvisorDemo(Class<? extends Annotation> annotationType) {
		this.annotationType = annotationType;
	}

	@Override
	public Pointcut getPointcut() {
		return new AnnotationMatchingPointcut(annotationType);
	}

	@Override
	public Advice getAdvice() {
		return new MethodBeforeAdvice() {
			@Override
			public void before(Method method, Object[] args, Object target) throws Throwable {
				System.out.println("before... ");
			}
		};
	}

	@Override
	public boolean isPerInstance() {
		return false;
	}

}
