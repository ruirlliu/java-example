package com.example.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lr
 * @date 2021/3/12
 */
@RestController
public class RedirectDemo {

    @GetMapping("/0312test")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://www.baidu.com");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = RedirectDemo.class.getClassLoader().loadClass("com.example.rest.graphql.v1.GraphQLDemo1");
        System.out.println(aClass.getName());
    }

}
