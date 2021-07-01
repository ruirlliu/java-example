package example.pattern.observer.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体被观察者
 * @author lr
 * @date 2021/3/11
 */
public class ConcreteSubject implements Subject{

    private final List<Observer> observers = new ArrayList<>();


    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
