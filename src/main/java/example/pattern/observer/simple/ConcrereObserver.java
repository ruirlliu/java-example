package example.pattern.observer.simple;

/**
 * 具体观察者
 * @author lr
 * @date 2021/3/11
 */
public class ConcrereObserver implements Observer{

    private final String name;

    public ConcrereObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " - " + message);
    }
}
