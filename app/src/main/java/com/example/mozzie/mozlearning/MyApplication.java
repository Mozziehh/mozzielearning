package com.example.mozzie.mozlearning;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;

import com.MyInstrumentation;
import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.github.moduth.blockcanary.BlockCanary;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by mozzie on 16/12/27.
 */

public class MyApplication extends Application {

    private static Context mContext;
    private static String TAG = "huhao-MyApplication";

    @Override public void onCreate() {
        super.onCreate();
        try {
            hookInstrumentation();
            LOGGER.d(TAG, "hookInstrumentation");
        }catch (Exception e){
            LOGGER.d(TAG, e.toString());
        }

        LOGGER.d(TAG, "onCreate");
        mContext = this.getApplicationContext();

        initWechatSoter();
        BlockCanary.install(this, new AppContext()).start();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LOGGER.d(TAG, "onConfigurationChanged");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LOGGER.d(TAG, "onLowMemory");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        LOGGER.d(TAG, "onTerminate");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        LOGGER.d(TAG, "onTrimMemory");
    }

    private void initWechatSoter() {

    }

    public static Context getMyContext(){
        return mContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    private void hookInstrumentation() throws Exception{
        Class<?> activityThread=Class.forName("android.app.ActivityThread");
        Method currentActivityThread=activityThread.getDeclaredMethod("currentActivityThread");
        currentActivityThread.setAccessible(true);
        //获取主线程对象
        Object activityThreadObject=currentActivityThread.invoke(null);

        //获取Instrumentation字段
        Field mInstrumentation=activityThread.getDeclaredField("mInstrumentation");
        mInstrumentation.setAccessible(true);
        Instrumentation instrumentation= (Instrumentation) mInstrumentation.get(activityThreadObject);
        MyInstrumentation customInstrumentation= new MyInstrumentation(instrumentation,this);
        //替换掉原来的,就是把系统的instrumentation替换为自己的Instrumentation对象
        mInstrumentation.set(activityThreadObject, customInstrumentation);
    }
}
