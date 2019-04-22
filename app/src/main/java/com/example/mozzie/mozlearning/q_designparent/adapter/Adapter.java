package com.example.mozzie.mozlearning.q_designparent.adapter;

public class Adapter extends Adaptee implements Target{
    @Override
    public void request() {
        super.doSomething();
    }

    public interface MyCallback{
        void callback(int code, String errormsg);
    }

    public void getRequest(int code, String errorMsg, MyCallback myCallback){
        myCallback.callback(code+1, errorMsg);
    }
}
