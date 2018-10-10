package com.example.mozzie.mozlearning.q_designparent.component;

/**
 * 安全模式
 */
public abstract class Component {

    protected String name;

    public Component(String name){
        this.name = name;
    }

    public abstract void add(Component component);

    public abstract void delete(Component component);

    public abstract void show(int index);

}
