package com.example.pattern.observer.simple;

/**
 * @author lr
 * @date 2021/3/11
 */
public class Client {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();

        Observer zhangsan = new ConcrereObserver("zhangsan");
        Observer lisi = new ConcrereObserver("lisi");
        Observer wangwu = new ConcrereObserver("wangwu");

        subject.attach(zhangsan);
        subject.attach(lisi);
        subject.attach(wangwu);

        subject.notify("hello");
    }

}
