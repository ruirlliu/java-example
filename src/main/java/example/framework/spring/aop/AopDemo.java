package example.framework.spring.aop;

/**
 * 描述:TOTO 请补全模块<br>
 *
 * @author LR<br>
 * @date 2021/7/21 10:31
 */
@AspectDemo
public class AopDemo {

	public int sum(int i1, int i2) {
		int i = i1 + i2;
		System.out.println("sum value is :" + i);
		return i;
	}

}
