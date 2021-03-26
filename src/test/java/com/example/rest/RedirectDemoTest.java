package com.example.rest;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author lr
 * @date 2021/3/12
 */
public class RedirectDemoTest {

    public static void main(String[] args) throws Exception {
        ResultActions perform = MockMvcBuilders.webAppContextSetup(new AnnotationConfigWebApplicationContext())
                .build()
                .perform(
                        MockMvcRequestBuilders
                                .get("/0312test")
                );
        System.out.println(perform.andDo(MockMvcResultHandlers.print()));
    }

}