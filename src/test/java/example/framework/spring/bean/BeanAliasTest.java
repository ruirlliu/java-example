package example.framework.spring.bean;

import example.framework.spring.aware.AwareComponent;
import example.framework.spring.controller.ValidateController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@SpringBootTest
@RunWith(SpringRunner.class)
public class BeanAliasTest {

    @Autowired
    private AwareComponent awareComponent;

    @Autowired
    private ValidateController controller;

    @Autowired
    private ApplicationContext context;

    @Test
    public void test() {
        System.out.println(Arrays.toString(awareComponent.getAlias()));
        System.out.println(Arrays.toString(controller.getAlias()));
    }

}