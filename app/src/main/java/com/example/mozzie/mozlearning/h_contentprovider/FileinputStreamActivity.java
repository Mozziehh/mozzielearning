package com.example.mozzie.mozlearning.h_contentprovider;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.mozzie.mozlearning.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mozzie on 16/12/30.
 */

public class FileinputStreamActivity extends Activity{

    private EditText mFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_file);
        mFileContent = (EditText) findViewById(R.id.editText);
        load();
    }

    private void load() {
        FileInputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder content = new StringBuilder();

        try {
            inputStream = openFileInput("data");
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
