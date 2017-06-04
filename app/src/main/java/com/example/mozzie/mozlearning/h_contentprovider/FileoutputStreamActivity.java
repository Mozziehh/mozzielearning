package com.example.mozzie.mozlearning.h_contentprovider;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.mozzie.mozlearning.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by mozzie on 16/12/30.
 */

public class FileoutputStreamActivity extends Activity{

    private EditText mFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_file);
        mFileContent = (EditText) findViewById(R.id.editText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save(mFileContent.getText().toString());
    }

    private void save(String fileStr) {
        if (TextUtils.isEmpty(fileStr)){
            return;
        }
        //构建输出流
        FileOutputStream fileOutputStream = null;
        //构建缓冲区写入器
        BufferedWriter bufferedWriter = null;
        try {
            //实例化－打开文件获取输出流
            fileOutputStream = openFileOutput("data", Context.MODE_PRIVATE);
            //实例化－输出流转缓冲区写入器
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            //写入
            bufferedWriter.write(fileStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
