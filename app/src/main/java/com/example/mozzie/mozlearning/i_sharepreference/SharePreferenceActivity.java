package com.example.mozzie.mozlearning.i_sharepreference;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.SharePreferenceUtils;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;
import com.example.mozzie.mozlearning.d_intent.Aactivity;

/**
 * Created by mozzie on 17/1/3.
 */

public class SharePreferenceActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharepreference_activity);
        final EditText editText = (EditText)findViewById(R.id.editText2);
        Button button11 = (Button) findViewById(R.id.button11);
        Button button12 = (Button) findViewById(R.id.button12);

        //save
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if(!TextUtils.isEmpty(text)){
                    SharePreferenceUtils.save(SharePreferenceActivity.this, text);
                }
            }
        });

        //get
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = SharePreferenceUtils.get(SharePreferenceActivity.this);
                ToastUtils.show(SharePreferenceActivity.this, text);
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SharePreferenceActivity.class);
        context.startActivity(intent);
    }
}
