package com.example.mozzie.mozlearning.ad_xuanfuchuang;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;

public class XuanfuWebActivity extends Activity implements View.OnClickListener{

    WebView mWebView;
    private TextView webview_back,webview_title,webview_status;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuanfuweb);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.loadUrl("http://www.58.com");

        webview_back = (TextView) findViewById(R.id.webview_back);
        webview_title = (TextView) findViewById(R.id.webview_title);
        webview_status = (TextView) findViewById(R.id.webview_status);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mWebView.destroy();
        mWebView = null;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.webview_back:
                break;
            case R.id.webview_title:
                break;
            case R.id.webview_status:
                break;
        }
    }
}
