package com.example.demo.rest;

import org.springframework.web.client.RestTemplate;

/**
 * @author lr
 * @date 2021/3/3
 */
public class RestTemplateDemo {



    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://www.sina.cn", String.class);
        System.out.println(forObject);

    }

}
