package example.jdk.reflect.method;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 *
 * @author LR<br>
 * @date 2021/7/13 19:31
 */
public class MethodReflectDemo {

	public static void main(String[] args) throws NoSuchMethodException {

		Method[] declaredMethods = BridgeDemoImpl.class.getDeclaredMethods();
		for (Method declaredMethod : declaredMethods) {
			System.out.println("name: " + declaredMethod.getName() + "; params: " + Arrays.toString(declaredMethod.getParameterTypes()) + "; isBridge: " + declaredMethod.isBridge());
		}

		for (Method declaredMethod : EnumDemo.class.getDeclaredMethods()) {
			System.out.println("name: " + declaredMethod.getName() + "; params: " + Arrays.toString(declaredMethod.getParameterTypes()) + "; " + declaredMethod.isBridge() + ";"  + declaredMethod.isSynthetic());

		}

//		MethodReflectDemo demo = new MethodReflectDemo();
//		Method someThing = BridgeDemoImpl.class.getDeclaredMethod("someThing", Integer.class);
//		System.out.println(demo.methodIsBridge(someThing));
	}

	public boolean methodIsBridge(Method method) {
		return method.isBridge();
	}

	enum EnumDemo {
		ONE,
		TWO
	}

}

