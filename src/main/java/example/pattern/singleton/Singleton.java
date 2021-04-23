package example.pattern.singleton;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author lr
 * @date 2021/4/19
 */
public class Singleton implements Serializable {

    private static final long serialVersionUID = -3782723772939643172L;

    private static class SingleTonHolder {
        static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    private Singleton() {
        if (SingleTonHolder.INSTANCE != null) {
            throw new IllegalStateException("can't reflect singleton");
        }
    }

    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        throw new InvalidObjectException("can't deserialize singleton");
    }

    private void readObjectNoData() throws ObjectStreamException {
        throw new InvalidObjectException("can't deserialize singleton");
    }

}
