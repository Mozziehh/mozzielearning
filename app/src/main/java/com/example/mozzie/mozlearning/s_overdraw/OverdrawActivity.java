package com.example.mozzie.mozlearning.s_overdraw;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.example.mozzie.mozlearning.b_utils.TitleHolder;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;
import com.example.mozzie.mozlearning.p_recycleview.RecycleviewActivity;

/**
 * Created by mozzie on 17/9/11.
 */

public class OverdrawActivity extends Activity{

    RelativeLayout overdrawUp;
//    RelativeLayout overdrawDown;
    RelativeLayout overdrawOpp;
    WebView mWebView;
    TextView mTextView;
    boolean isClick;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OverdrawDrawable overdraw = new OverdrawDrawable();
        getWindow().setBackgroundDrawable(overdraw);

        setContentView(R.layout.activity_overdrawacitivity_layout);
        for(int i = 0 ; i< 10 ; i++){
            LOGGER.d(this, "" + i);
        }
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(overdraw, "colorValue", 0, 255);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);

        overdrawUp = (RelativeLayout)findViewById(R.id.overdraw_up);
//        overdrawDown = (RelativeLayout)findViewById(R.id.overdraw_down);
        overdrawOpp = (RelativeLayout)findViewById(R.id.overdraw_opp);
        mWebView = (WebView)findViewById(R.id.overdraw_webview);
        mTextView = (TextView)findViewById(R.id.textview10);
        mWebView.loadUrl("http://www.baidu.com");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        final TitleHolder titleHolder = new TitleHolder(getApplicationContext() ,overdrawUp, mWebView, overdrawOpp);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(isClick){
//                    titleHolder.showTitle();
//                    isClick = false;
//                }else {
//                    titleHolder.hideTitle();
//                    isClick = true;
//                }titleHolder.hideTitle();
                titleHolder.hideTitle();
            }
        });
//        mWebView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isClick){
//                    titleHolder.showTitle();
//                    isClick = false;
//                }else {
//                    titleHolder.hideTitle();
//                    isClick = true;
//                }
//            }
//        });
//
//        overdrawDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isClick){
//                    titleHolder.showTitle();
//                    isClick = false;
//                }else {
//                    titleHolder.hideTitle();
//                    isClick = true;
//                }
//            }
//        });
        overdrawUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(getApplicationContext(),"sha" + titleHolder.getTitleSie());
            }
        });

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, OverdrawActivity.class);
        context.startActivity(intent);
    }

    private class OverdrawDrawable extends Drawable {
        Paint paint = new Paint();
        int mColorValue = 0;

        @SuppressWarnings("unused")
        public void setColorValue(int colorValue) {
            mColorValue = colorValue;
            invalidateSelf();
        }

        @Override
        public void draw(Canvas canvas) {
            paint.setColor(Color.rgb(mColorValue, 255 - mColorValue, 255));

            for (int i = 0; i < 10; i++) {
                canvas.drawRect(getBounds(), paint);
            }
        }

        @Override
        public void setAlpha(int alpha) {
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
        }

        @Override
        public int getOpacity() {
            return PixelFormat.OPAQUE;
        }
    }


}
