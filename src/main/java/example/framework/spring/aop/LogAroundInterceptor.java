package example.framework.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * @see org.springframework.transaction.interceptor.TransactionInterceptor
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2021/8/9 14:02
 */
public class LogAroundInterceptor implements MethodInterceptor {


	@Override
	public Object invoke( MethodInvocation invocation) throws Throwable {
		System.out.println("before method invoke log....");
		Method method = invocation.getMethod();
		Object[] args = invocation.getArguments();
		Object target = invocation.getThis();
		Object invoke = method.invoke(target, args);
		System.out.println("after method invoke log....");
		Map<String, Object> map = ResolveUtils.parameterAttribute(args, method);
		System.out.println(map);
		return invoke;
	}
}
