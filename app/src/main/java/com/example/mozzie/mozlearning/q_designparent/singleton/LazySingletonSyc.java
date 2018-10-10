package com.example.mozzie.mozlearning.q_designparent.singleton;

/**
 * 描述：这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
 优点：第一次调用才初始化，避免内存浪费。
 缺点：由于在getInstance()方法中添加了synchronized关键字，第一次加载时需要及时进行实例化，
 反应稍慢，最大的问题是每次调用getInstance都进行同步，造成不必要的同步开销，这种模式一般不建议使用。
 */
public class LazySingletonSyc {

    private static LazySingletonSyc mySingleton;
    private static boolean isPrint;
    private LazySingletonSyc(){ }

    public static synchronized LazySingletonSyc getInstance(){
        if(isPrint){
            System.out.println("LazySingleton == null ? " + mySingleton == null);
        }
        if(mySingleton == null){
            mySingleton = new LazySingletonSyc();
            if(isPrint){
                System.out.println("new LazySingleton");
            }
        }
        return mySingleton;
    }

    public String getSingletonString(){
        return "This is LazySingletonSyc";
    }

    public void setPrint(boolean isPrint) {
        this.isPrint = isPrint;
    }
}
