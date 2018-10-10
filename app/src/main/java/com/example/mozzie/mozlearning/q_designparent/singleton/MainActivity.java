package com.example.mozzie.mozlearning.q_designparent.singleton;

/**
 * Created by mozzie on 17/8/5.
 */

public class MainActivity {

    public static void main(String args[]){
        System.out.println("========懒加载========");
        long cur = System.currentTimeMillis();
        LazySingleton.getInstance().setPrint(false);
        for(int i = 0 ; i < 5 ; i ++){
            System.out.println(LazySingleton.getInstance().getSingletonString());
        }
        System.out.println("执行时间：" + (System.currentTimeMillis() - cur));
        System.out.println("=====================");



        System.out.println("========懒加载带同步锁========");
        cur = System.currentTimeMillis();
        LazySingletonSyc.getInstance().setPrint(false);
        for(int i = 0 ; i < 5 ; i ++){
            System.out.println(LazySingletonSyc.getInstance().getSingletonString());
        }
        System.out.println("执行时间：" + (System.currentTimeMillis() - cur));
        System.out.println("=====================");



        System.out.println("========饿汉加载带同步锁========");
        cur = System.currentTimeMillis();
        HangurySingleton.getInstance().setPrint(false);
        for(int i = 0 ; i < 5 ; i ++){
            System.out.println(HangurySingleton.getInstance().getSingletonString());

        }
        System.out.println("执行时间：" + (System.currentTimeMillis() - cur));
        System.out.println("=====================");





        System.out.println("========DCL单例========");
        cur = System.currentTimeMillis();
        DCLSingleton.getInstance().setPrint(false);
        for(int i = 0 ; i < 5 ; i ++){
            System.out.println(DCLSingleton.getInstance().getSingletonString());

        }
        System.out.println("执行时间：" + (System.currentTimeMillis() - cur));
        System.out.println("=====================");




        System.out.println("========静态内部类单例========");
        cur = System.currentTimeMillis();
        StaticSingleton.getInstance().setPrint(false);
        for(int i = 0 ; i < 5 ; i ++){
            System.out.println(StaticSingleton.getInstance().getSingletonString());

        }
        System.out.println("执行时间：" + (System.currentTimeMillis() - cur));
        System.out.println("=====================");

    }
}
