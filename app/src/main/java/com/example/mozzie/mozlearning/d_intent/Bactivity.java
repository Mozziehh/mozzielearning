package com.example.mozzie.mozlearning.d_intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;

/**
 * Created by mozzie on 16/12/27.
 */

public class Bactivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        TextView textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        button.setText("B");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("B", "i come from b");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.mozzie.mozlearning.d_intent.ACTION_START");
                intent.addCategory("android.intent.category.MY_CATEGORY");
                startActivity(intent);
            }
        });
    }
}
