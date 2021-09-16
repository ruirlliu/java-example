package example.framework.spring.factories;


import org.springframework.context.ApplicationListener;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;

import java.util.List;

public class SpringFactoriesDemo {

	public static void main(String[] args) {
		List<String> strings = SpringFactoriesLoader.loadFactoryNames(ApplicationListener.class, ClassUtils.getDefaultClassLoader());
		for (String string : strings) {
			System.out.println(string);
		}
	}

}
