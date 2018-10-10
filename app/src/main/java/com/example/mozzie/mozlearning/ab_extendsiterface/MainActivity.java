package com.example.mozzie.mozlearning.ab_extendsiterface;

import com.example.mozzie.mozlearning.q_designparent.adapter.Adapter;
import com.example.mozzie.mozlearning.q_designparent.adapter.Contaget;
import com.example.mozzie.mozlearning.q_designparent.adapter.Target;

/**
 * Created by mozzie on 17/8/5.
 适配器模式：A和C接口不兼容，需要找一个B做中间件的适配
 */

public class MainActivity{

    public static void main(String args[]){
        Myinterface myinterface = new Myinterface();
        myinterface.onBackPress();
        myinterface.onBackPress();
        myinterface.onBackPress();
    }
}
