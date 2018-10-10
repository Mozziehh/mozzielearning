package com.example.mozzie.mozlearning.q_designparent.component;

/**
 * Created by mozzie on 17/8/5.
 * 组合模式允许你将对象组合成树形结构来表现”部分-整体“的层次结构，使得客户以一致的方式处理单个对象以及对象的组合。
 组合模式实现的最关键的地方是——简单对象和复合对象必须实现相同的接口。这就是组合模式能够将组合对象和简单对象进行一致处理的原因。
 -root
 ---Leaf1
 ---Leaf2
 ---leaf6
 ---SubRoot
 -----leaf3
 -----leaf4
 -----leaf5
 */

public class MainActivity {

    public static void main(String args[]){
        System.out.println("========组合模式========");

        ComponentImpl root = new ComponentImpl("root");
        root.add(new Leaf("Leaf1"));
        root.add(new Leaf("Leaf2"));

        ComponentImpl subRoot = new ComponentImpl("SubRoot");
        subRoot.add(new Leaf("leaf3"));
        subRoot.add(new Leaf("leaf4"));
        subRoot.add(new Leaf("leaf5"));

        Leaf leaf6 = new Leaf("leaf6");
        root.add(leaf6);
//        root.delete(leaf6);
        subRoot.add(leaf6);
        root.add(subRoot);
        root.show(1);
    }
}
