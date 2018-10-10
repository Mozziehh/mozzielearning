package com.example.mozzie.mozlearning.q_designparent.observer;

import java.util.ArrayList;
import java.util.List;

public class Observer implements IObserver{

    private String mName;
    public Observer(String name){
        mName = name;
    }
    @Override
    public void update() {
        System.out.println("========" + mName + "========");
    }
}
