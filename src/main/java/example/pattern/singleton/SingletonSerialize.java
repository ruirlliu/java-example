package example.pattern.singleton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * @author lr
 * @date 2021/4/23
 */
public class SingletonSerialize {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Singleton origin = Singleton.getInstance();
        System.out.println("origin: " + System.identityHashCode(origin));
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:\\serialize\\instance.txt"));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\serialize\\instance.txt"))) {
            oos.writeObject(origin);
            Singleton serialize = (Singleton) ois.readObject();
            System.out.println("serialize: " + System.identityHashCode(serialize));
        }
    }

}
