package com.example.mozzie.mozlearning.q_designparent.singleton;

/**
 * 描述：这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。
 * 因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
 * 这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
 */
public class HangurySingleton {

    private static HangurySingleton mySingleton = new HangurySingleton();
    private static boolean isPrint = false;
    private HangurySingleton(){ }

    public static HangurySingleton getInstance(){
        return mySingleton;
    }

    public String getSingletonString(){
        return "This is HangurySingleton";
    }

    public void setPrint(boolean isPrint) {
        this.isPrint = isPrint;
    }
}
