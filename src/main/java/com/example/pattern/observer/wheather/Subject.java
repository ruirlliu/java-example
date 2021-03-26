package com.example.pattern.observer.wheather;

/**
 * 主题（发布者、被观察者）
 * @author lr
 * @date 2021/3/11
 */
public interface Subject {
    /**
     * 注册观察者
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObservers();

}
