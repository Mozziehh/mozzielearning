package com.example.mozzie.mozlearning.w_leak;

import android.content.Context;

public class SingleLeakTest {

    public static SingleLeakTest sInstance;
    public Context mContext;
    private String mData;

    private SingleLeakTest(Context context){
        mContext = context.getApplicationContext();
    }

    public static SingleLeakTest getInstance(Context context){
        if(sInstance == null){
            sInstance = new SingleLeakTest(context);
        }
        return sInstance;
    }

    public void setData(String data) {
        mData = data;
    }

    public OnDataChangeListener mOndataChangeListener;

    /**
     * 销毁监听
     */
    public void onDestroy() {
        mOndataChangeListener = null;
    }

    public interface OnDataChangeListener{
        void onDataChanged(String newData);
    }

    public void setData(String data, OnDataChangeListener onDataChangeListener){
        final String newData = data + "hhhh";
        mOndataChangeListener = onDataChangeListener;
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        };
//        runnable.run();
        if(mOndataChangeListener != null){
            mOndataChangeListener.onDataChanged(newData);
        }

    }

    public String getData(){
        return mData;
    }
}
