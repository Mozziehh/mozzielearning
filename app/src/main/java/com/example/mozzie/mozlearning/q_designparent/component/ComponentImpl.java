package com.example.mozzie.mozlearning.q_designparent.component;

import java.util.ArrayList;
import java.util.List;

public class ComponentImpl extends Component{

    List<Component> componentList = new ArrayList<>();

    public ComponentImpl(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        componentList.add(component);
    }

    @Override
    public void delete(Component component) {
        componentList.remove(component);
    }

    @Override
    public void show(int index) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < index; i++){
            stringBuilder.append("-");
        }
        System.out.println(stringBuilder.toString() + this.name);
        for(Component component : componentList){
            component.show(index + 1);
        }
    }
}
