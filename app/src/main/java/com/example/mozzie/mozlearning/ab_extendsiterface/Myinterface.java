package com.example.mozzie.mozlearning.ab_extendsiterface;


import android.text.TextUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Myinterface implements IInterface{

    public ConcurrentHashMap<String, AbstactClass> classList ;
    public Myinterface(){
        classList = new ConcurrentHashMap<>();
        classList.put("TAG_A", new ClassA("TAG_A"));
        classList.put("TAG_B", new ClassB("TAG_B"));
        classList.put("TAG_C", new ClassC("TAG_C"));
    }

    @Override
    public void onBackPress() {
        if(onBack()){
            System.out.println("onBackPress");
        }else{
            System.out.println("cannot - onBackPress");
        }
    }

    public boolean onBack() {
        Iterator iterator=classList.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String,AbstactClass> entry = (Map.Entry<String,AbstactClass>) iterator.next();
            if(entry.getValue() != null && entry.getValue() instanceof MyOnback){
                return ((MyOnback)entry.getValue()).myOnback();
            }
        }
        return false;
    }

}
