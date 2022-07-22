package example.framework.spring.aop;

import example.utils.JsonUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class ResolveUtils {

	@SuppressWarnings("rawtypes,unchecked")
	public static Map<String, Object> parameterAttribute(Object[] args, Method method) {
		Map<String, Object> attribute = new LinkedHashMap<>((int) (args.length / 0.75f) + 1);
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
				String jsonStr = JsonUtils.toJsonStr(args[i]);
				Map<String, Object> arg = JsonUtils.toJson(jsonStr, Map.class);
				attribute.putAll(arg);
			}
		}
		return attribute;
	}

	//	private final static Map<Object, String[]> attributeCache = new ConcurrentHashMap<>(32);

}
