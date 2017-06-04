package com.example.mozzie.mozlearning.l_http;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.example.mozzie.mozlearning.d_intent.Aactivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mozzie on 17/2/21.
 */

public class HttpUrlConnectionAcitivity extends Aactivity implements View.OnClickListener{

    private Button mHttpButton;
    private TextView mTextView;
    private HttpAnsyTask myHttpAnsyTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpurlconnection_layout);

        mHttpButton = (Button)findViewById(R.id.button14);
        mHttpButton.setOnClickListener(this);

        mTextView = (TextView)findViewById(R.id.textview8);

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, HttpUrlConnectionAcitivity.class);
        context.startActivity(intent);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button14:
                myHttpAnsyTask = new HttpAnsyTask();
                myHttpAnsyTask.execute();
                break;
        }
    }

    private class HttpAnsyTask extends AsyncTask<String ,Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            LOGGER.d("HttpAnsyTask", "doInBackground");
//            StringBuilder response = new StringBuilder();
//            HttpURLConnection httpURLConnection = null;
//            try {
//                URL url = new URL("http://www.58.com");
//                httpURLConnection = (HttpURLConnection)url.openConnection();
//                httpURLConnection.setRequestMethod("GET");
//                httpURLConnection.setConnectTimeout(8000);
//                httpURLConnection.setReadTimeout(8000);
//                InputStream in = httpURLConnection.getInputStream(); // 下面对获取到的输入流进行读取
//                BufferedReader reader = new BufferedReader(new
//                        InputStreamReader(in));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (httpURLConnection != null) {
//                    httpURLConnection.disconnect();
//                }
//            }
//            return response.toString();
            StringBuilder response = new StringBuilder();

            return response.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mTextView.setText(result);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myHttpAnsyTask != null){
            myHttpAnsyTask.cancel(true);
        }
    }
}
