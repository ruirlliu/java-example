package example.pattern.single;

import org.springframework.web.client.RestTemplate;

/**
 * @author liurui
 * @date 2021/4/14
 */
public class LazyDoubleCheckVolatileModeSingle {

    private volatile static RestTemplate template;

    public static RestTemplate get() {
        if (template == null) {
            synchronized (LazyDoubleCheckVolatileModeSingle.class) {
                if (template == null) {
                    template = new RestTemplate();
                }
            }
        }
        return template ;
    }

}
