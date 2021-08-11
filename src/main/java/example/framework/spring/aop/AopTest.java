package example.framework.spring.aop;

import example.framework.spring.aop.test.AopDemo1;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;

/**
 *
 * @author LR<br>
 * @date 2021/7/21 10:31
 */
public class AopTest {


	public static void main(String[] args) {

//
//			final NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor = new NameMatchMethodPointcutAdvisor();
//			nameMatchMethodPointcutAdvisor.setMappedName("foo");
//			nameMatchMethodPointcutAdvisor.setAdvice(advice);
		PointcutAdvisor pointcutAdvisor = new LogAroundPointcutAdvisor();

		final ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.addAdvisor(pointcutAdvisor);

		AopDemo1 demo = new AopDemo1();
		proxyFactory.setTarget(demo);

		final AopDemo1 demoProxy = (AopDemo1) proxyFactory.getProxy();
		int sum = demoProxy.sum(1, 3);
	}

}
