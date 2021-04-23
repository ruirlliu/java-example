package example.pattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lr
 * @date 2021/4/22
 */
public class SingletonReflect {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

//
        Singleton origin = Singleton.getInstance();
        System.out.println("origin: " + System.identityHashCode(origin));
//        System.out.println("second...");
//        Singleton origin1 = Singleton.getInstance();
//        System.out.println("origin: " + System.identityHashCode(origin1));


        Class<Singleton> singletonClass = Singleton.class;
        Constructor<Singleton> constructor = singletonClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton reflect = constructor.newInstance();
        System.out.println("reflect: " + System.identityHashCode(reflect));
    }

}
