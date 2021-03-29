package com.example.utils.lang3;

import org.apache.commons.lang3.ArchUtils;
import org.apache.commons.lang3.arch.Processor;

import java.util.Properties;

/**
 * @author lr
 * @date 2020/12/2
 */
public class Demo {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        properties.forEach((x,y) -> System.out.println(x + " = " + y));
        Processor processor = ArchUtils.getProcessor();
        System.out.println(processor);
        System.out.println();
    }
}
