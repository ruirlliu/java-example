package example.jdk.stream.entity;

import java.util.Objects;

/**
 * @author lr
 * @date 2021/4/22
 */
public class EqualsEntity extends BaseEntity {

    public EqualsEntity(String name, String desc) {
        super(name, desc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(desc, that.desc);
    }

    public static BaseEntity valueOf(BaseEntity entity) {
        return new EqualsEntity(entity.getName(), entity.getDesc());
    }

}
