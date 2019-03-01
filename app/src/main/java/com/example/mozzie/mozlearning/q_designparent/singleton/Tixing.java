package com.example.mozzie.mozlearning.q_designparent.singleton;

public class Tixing {
    float shangdi,gao;
    float xiadi;   // static float xiadi;

    Tixing(float x, float y, float h){
        shangdi = x;
        xiadi = y;
        gao = h;
    }

    public float getXiadi() {
        return xiadi;
    }

    public void xiugaixiadi(float b){
        xiadi = b;
    }
}
