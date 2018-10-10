package com.example.mozzie.mozlearning.aa_rxjava;


import com.example.mozzie.mozlearning.q_designparent.component.ComponentImpl;
import com.example.mozzie.mozlearning.q_designparent.component.Leaf;

import rx.Observable;
import rx.Subscriber;


/**
 * rxjava
 * 异步神器
 * Observable：发射源，英文释义“可观察的”，在观察者模式中称为“被观察者”或“可观察对象”；
 * Observer：接收源，是观察者模式中的“观察者”，可接收Observable、Subject发射的数据；
 * Subscriber：Subscriber实现了Observer接口，比Observer多了一个最重要的方法unsubscribe( )，用来取消订阅，当你不再想接收数据了，可以调用unsubscribe( )方法停止接收，Observer 在 subscribe() 过程中,最终也会被转换成 Subscriber 对象，一般情况下，建议使用Subscriber作为接收源；
 * Subscription ：Observable调用subscribe( )方法返回的对象，同样有unsubscribe( )方法，可以用来取消订阅事件；
 * Subject：Subject是一个比较特殊的对象，既可充当发射源，也可充当接收源，为避免初学者被混淆，本章将不对Subject做过多的解释和使用，重点放在Observable和Observer上，先把最基本方法的使用学会，后面再学其他的都不是什么问题；
 *
 * 参数：
 * Action0：RxJava中的一个接口，它只有一个无参call（）方法，且无返回值，同样还有Action1，Action2...Action9等，Action1封装了含有 1 个参的call（）方法，即call（T t），Action2封装了含有 2 个参数的call方法，即call（T1 t1，T2 t2），以此类推；
 * Func0：与Action0非常相似，也有call（）方法，但是它是有返回值的，同样也有Func0、Func1...Func9;
 *
 * 特点：
 * 一个Observable可以发出零个或者多个事件，直到结束或者出错。每发出一个事件，就会调用它的Subscriber的onNext方法，最后调用Subscriber.onNext()或者Subscriber.onError()结束。

 Rxjava的看起来很像设计模式中的观察者模式，但是有一点明显不同，那就是如果一个Observerble没有任何的的Subscriber，那么这个Observable是不会发出任何事件的。
 */

public class MainActivity {

    public static void main(String args[]){
        System.out.println("========RxJava========");

        basicUse();



    }

    private static void basicUse() {
        System.out.println("========RxJava-基本用法========");
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello rx");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> stringSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("========RxJava-s======== : " + s);
            }

            @Override
            public void onStart() {
                super.onStart();
                System.out.println("========RxJava-onStart======== ");
                System.out.println("Subscriber特有，要做一些准备工作");
            }
        };

        observable.subscribe(stringSubscriber);
    }
}
