package example.pattern.singleton;

import org.springframework.web.client.RestTemplate;

/**
 * @author lr
 * @date 2021/4/16
 */
public enum EnumSingle {

    REST(new RestTemplate());

    private RestTemplate template;

    EnumSingle(RestTemplate template) {
        this.template = template;
    }



}
