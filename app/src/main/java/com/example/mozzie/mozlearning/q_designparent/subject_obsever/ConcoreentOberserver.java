package com.example.mozzie.mozlearning.q_designparent.subject_obsever;

import com.example.mozzie.mozlearning.b_utils.LOGGER;

/**
 * Created by mozzie on 17/8/5.
 */

public class ConcoreentOberserver extends Observer {

    private String state;
    private String name;

    public ConcoreentOberserver(String name, String state){
        this.name = name;
        this.state = state;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        super.update();
        System.out.println("my name is :" + name  + ",I am :" + state);
    }
}
