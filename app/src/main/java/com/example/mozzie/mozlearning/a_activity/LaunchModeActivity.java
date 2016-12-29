package com.example.mozzie.mozlearning.a_activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.d_intent.Aactivity;

/**
 * Created by mozzie on 16/12/28.
 */

public class LaunchModeActivity extends Aactivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_mode_layout);
        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        Button standard = (Button) findViewById(R.id.button3);
        Button singleTop = (Button) findViewById(R.id.button4);
        Button singleTask = (Button) findViewById(R.id.button5);
        Button singleInstance = (Button) findViewById(R.id.button6);
        standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setText("直接在栈顶创建");
            }
        });
        singleTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setText("先看看有没有，有就直接用，没有再在栈顶创建");
            }
        });
        singleTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setText("先看看有没有，有就直接用并且把它上方的都出栈，没有再在栈顶创建 (其实如果 singleTask 模式指定了不同的 taskAffinity,也会启 动一个新的返回栈)");
            }
        });
        singleInstance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setText("先看看有没有，有就直接用，没有就创建，但是要创建在新的栈里面");
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LaunchModeActivity.class);
        context.startActivity(intent);
    }
}
