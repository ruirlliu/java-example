package example.jdk.exception;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * java.lang.ClassNotFoundException vs  java.lang.NoClassDefFoundError
 * @author lr
 * @date 2021/4/12
 */
public class ClassThrowable {

    static class ClassNotFoundTest {
        // java.lang.ClassNotFoundException
        public static void main(String[] args) {
            try {
                Class<?> aClass = Class.forName("com.test.NotExist");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    static class NoClassDefTest {
        // dependency scope : provided
        // java.lang.NoClassDefFoundError
        public static void main(String[] args) {
            Document parse = Jsoup.parse("www.baidu.com");
        }
    }

}
