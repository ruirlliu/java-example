package example.framework.spring.loader;

import example.DemoApplication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 * @author LR<br>
 * @date 2021/7/22 15:57
 */
public class BeanDefinitionLoaderDemo {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class<?> loadClass = ClassUtils.forName("org.springframework.boot.BeanDefinitionLoader", ClassUtils.getDefaultClassLoader());
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		Class<?> source = DemoApplication.class;
		Constructor<?> constructor = ReflectionUtils.accessibleConstructor(loadClass, BeanDefinitionRegistry.class, Object[].class);
		Object loader = BeanUtils.instantiateClass(constructor, beanFactory, new Object[]{source});
		Method loadMethod = loadClass.getDeclaredMethod("load");
		ReflectionUtils.makeAccessible(loadMethod);
		loadMethod.invoke(loader);
		int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
		System.out.println("beanDefinitionCount=" + beanDefinitionCount);
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		System.out.println("beanDefinitionNames=" + Arrays.toString(beanDefinitionNames));
	}
}
