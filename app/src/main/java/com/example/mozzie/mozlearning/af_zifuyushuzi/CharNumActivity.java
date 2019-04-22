package com.example.mozzie.mozlearning.af_zifuyushuzi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.ad_xuanfuchuang.XuanfoActivity;
import com.example.mozzie.mozlearning.b_utils.DisplayUtil;
import com.example.mozzie.mozlearning.b_utils.LOGGER;

import java.io.LineNumberReader;

/**
 * Created by mozzie on 2019/3/27.
 */

public class CharNumActivity extends Activity{

    private Button mButton;
    private EditText mEditText;
    private LinearLayout mLinearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chartnum);
        mLinearLayout = (LinearLayout)findViewById(R.id.linear_layout);
        mEditText = (EditText) findViewById(R.id.btn_charnum_edit);
        mButton = (Button)findViewById(R.id.btn_chartnum);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView mtextView = new TextView(CharNumActivity.this);
//                mtextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
                mtextView.setEllipsize(TextUtils.TruncateAt.END);
                String text = mEditText.getText().toString();
                LayoutInflater layoutInflater = LayoutInflater.from(CharNumActivity.this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 0, 0);
                mtextView.setMaxWidth(DisplayUtil.dip2px(CharNumActivity.this, 80));
                mtextView.setLayoutParams(params);
                mtextView.setText(text);
                mLinearLayout.addView(mtextView);
                LOGGER.d("CharNumActivity","text-length:" + mtextView.getMeasuredWidth());
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CharNumActivity.class);
        context.startActivity(intent);
    }

}
