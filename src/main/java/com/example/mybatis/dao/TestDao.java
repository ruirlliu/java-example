package com.example.mybatis.dao;

import com.example.mybatis.entity.Human;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lr
 * @date 2021/3/16
 */

public interface TestDao {

//    @Select("select * from human")
    // 不能和xml重复出现，否则会报错，configuration类中 mappedStatements 变量
    List<Human> list();

}
