package example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 描述:TOTO 请补全模块<br>
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2022/7/22 17:28
 */
public class JsonUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String toJsonStr(Object obj) {
		try {
			return objectMapper.writer().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}


	public static <T> T toJson(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.reader().readValue(jsonStr, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
