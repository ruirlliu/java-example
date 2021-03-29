package com.harbor.client.v1.op;

import cn.hutool.core.codec.Base64;
import com.harbor.client.data.Project;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author lr
 * @date 2021/2/5
 */
public class HarborProjectTest {

    public static void main(String[] args) {

        String auth = Base64.encode("admin:Harbor123456");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + auth);
        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<List<Project>> exchange = new RestTemplate().exchange("http://192.168.1.72:30000/api/v2.0/projects", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Project>>() {
        });

        System.out.println(exchange.getBody());

        test();
    }

    public static void test() throws RestClientException {

    }

}