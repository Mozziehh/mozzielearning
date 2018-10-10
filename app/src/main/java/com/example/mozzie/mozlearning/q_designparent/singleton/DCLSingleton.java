package com.example.mozzie.mozlearning.q_designparent.singleton;

/**
 * 本程序的亮点自然都在getInstance方法上，可以看到getInstance方法中对singleton进行了两次判空：第一层判断主要是为了避免不必要的同步，第二层的判断是为了在null的情况下创建实例。这是什么意思呢？是不是有点摸不着头脑，下面就一起来分析一下。

 假设线程A执行到singleton = new Singleton()语句，这里看起来是一句代码，但实际上它并不是一个原子操作，这句代码最终会被编译成多条汇编指令，它大概做了3件事情：

 给Singleton的实例分配内存
 调用Singleton()的构造函数，初始化成员字段
 将singleton对象指向分配的内存空间（此时singleton就不是null了）
 但是，由于Java编译器允许处理器乱序执行，以及JDK1.5之前JMM（Java Memory Model，即Java内存模型）中Cache、寄存器到主内存回写顺序的规定，上面的第二和第三的顺序是无法保证的。也就是说，执行顺序可能是1-2-3，也可能是1-3-2。如果是后者，并且在3执行完毕、2未执行之前，被切换到线程B上，这时候singleton因为已经在线程A内执行过了第三点，singleton已经是非空了，所以线程B直接取走singleton，再使用时就会报错，这就是DCL失效问题，而且这种难以跟踪难以重现的错误很可能会隐藏很久。

 在JDK1.5之后，SUN官方已经注意到这种问题，调整了JVM，这时候，volatile关键字闪亮登场了，volatile可以保证singleton对象每次都是从主内存中读取。当然，volatile或多或少也会影响性能，但是考虑程序的正确性，牺牲这点性能还是值得的。在写代码的时候，我发现很多同学都不写volatile关键字，实际上是存在隐患的。

 DCL模式是使用最多的单例实现方式。
 */
public class DCLSingleton {

    //使用volatile关键字
    private volatile static DCLSingleton mySingleton;
    private static boolean isPrint = false;
    private DCLSingleton(){ }

    public static DCLSingleton getInstance(){
        if(isPrint){
            System.out.println("DCLSingleton == null ? " + mySingleton == null);
        }
        if(mySingleton == null){
            //进行同步
            synchronized (DCLSingleton.class){
                //给Singleton的实例分配内存
                //调用Singleton()的构造函数，初始化成员字段
                //将singleton对象指向分配的内存空间（此时singleton就不是null了）
                mySingleton = new DCLSingleton();
                if(isPrint){
                    System.out.println("new DCLSingleton");
                }
            }
        }
        return mySingleton;
    }

    public String getSingletonString(){
        return "This is DCLSingleton";
    }

    public void setPrint(boolean isPrint) {
        this.isPrint = isPrint;
    }
}
