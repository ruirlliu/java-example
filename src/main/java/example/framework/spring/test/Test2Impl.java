package example.framework.spring.test;

/**
 * @author lr
 * @date 2021/4/19
 */
public class Test2Impl implements Test2{
    @Override
    public String test(String str) {
        return "TEST: " + str;
    }
}
