package example.framework.spring.aop;

import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;

/**
 * 描述:TOTO 请补全模块<br>
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
		PointcutAdvisor pointcutAdvisor = new PointcutAdvisorDemo(AspectDemo.class);

		final ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.addAdvisor(pointcutAdvisor);

		AopDemo demo = new AopDemo();
		proxyFactory.setTarget(demo);

		final AopDemo demoProxy = (AopDemo) proxyFactory.getProxy();
		int sum = demoProxy.sum(1, 3);
	}

}
