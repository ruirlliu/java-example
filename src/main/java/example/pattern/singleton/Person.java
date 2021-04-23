package example.pattern.singleton;

import java.io.Serializable;

/**
 * @author lr
 * @date 2021/4/16
 */
public class Person implements Serializable {

    private transient String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    private static final long serialVersionUID = -3658123571574062095L;

    // 解决序列化、反序列化导致对象不一致
    /*private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        // 注意读顺序需要和写的顺序相同
        name = s.readUTF();
        age = s.readInt() + 2;
    }


    private void writeObject(java.io.ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(name);
        s.writeInt(age);
    }*/

    private Object readResolve() {
        return this;
    }


}
