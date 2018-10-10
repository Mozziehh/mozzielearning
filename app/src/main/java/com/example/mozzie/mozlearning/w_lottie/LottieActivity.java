package com.example.mozzie.mozlearning.w_lottie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;

import java.lang.reflect.Method;

public class LottieActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LottieActivity.class);
        context.startActivity(intent);
        getXiaoMiChannelPath("com.example.mozzie.mozlearning");
    }

    public static String getXiaoMiChannelPath(String pkg) {
        try {
            // 通过反射获取接口：miui.os.MiuiInit.getMiuiChannelPath
            Class<?> miui = Class.forName("miui.os.MiuiInit2");
            Method method = miui.getMethod("getMiuiChannelPath", String.class);
            // 调用接口
            String result = (String) method.invoke(null, pkg);
            return result;
        } catch (Exception e) {
            LOGGER.d("PRESETCHANNEL", "获取小米预置渠道路径失败 " + e);
            //当前机型还没有这个接口，无法获取渠道号文件路径
        }
        return "";
    }

}
