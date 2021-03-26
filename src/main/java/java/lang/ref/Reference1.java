package java.lang.ref;

/**
 * @author lr
 * @date 2021/3/25
 */
public class Reference1<T> {

    private T referent;

    public Reference1(T referent) {
        this.referent = referent;
    }

    public T get() {
        return referent;
    }
}
