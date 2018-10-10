package com.example.mozzie.mozlearning.q_designparent.strategy;

public class ContextStrategy {

    private Strategy IStrategy;

    public ContextStrategy(Strategy strategy){
        IStrategy = strategy;
    }

    public void doSomething(){
        IStrategy.doSomething();
    }
}
