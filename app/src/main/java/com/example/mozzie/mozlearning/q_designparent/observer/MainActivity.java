package com.example.mozzie.mozlearning.q_designparent.observer;

import com.example.mozzie.mozlearning.q_designparent.component.ComponentImpl;
import com.example.mozzie.mozlearning.q_designparent.component.Leaf;

/**
 * Created by mozzie on 17/8/5.
 观察者模式：很简单，一个接口，一个抽象类，两个实现类，抽象类之间再来层监听，我先自己写一个
 */

public class MainActivity {

    public static void main(String args[]){
        System.out.println("========观察者模式========");

        Oberserved oberserved = new Oberserved();
        Observer observer = new Observer("observer");
        Observer observer2 = new Observer("observer2");
        Observer observer3 = new Observer("observer3");

        oberserved.addObserver(observer);
        oberserved.addObserver(observer2);
        oberserved.addObserver(observer3);
        oberserved.notifyObservers();

        oberserved.deleteObserver(observer3);
        oberserved.notifyObservers();
    }
}
