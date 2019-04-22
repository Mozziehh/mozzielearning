package com.example.mozzie.mozlearning.q_designparent.adapter;

import com.example.mozzie.mozlearning.q_designparent.component.ComponentImpl;
import com.example.mozzie.mozlearning.q_designparent.component.Leaf;

/**
 * Created by mozzie on 17/8/5.
 适配器模式：A和C接口不兼容，需要找一个B做中间件的适配
 */

public class MainActivity {

    public static void main(String args[]){
        System.out.println("========适配器模式========");

        Target target = new Contaget();
        target.request();

        Target target1 = new Adapter();
        target1.request();

        Adapter adapter = new Adapter();
        adapter.getRequest(1, "huhao", new Adapter.MyCallback() {
            @Override
            public void callback(int code, String errormsg) {
                System.out.println("code = " + code + "," + "errormsg = " + errormsg);
            }
        });

        Object o = null;
        System.out.println(String.valueOf(o));
//        System.out.println((String) o);
    }
}
