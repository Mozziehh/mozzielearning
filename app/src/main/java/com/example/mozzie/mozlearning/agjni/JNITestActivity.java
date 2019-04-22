package com.example.mozzie.mozlearning.agjni;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;

public class JNITestActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastUtils.show(getBaseContext(), MyJniTest.getString());
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, JNITestActivity.class);
        context.startActivity(intent);
    }
}
