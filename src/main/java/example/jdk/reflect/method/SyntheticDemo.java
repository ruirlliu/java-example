package example.jdk.reflect.method;

import java.lang.reflect.Method;

/**
 *
 * @author LR<br>
 * @date 2021/7/14 9:59
 */
public class SyntheticDemo {

	private String param1 = "1";

	private String param2 = "2";

	public void printMethod() {
		System.out.println("this is printMethod");
	}


	public class SubClass {

		private String subParam1;

		private String subParam2;

		public void printMethodSub() {
			System.out.println("this is printMethodSub");
		}
	}


	public void isSynthetic() {
		SubClass subClass = new SubClass();
		System.out.println(subClass.subParam1);
		System.out.println(subClass.subParam2);

		for (Method method : subClass.getClass().getDeclaredMethods()) {
			System.out.println("class name:" + subClass.getClass().getName() + "-" + method.getName() + ":" + method.isSynthetic());
		}

	}

	public static void main(String[] args) {
//		SyntheticDemo demo = new SyntheticDemo();
//		demo.isSynthetic();
		System.out.println("*********");
		Class<SubClass> subClassClass = SubClass.class;
		for (Method declaredMethod : subClassClass.getDeclaredMethods()) {
			System.out.println(declaredMethod.getName() + ":" + declaredMethod.isSynthetic());
		}
	}
}
