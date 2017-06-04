package com.example.mozzie.mozlearning.d_intent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;

/**
 * Created by mozzie on 16/12/28.
 */

public class Dactivity extends Aactivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        TextView textView = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.button);
        button.setText("D");
    }
}
