package example.jdk.exception;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author liurui
 * @date 2021/4/12
 */
public class ClassThrowable {

    static class ClassNotFoundTest {

        public static void main(String[] args) {
            try {
                Class<?> aClass = Class.forName("com.test.NotExist");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    static class NoClassDefTest {

        public static void main(String[] args) {
            Document parse = Jsoup.parse("www.baidu.com");
        }
    }

}
