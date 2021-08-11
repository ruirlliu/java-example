package example.framework.spring.aop.test;

import example.framework.spring.aop.LogAround;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2021/8/9 14:18
 */
@Component
public class AopDemo2 {

	@LogAround
	public int sum(int i1, int i2) {
		int i = i1 + i2;
		return i;
	}

	@LogAround
	public static int sumWithOut(int i1, int i2) {
		int i = i1 + i2;
		System.out.println("sun value is :: " + i);
		return i;
	}

	public static void main(String[] args) {
		Method[] allDeclaredMethods = ReflectionUtils.getAllDeclaredMethods(AopDemo2.class);
		for (Method allDeclaredMethod : allDeclaredMethods) {
			System.out.println(allDeclaredMethod.getName());
		}
	}

}
