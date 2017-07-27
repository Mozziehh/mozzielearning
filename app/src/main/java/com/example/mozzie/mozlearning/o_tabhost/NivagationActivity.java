package com.example.mozzie.mozlearning.o_tabhost;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mozzie.mozlearning.R;

/**
 * Created by mozzie on 17/7/25.
 */

public class NivagationActivity extends AppCompatActivity {
    TextView view1,view2;//linearLayout当中竖直排列的两个imageView
    AlphaAnimation animationOut,animationIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivagation);
        view1 = (TextView) findViewById(R.id.textView8);
        view2 = (TextView) findViewById(R.id.textView9);
        initAnimation();
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofInt(view2,"top",view2.getTop()-view1.getHeight()).setDuration(500).start();
                ObjectAnimator.ofInt(view2,"bottom",view2.getBottom()-view1.getHeight()).setDuration(500).start();
                view1.startAnimation(animationOut);
            }
        });
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(view1.getVisibility() == View.GONE){
                    ObjectAnimator.ofInt(view2,"top",view2.getTop()+view1.getHeight()).setDuration(500).start();
                    ObjectAnimator.ofInt(view2,"bottom",view2.getBottom()+view1.getHeight()).setDuration(500).start();
                    view1.startAnimation(animationIn);

                }
            }
        });
    }
    private void setClickable(boolean which){
        view1.setClickable(which);
        view2.setClickable(which);
    }
    private void initAnimation(){
        animationOut = new AlphaAnimation(1f,0f);
        animationOut.setDuration(500);
        animationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                setClickable(false);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                view1.setVisibility(View.GONE);
                setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        //
        animationIn = new AlphaAnimation(0f,1f);
        animationIn.setDuration(500);
        animationIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view1.setVisibility(View.VISIBLE);
                setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NivagationActivity.class);
        context.startActivity(intent);
    }
}
