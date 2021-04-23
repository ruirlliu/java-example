package example.jdk.stream.entity;

import java.util.Objects;

/**
 * @author lr
 * @date 2021/4/22
 */
public class FullEntity extends BaseEntity {

    public FullEntity(String name, String desc) {
        super(name, desc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc);
    }

    public static BaseEntity valueOf(BaseEntity entity) {
        return new FullEntity(entity.getName(), entity.getDesc());
    }

}
