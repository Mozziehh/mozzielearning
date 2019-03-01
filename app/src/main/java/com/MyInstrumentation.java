package com;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

import com.example.mozzie.mozlearning.MyApplication;
import com.example.mozzie.mozlearning.b_utils.LOGGER;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyInstrumentation extends Instrumentation{
    private static String TAG = "huhao-MyInstrumentation";

    private Instrumentation base;

    private MyApplication myApplication;
    public MyInstrumentation(Instrumentation base, MyApplication myApplication) {
        this.base = base;
        this.myApplication = myApplication;
    }

    private void  getLoaderApk() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        Field mLoadedApk=myApplication.getClass().getSuperclass().getDeclaredField("mLoadedApk");
        mLoadedApk.setAccessible(true);
        Object mLoadedApkObject=mLoadedApk.get(myApplication);
        LOGGER.d(TAG,"获取的mLoadedApkObject="+mLoadedApkObject);
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        LOGGER.d(TAG,"哈哈，你被Hook了");
        try {
            getLoaderApk();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        LOGGER.d(TAG,"className="+className+" intent="+intent);
        return super.newActivity(cl, className, intent);
    }

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        LOGGER.d(TAG, "onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        LOGGER.d(TAG, "onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LOGGER.d(TAG, "onDestroy");
    }

    @Override
    public void callActivityOnCreate(Activity activity, Bundle icicle) {
        super.callActivityOnCreate(activity, icicle);
        LOGGER.d(TAG, "callActivityOnCreate");
    }

    @Override
    public void callActivityOnRestart(Activity activity) {
        super.callActivityOnRestart(activity);
        LOGGER.d(TAG, "callActivityOnRestart");
    }

    @Override
    public void callActivityOnDestroy(Activity activity) {
        super.callActivityOnDestroy(activity);
        LOGGER.d(TAG, "callActivityOnDestroy");

    }
}
