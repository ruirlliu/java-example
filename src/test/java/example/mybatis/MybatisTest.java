package example.mybatis;

import example.DemoApplication;
import example.framework.mybatis.dao.HumanDao;
import example.framework.mybatis.entity.Human;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lr
 * @date 2021/3/17
 */
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MybatisTest {

    @Autowired
    private HumanDao dao;

    @Test
    public void test() throws InterruptedException {

        List<Human> list = dao.list();
        System.out.println(list);

        System.out.println("sleep...");
        Thread.sleep(TimeUnit.SECONDS.toMillis(10));
        System.out.println("2nd select...");
        System.out.println(dao.list());
    }


}
