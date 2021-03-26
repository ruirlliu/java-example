package com.example.pattern.observer.simple;

/**
 * 抽象主题（抽象被观察者），
 * 抽象主题角色把所有观察者对象保存在一个集合里，
 * 每个主题都可以有任意数量的观察者，抽象主题提供一个接口，可以增加和删除观察者对象。
 * @author lr
 * @date 2021/3/11
 */
public interface Subject {

    /**
     * 增加订阅者
     * @param observer
     */
    public void attach(Observer observer);
    /**
     * 删除订阅者
     * @param observer
     */
    public void detach(Observer observer);
    /**
     * 通知订阅者更新消息
     */
    public void notify(String message);

}
