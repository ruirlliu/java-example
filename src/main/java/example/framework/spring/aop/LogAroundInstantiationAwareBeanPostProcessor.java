package example.framework.spring.aop;

import example.framework.spring.aop.test.AopDemo;
import example.framework.spring.aop.test.AopDemo1;
import example.framework.spring.aop.test.AopDemo2;
import example.framework.spring.aop.test.IAopDemo;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * 描述: Bean实例化后置处理器<br>
 *
 * Spring 事务原本以为是通过某种 TransactionalInstantiationAwareBeanPostProcessor 通过 postProcessBeforeInstantiation 方法返回一个代理对象实现。
 * 其实 Spring 只通过一个 InfrastructureAdvisorAutoProxyCreator 后置处理器进行处理
 * 查找到bean实例的所有 Advisor bean，进行代理。
 *
 * 注解 @Aspect 实现代理的原理相同
 *
 * @see org.springframework.transaction.annotation.EnableTransactionManagement
 * @see org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration
 * @see org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2021/8/9 14:12
 */
//@Component
public class LogAroundInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

	LogAroundPointcutAdvisor advisor = new LogAroundPointcutAdvisor();

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (AopUtils.canApply(advisor, beanClass)) {
			//
//			final NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor = new NameMatchMethodPointcutAdvisor();
//			nameMatchMethodPointcutAdvisor.setMappedName("foo");
//			nameMatchMethodPointcutAdvisor.setAdvice(advice);

			final ProxyFactory proxyFactory = new ProxyFactory();
			proxyFactory.addAdvisor(advisor);

			Object instance = BeanUtils.instantiateClass(beanClass);
			proxyFactory.setTarget(instance);
			return proxyFactory.getProxy();
		}
		return null;
	}

	public static void main(String[] args) {
		boolean b1 = AopUtils.canApply(new LogAroundPointcutAdvisor(), AopDemo1.class);
		boolean b2 = AopUtils.canApply(new LogAroundPointcutAdvisor(), AopDemo2.class);


		final ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.addAdvisor(new LogAroundPointcutAdvisor());

		Object instance = BeanUtils.instantiateClass(AopDemo.class);
		proxyFactory.setTarget(instance);
		proxyFactory.setInterfaces(IAopDemo.class);
		Object proxy = proxyFactory.getProxy();
		System.out.println(proxyFactory.toProxyConfigString());
		System.out.println(proxyFactory.toString());
		System.out.println(proxy.getClass().getName());
	}
}
