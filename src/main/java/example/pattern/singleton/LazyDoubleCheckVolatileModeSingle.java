package example.pattern.singleton;

/**
 * @author lr
 * @date 2021/4/14
 */
public class LazyDoubleCheckVolatileModeSingle /*implements Serializable*/ {

    private volatile static Person person;

    public static Person get() {
        if (person == null) {
            synchronized (LazyDoubleCheckVolatileModeSingle.class) {
                if (person == null) {
                    person = new Person("zhangsan", 22);
                }
            }
        }
        return person ;
    }

    // 解决序列化、反序列化导致对象不一致
//    private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
//        s.defaultReadObject();
//        person = (Person) s.readObject();
//    }
//
//
//    private void writeObject(java.io.ObjectOutputStream s) throws IOException {
//        s.defaultWriteObject();
//        s.writeObject(person);
//    }




}
