package com.example.mozzie.mozlearning.b_utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;


/**
 * Created by mozzie on 17/7/24.
 */

public class TitleHolder {
    private int mLastItemPos;
    private boolean mIsTabShpw; //tab是否展示
    private boolean isForbidScroll; //单个tab就不存在展示问题
    AlphaAnimation animationOut,animationIn;
    private View mViewUp, mViewDown;
    int mTopMax = 0;
    int mBottomMax = 0;
    public TitleHolder(View textViewUp, View textViewDown) {
        mViewUp = textViewUp;
        mViewDown = textViewDown;
        initAnimation();
    }

    public void setTabShow(boolean b){
        mIsTabShpw = b;
    }

    public void setForbidScroll(boolean b){
        isForbidScroll = b;
    }

    public void onScroll(int position){
        if(isForbidScroll){
            return;
        }
        if(position > mLastItemPos && mIsTabShpw){
            hideTab();
        }

        if(position < mLastItemPos && !mIsTabShpw){
            showTab();
        }
        mLastItemPos = position;
    }

    public void hideTab(){
        mTopMax = 0;
        int topLength = (mViewDown.getTop()-mViewUp.getHeight()) <= mTopMax ? mTopMax: mViewDown.getTop()+mViewUp.getHeight();
        ObjectAnimator downViewTop = ObjectAnimator.ofInt(mViewDown,"top", topLength);

        mBottomMax = mViewUp.getHeight();
        int bottomLength = (mViewDown.getBottom()-mViewUp.getHeight()) <= mBottomMax ? mBottomMax : (mViewDown.getBottom()-mViewUp.getHeight());
        ObjectAnimator downViewBottom = ObjectAnimator.ofInt(mViewDown,"bottom", bottomLength);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(downViewTop, downViewBottom);
        set.setDuration(200);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                upViewOut();
            }

            @Override
            public void onAnimationEnd(Animator animation) {


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
        mIsTabShpw = false;
    }

    private void upViewOut() {
        mViewUp.startAnimation(animationOut);
    }

    public void showTab(){
        if(isForbidScroll){
            return;
        }
        mTopMax = mViewUp.getHeight();
        int topLength = (mViewDown.getTop()+mViewUp.getHeight()) > mTopMax ? mTopMax: mViewDown.getTop()+mViewUp.getHeight();
        ObjectAnimator downViewTop = ObjectAnimator.ofInt(mViewDown,"top",topLength);

        mBottomMax = mViewUp.getHeight() + mViewDown.getHeight();
        int bottomLength = (mViewDown.getBottom()+mViewUp.getHeight()) > mBottomMax ? mBottomMax : (mViewDown.getBottom()+mViewUp.getHeight());
        ObjectAnimator downViewBottom = ObjectAnimator.ofInt(mViewDown,"bottom",bottomLength);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(downViewTop, downViewBottom);
        set.setDuration(200);

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                upViewIn();
            }

            @Override
            public void onAnimationEnd(Animator animation) {


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
        mIsTabShpw = true;
    }

    private void upViewIn() {
        mViewUp.startAnimation(animationIn);
    }

    private void initAnimation(){
        animationOut = new AlphaAnimation(1f,1f);
        animationOut.setDuration(200);
        animationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                setClickable(false);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                mViewUp.setVisibility(View.GONE);
                setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        animationIn = new AlphaAnimation(1f,1f);
        animationIn.setDuration(200);
        animationIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mViewUp.setVisibility(View.VISIBLE);
                setClickable(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void setClickable(boolean isClickable){
        mViewUp.setClickable(isClickable);
        mViewDown.setClickable(isClickable);
    }
}
