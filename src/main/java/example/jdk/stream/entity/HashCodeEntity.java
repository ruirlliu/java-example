package example.jdk.stream.entity;

import java.util.Objects;

/**
 * @author lr
 * @date 2021/4/22
 */
public class HashCodeEntity extends BaseEntity {

    public HashCodeEntity(String name, String desc) {
        super(name, desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, desc);
    }

    public static BaseEntity valueOf(BaseEntity entity) {
        return new HashCodeEntity(entity.getName(), entity.getDesc());
    }
}
