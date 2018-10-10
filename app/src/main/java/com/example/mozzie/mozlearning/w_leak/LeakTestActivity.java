package com.example.mozzie.mozlearning.w_leak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.a_activity.LaunchModeActivity;
import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.example.mozzie.mozlearning.v_file.FileActivity;

public class LeakTestActivity extends Activity {

    private SingleLeakTest mSingleLeakTest;
    private Button mLeakTestButton,mLeakTestButton2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_test);

        mSingleLeakTest = SingleLeakTest.getInstance(this);
        mSingleLeakTest.setData("1");

        mLeakTestButton = (Button) findViewById(R.id.leak_test_button);
        mLeakTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchModeActivity.startActivity(LeakTestActivity.this);
                finish();
            }
        });
        mLeakTestButton2 = (Button) findViewById(R.id.leak_test_button2);
        mLeakTestButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSingleLeakTest.setData("2", new SingleLeakTest.OnDataChangeListener() {
                    @Override
                    public void onDataChanged(String newData) {
                        LaunchModeActivity.startActivity(LeakTestActivity.this);
                        finish();
                    }
                });
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LeakTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mSingleLeakTest != null){
            mSingleLeakTest.onDestroy();
        }
    }
}
