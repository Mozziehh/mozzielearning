package com.example.mozzie.mozlearning.b_utils;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by mozzie on 17/6/23.
 */

public class TabHolder {

    //    private TabWidget mTabWidget;
    private ViewGroup mTabView;
    private int mLastItemPos;
    private boolean mIsTabShpw; //tab是否展示
    private boolean isForbidScroll; //单个tab就不存在展示问题

    public TabHolder(ViewGroup tabview){
        mTabView = tabview;
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

//    public void onScroll(int position){
//        if(isForbidScroll){
//            return;
//        }
//        if(position > mLastItemPos && mIsTabShpw){
//            invitationClose();
//
//        }
//
//        if(position < mLastItemPos && !mIsTabShpw){
//            invitationOpen();
//        }
//        mLastItemPos = position;
//    }

//    public void showTab(){
//        if(isForbidScroll){
//            return;
//        }
//        if(mTabView.getVisibility() != View.VISIBLE){
//            mTabView.setVisibility(View.VISIBLE);
//            Animation upAnim = new TranslateAnimation(0, 0, mTabView.getHeight(), 0);
//            upAnim.setFillAfter(true);
//            upAnim.setDuration(200);
//            mTabView.startAnimation(upAnim);
//            mIsTabShpw = true;
//        }
//    }
//
//    public void hideTab(){
//        Animation downAnim = new TranslateAnimation(0, 0, 0, mTabView.getHeight());
//        downAnim.setFillAfter(true);
//        downAnim.setDuration(200);
//        mTabView.startAnimation(downAnim);
//        downAnim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                mTabView.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        mIsTabShpw = false;
//    }

    public void showTab(){
        if(isForbidScroll){
            return;
        }
        if(mTabView.getVisibility() != View.VISIBLE){
            Animation upAnim = new TranslateAnimation(0, 0, mTabView.getHeight(), 0);
            upAnim.setDuration(200);
            mTabView.startAnimation(upAnim);
            upAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mTabView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mIsTabShpw = true;
        }
    }

    public void hideTab(){
        Animation downAnim = new TranslateAnimation(0, 0, 0, mTabView.getHeight());
        downAnim.setDuration(200);
        mTabView.setVisibility(View.GONE);
        mTabView.startAnimation(downAnim);

        downAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mIsTabShpw = false;
    }

    /**
     * 底部导航关闭
     */
    private void invitationClose() {
        final LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTabView.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(0, -mTabView.getHeight());
        animator.setDuration(200);
        animator.setRepeatCount(1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                layoutParams.bottomMargin = value;
                mTabView.setLayoutParams(layoutParams);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
//                state = CLOSEING;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                state = CLOSE;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    /**
     * 底部导航打开
     */
    private void invitationOpen() {
        final LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTabView.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(-mTabView.getHeight(), 0);
        animator.setDuration(200);
        animator.setRepeatCount(1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                layoutParams.bottomMargin = value;
                mTabView.setLayoutParams(layoutParams);
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
//                state = OPENING;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                state = OPEN;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }
}
