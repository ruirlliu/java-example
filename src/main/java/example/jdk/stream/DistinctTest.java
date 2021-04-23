package example.jdk.stream;

import example.jdk.stream.entity.BaseEntity;
import example.jdk.stream.entity.EqualsEntity;
import example.jdk.stream.entity.FullEntity;
import example.jdk.stream.entity.HashCodeEntity;

import java.util.ArrayList;

/**
 * stream 中的distinct给自定义对象去重
 * @author lr
 * @date 2021/4/22
 */
public class DistinctTest {

    public static void main(String[] args) {

        ArrayList<BaseEntity> list = new ArrayList<>();

        list.add(new BaseEntity("TEST01", "VALUE01"));
        list.add(new BaseEntity("TEST01", "VALUE01"));
        list.add(new BaseEntity("TEST02", "VALUE02"));
        list.add(new BaseEntity("TEST03", "VALUE03"));

        System.out.println("base.........");
        list.stream().distinct().forEach(System.out::println);
        System.out.println("hashCode.........");
        list.stream().map(HashCodeEntity::valueOf).distinct().forEach(System.out::println);
        System.out.println("equals.........");
        list.stream().map(EqualsEntity::valueOf).distinct().forEach(System.out::println);
        System.out.println("hashCode and equals.........");
        list.stream().map(FullEntity::valueOf).distinct().forEach(System.out::println);
    }

}
