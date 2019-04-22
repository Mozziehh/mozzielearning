package com.example.mozzie.mozlearning.agjni;

public class MyJniTest {

    static {
        System.loadLibrary("MyJniTest");
    }

    public native static String getString();

}
