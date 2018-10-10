package com.example.mozzie.mozlearning.q_designparent.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者
 */
public abstract class IObservered {

    private List<Observer> observerList = new ArrayList();

    public void addObserver(Observer observer){
        observerList.add(observer);
    }

    public void deleteObserver(Observer observer){
        observerList.remove(observer);
    }

    public void notifyObservers(){
        for(Observer observer : observerList){
            observer.update();
        }
    }
}

