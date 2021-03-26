package com.example.reactor;


import io.reactivex.Observable;

/**
 * @author lr
 * @date 2021/3/8
 */
public class ObservableDemo {

    public static void main(String[] args) {
        Boolean aBoolean = Observable.just("1")
                .map(Integer::valueOf)
                .isEmpty().blockingGet();
        System.out.println(aBoolean);
    }

}
