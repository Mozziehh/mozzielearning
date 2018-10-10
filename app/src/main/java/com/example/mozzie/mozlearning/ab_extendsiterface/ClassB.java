package com.example.mozzie.mozlearning.ab_extendsiterface;

public class ClassB extends AbstactClass implements MyOnback{

    private boolean isCanback;
    public ClassB(String tag) {
        super("TAG_B");
        isCanback = true;
    }

    @Override
    public boolean myOnback() {
        if(isCanback){
            isCanback = false;
            return true;
        }
        return isCanback;
    }
}
