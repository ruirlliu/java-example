package example.jdk.cache.lru;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.utils.JsonUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lr
 * @date 2021/3/12
 */
class LRUTest {


    public static void main(String[] args) throws JsonProcessingException {


        Map<String, Object> map = new HashMap<>();
        map.put("test01", 1);
        map.put("test02", 2);
        map.put("test03", 3);

        ObjectMapper mapper = new ObjectMapper();
        Map<Object, Object> map1 = JsonUtils.toJson(JsonUtils.toJsonStr(map), Map.class);

        System.out.println(map1);

        System.out.println(map1 instanceof LinkedHashMap);

    }


}