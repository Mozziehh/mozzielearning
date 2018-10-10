package com.example.mozzie.mozlearning.q_designparent.strategy;

import com.example.mozzie.mozlearning.q_designparent.adapter.Adapter;
import com.example.mozzie.mozlearning.q_designparent.adapter.Contaget;
import com.example.mozzie.mozlearning.q_designparent.adapter.Target;

/**
 * Created by mozzie on 17/8/5.
 其实这个一开始跟我想的可能不太一样，一个接口，N多个实现。然后随便找一个类拿了这个接口，然后就可以调用实现类的方法，这就是策略？
 */

public class MainActivity {

    public static void main(String args[]){
        System.out.println("========策略模式========");

        ContextStrategy AcontextStrategy = new ContextStrategy(new AStrategy());
        AcontextStrategy.doSomething();

        ContextStrategy BcontextStrategy = new ContextStrategy(new BStrategy());
        BcontextStrategy.doSomething();

        ContextStrategy CcontextStrategy = new ContextStrategy(new CStrategy());
        CcontextStrategy.doSomething();

    }
}
