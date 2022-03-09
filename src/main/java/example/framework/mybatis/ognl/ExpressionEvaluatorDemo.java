package example.framework.mybatis.ognl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.ibatis.scripting.xmltags.ExpressionEvaluator;

import java.util.ArrayList;

/**
 * 描述: 测试mybatis动态SQL<br>
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2022/3/9 16:27
 * @see org.apache.ibatis.scripting.xmltags.ExpressionEvaluator
 */
public class ExpressionEvaluatorDemo {

	private static final ExpressionEvaluator EVALUATOR = new ExpressionEvaluator();

	public static void main(String[] args) {
		ExpressionEvaluatorDemo demo = new ExpressionEvaluatorDemo();
		ImmutableMap<String, ArrayList<String>> types = ImmutableMap.of("types", Lists.newArrayList("1", "2"));
		boolean size = demo.testIf("types != null and types.size > 0", types);
		System.out.println("testIf :: " + size);
		boolean contains = demo.testIf("types != null and types.contains('1'.toString) > 0", types);
		System.out.println("testIf :: " + contains);
	}

	public boolean testIf(String express, Object param) {
		return EVALUATOR.evaluateBoolean(express, param);
	}

}
