package example.jdk.stream.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * @author lr
 * @date 2021/4/22
 */
@Getter
@Setter
@ToString
public class BaseEntity {

    protected String name;

    protected String desc;

//    public BaseEntity() {}

    public BaseEntity(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

}

