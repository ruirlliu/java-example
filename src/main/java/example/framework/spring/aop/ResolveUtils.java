package example.framework.spring.aop;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;


public class ResolveUtils {

	@SuppressWarnings("rawtypes,unchecked")
	public static JSONObject parameterAttribute(Object[] args, Method method) {
		JSONObject attribute = new JSONObject((int) (args.length / 0.75f) + 1, true);
		String[] parasName = Arrays.stream(method.getParameters()).map(Parameter::getName).toArray(String[]::new);
		//				attributeCache.computeIfAbsent(new MethodClassKey(method, targetClass),
		//				k -> Arrays.stream(method.getParameters()).map(Parameter::getName).toArray(String[]::new));
		if (args.length != parasName.length) {
			throw new RuntimeException("参数个数不匹配");
		}
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof String || args[i] instanceof Integer) {
				attribute.put(parasName[i], args[i]);
			} else if (args[i] instanceof Map) {
				for (Map.Entry entry : (Set<Map.Entry>) ((Map) args[i]).entrySet()) {
					attribute.put(entry.getKey().toString(), entry.getValue());
				}
			} else {
				JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(args[i]));
				attribute.putAll(jsonObject);
			}
		}
		return attribute;
	}

	//	private final static Map<Object, String[]> attributeCache = new ConcurrentHashMap<>(32);

}
