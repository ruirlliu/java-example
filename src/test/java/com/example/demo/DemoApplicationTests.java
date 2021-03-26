package com.example.demo;

import com.example.DemoApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.type.StandardAnnotationMetadata;

import java.util.Set;

//@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testAnnotationMetadata() {
        StandardAnnotationMetadata annotationMetadata = new StandardAnnotationMetadata(DemoApplication.class);
        Set<String> annotationTypes = annotationMetadata.getAnnotationTypes();
        System.out.println(annotationTypes);
        System.out.println(annotationMetadata.isIndependent());
    }

    @Test
    public void testIsIndependent() {
        StandardAnnotationMetadata test01 = new StandardAnnotationMetadata(Test01.class);
        StandardAnnotationMetadata test02 = new StandardAnnotationMetadata(Test02.class);
        StandardAnnotationMetadata test03 = new StandardAnnotationMetadata(new Runnable() {

            @Override
            public void run() {

            }
        }.getClass());
        System.out.println(test01.isIndependent());
        System.out.println(test02.isIndependent());
        System.out.println(test03.isIndependent());
    }

    static class Test01 {

    }

    class Test02 {
    }

}
