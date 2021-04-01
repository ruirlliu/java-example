package example.jdk.utils;

import java.util.Comparator;
import java.util.function.Function;

/**
 * @author lr
 * @date 2021/3/26
 */
public class CompareUtil<U, T extends Comparable<T>> {

    private boolean nullsLast = false;

    private boolean natural = true;

    private Function<U, T> mapper = o -> null;

    public CompareUtil() {
    }

    public CompareUtil<U, T> nullsLast() {
        nullsLast = true;
        return this;
    }

    public CompareUtil<U, T> nullsFirst() {
        nullsLast = false;
        return this;
    }

    public CompareUtil<U, T> natural() {
        natural = true;
        return this;
    }

    public CompareUtil<U, T> unNatural() {
        natural = false;
        return this;
    }

    public CompareUtil<U, T> mapper(Function<U, T> mapper) {
        this.mapper = mapper;
        return this;
    }


    public Comparator<U> build() {
        return (i1, i2) -> {
            T o1 = mapper.apply(i1);
            T o2 = mapper.apply(i2);
            if (o1 == null) {
                return o2 == null ? 0 : ( nullsLast ? 1 : -1);
            } else if (o2 == null) {
                return nullsLast ? -1 : 1;
            } else {
                return natural ? o1.compareTo(o2) : o2.compareTo(o1);
            }
        };
    }


}

