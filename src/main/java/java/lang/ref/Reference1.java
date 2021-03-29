package java.lang.ref;

import java.security.ProtectionDomain;

/**
 * 自定义的类所在包名不能以 java 开头
 * @see ClassLoader#preDefineClass(String, ProtectionDomain)
 * @author lr
 * @date 2021/3/25
 *
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
