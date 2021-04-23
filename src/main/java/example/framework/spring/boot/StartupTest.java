package example.framework.spring.boot;

import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.metrics.StartupStep;

/**
 * @author lr
 * @date 2021/4/20
 */
public class StartupTest {

    public static void main(String[] args) {

        StartupStep test = ApplicationStartup.DEFAULT.start("test");

        test.tag("test01", "1");

        test.end();

    }

}
