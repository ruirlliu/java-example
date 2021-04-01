package example.jdk.cache.lru;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        Map<Object, Object> map1 = mapper.readValue(JSONObject.toJSONString(map), Map.class);

        System.out.println(map1);

        System.out.println(map1 instanceof LinkedHashMap);

    }


}