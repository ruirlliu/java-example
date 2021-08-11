package example.framework.mybatis.dao;

import example.framework.mybatis.entity.Human;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lr
 * @date 2021/3/16
 */

public interface HumanDao {

//    @Select("select * from human")
    // 不能和xml重复出现，否则会报错，configuration类中 mappedStatements 变量
    List<Human> list();

    // Mybatis Dao 层 方法重载 start
    // 动态sql中包含了 id 和 name 的 if 标签判断查询
    // https://snailclimb.gitee.io/javaguide/#/docs/system-design/framework/mybatis/mybatis-interview?id=_3%e3%80%81%e6%9c%80%e4%bd%b3%e5%ae%9e%e8%b7%b5%e4%b8%ad%ef%bc%8c%e9%80%9a%e5%b8%b8%e4%b8%80%e4%b8%aa-xml-%e6%98%a0%e5%b0%84%e6%96%87%e4%bb%b6%ef%bc%8c%e9%83%bd%e4%bc%9a%e5%86%99%e4%b8%80%e4%b8%aa-dao-%e6%8e%a5%e5%8f%a3%e4%b8%8e%e4%b9%8b%e5%af%b9%e5%ba%94%ef%bc%8c%e8%af%b7%e9%97%ae%ef%bc%8c%e8%bf%99%e4%b8%aa-dao-%e6%8e%a5%e5%8f%a3%e7%9a%84%e5%b7%a5%e4%bd%9c%e5%8e%9f%e7%90%86%e6%98%af%e4%bb%80%e4%b9%88%ef%bc%9fdao-%e6%8e%a5%e5%8f%a3%e9%87%8c%e7%9a%84%e6%96%b9%e6%b3%95%ef%bc%8c%e5%8f%82%e6%95%b0%e4%b8%8d%e5%90%8c%e6%97%b6%ef%bc%8c%e6%96%b9%e6%b3%95%e8%83%bd%e9%87%8d%e8%bd%bd%e5%90%97%ef%bc%9f

    // 无参，可查询全部
    List<Human> listBy();

    // 报错，提示 没有 name 这个参数
    List<Human> listBy(@Param("id") Integer id);

    // 报错，提示 没有 id 这个参数
    List<Human> listBy(@Param("name") String name);

    // 两个参数，可运行过滤查询
    List<Human> listBy(@Param("id") Integer id, @Param("name") String name);
    // Mybatis Dao 层方法重载 end









    int deleteByPrimaryKey(Integer id);

    int insert(Human record);

    int insertSelective(Human record);

    Human selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Human record);

    int updateByPrimaryKey(Human record);

}
