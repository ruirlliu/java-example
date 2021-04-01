package example.jdk.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 相当于mysql的group by功能
 * @author lr
 * @date 2021/2/23
 */
public class GroupByUtil {

    @SafeVarargs
    public static <T> List<T> groupBy(List<T> list, Function<T, ?> mapper, Function<T, ?>... mappers) {
        Map<GroupKey, T> group = new LinkedHashMap<>(16);
        Object[] condition = new Object[mappers.length + 1];
        for (T t : list) {
            for (int i = 0; i < mappers.length; i++) {
                condition[i] = mappers[i].apply(t);
            }
            condition[mappers.length] = mapper.apply(t);
//            Object[] objects = Arrays.stream(mappers).map(m -> m.apply(t)).toArray();
            group.putIfAbsent(new GroupKey(condition), t);
        }
        return new ArrayList<>(group.values());
    }

    static class GroupKey {
        private final Object[] condition;
        public GroupKey(Object... condition) {
            this.condition = condition;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            GroupKey key = (GroupKey) o;
            return Arrays.equals(condition, key.condition);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(condition);
        }
    }

}
