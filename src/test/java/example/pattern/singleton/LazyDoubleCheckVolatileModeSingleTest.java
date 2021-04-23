package example.pattern.singleton;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author lr
 * @date 2021/4/16
 */
public class LazyDoubleCheckVolatileModeSingleTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {


        reflect();


        serialize();

    }

    public static void reflect() throws InstantiationException, IllegalAccessException {
        System.out.println("reflect...");
        Person person = LazyDoubleCheckVolatileModeSingle.get();
        System.out.println(person + ": " + System.identityHashCode(person));
        LazyDoubleCheckVolatileModeSingle instance = LazyDoubleCheckVolatileModeSingle.class.newInstance();
        Person reflect = instance.get();
        System.out.println(reflect + ": " + System.identityHashCode(reflect));
        System.out.println(person == reflect);
    }

    public static void serialize() throws IOException, ClassNotFoundException {
        System.out.println("\nserialize...");
        Person person = LazyDoubleCheckVolatileModeSingle.get();
        System.out.println(person + ": " + System.identityHashCode(person));
        ObjectOutputStream oos= new ObjectOutputStream(Files.newOutputStream(Paths.get("C:/abcsys/Person.txt"), StandardOpenOption.CREATE, StandardOpenOption.WRITE));
        oos.writeObject(person);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:/abcsys/Person.txt"));
        Person serial =(Person) ois.readObject();
        ois.close();
        System.out.println(serial + ": " + System.identityHashCode(serial));
        System.out.println(person == serial);
    }

}