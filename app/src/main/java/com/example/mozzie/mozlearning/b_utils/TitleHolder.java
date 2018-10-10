package com.example.mozzie.mozlearning.b_utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
    private View mViewUp, mViewDown, mViewOpp;
    int mTopMax = 0;
    int mBottomMax = 0;
    private Context mContext;
    public TitleHolder(View textViewUp, View textViewDown) {
        mViewUp = textViewUp;
        mViewDown = textViewDown;
        initAnimation();
    }

    public TitleHolder(Context context, View textViewUp, View textViewDown, View textViewOpp){
        mContext = context;
        mViewUp = textViewUp;
        mViewDown = textViewDown;
        mViewOpp = textViewOpp;
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
        mBottomMax = mViewUp.getHeight();
        int topLength = (mViewDown.getTop()-mViewUp.getHeight()) <= mTopMax ? mTopMax: mViewDown.getTop()+mViewUp.getHeight();
        ObjectAnimator downViewTop = ObjectAnimator.ofInt(mViewDown,"top", topLength);
        ObjectAnimator downViewTop2 = ObjectAnimator.ofInt(mViewUp,"top", topLength - mViewUp.getHeight());

        int bottomLength = (mViewDown.getBottom()-mViewUp.getHeight()) <= mBottomMax ? mBottomMax : (mViewDown.getBottom()-mViewUp.getHeight());
        ObjectAnimator downViewBottom = ObjectAnimator.ofInt(mViewDown,"bottom", bottomLength);
        ObjectAnimator downViewBottom2 = ObjectAnimator.ofInt(mViewUp,"bottom", bottomLength- mViewUp.getHeight());
        LOGGER.d("title-hidetitle", "topLength: " + topLength + " bottomLength:" + bottomLength);

//        int topLength2 = (mViewDown.getTop()-mViewUp.getHeight()) <= mTopMax ? mTopMax: mViewDown.getTop()+mViewUp.getHeight();
//        ObjectAnimator downViewTop2 = ObjectAnimator.ofInt(mViewUp,"top", topLength2 - 200);
//
//        int bottomLength2 = (mViewDown.getBottom()-mViewUp.getHeight()) <= mBottomMax ? mBottomMax : (mViewDown.getBottom()-mViewUp.getHeight());
//        ObjectAnimator downViewBottom2 = ObjectAnimator.ofInt(mViewUp,"bottom", bottomLength2 - 200);


        AnimatorSet set = new AnimatorSet();
        set.playTogether(downViewTop, downViewBottom, downViewTop2, downViewBottom2);
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
        if(animationOut != null && animationOut.hasEnded()){
            mViewUp.startAnimation(animationOut);
        }
    }

    public void showTab(){
        if(isForbidScroll){
            return;
        }
        mTopMax = mViewUp.getHeight();
        int topLength = (mViewDown.getTop()+mViewUp.getHeight()) > mTopMax ? mTopMax: mViewDown.getTop()+mViewUp.getHeight();
        ObjectAnimator downViewTop = ObjectAnimator.ofInt(mViewDown,"top",topLength);
        ObjectAnimator downViewTop2 = ObjectAnimator.ofInt(mViewUp,"top",topLength - mTopMax);

        mBottomMax = mViewUp.getHeight() + mViewDown.getHeight();
        int bottomLength = (mViewDown.getBottom()+mViewUp.getHeight()) > mBottomMax ? mBottomMax : (mViewDown.getBottom()+mViewUp.getHeight());
        ObjectAnimator downViewBottom = ObjectAnimator.ofInt(mViewDown,"bottom",bottomLength);
        ObjectAnimator downViewBottom2 = ObjectAnimator.ofInt(mViewUp,"bottom",bottomLength - mTopMax);

        LOGGER.d("title-showtitle", "topLength: " + topLength + " bottomLength:" + bottomLength);

//        int topLength2 = (mViewDown.getTop()+mViewUp.getHeight()) > mTopMax ? mTopMax: mViewDown.getTop()+mViewUp.getHeight();
//        ObjectAnimator downViewTop2 = ObjectAnimator.ofInt(mViewUp,"top",topLength2 - 200);
//
//        mBottomMax = mViewUp.getHeight() + mViewDown.getHeight();
//        int bottomLength2 = (mViewDown.getBottom()+mViewUp.getHeight()) > mBottomMax ? mBottomMax : (mViewDown.getBottom()+mViewUp.getHeight());
//        ObjectAnimator downViewBottom2 = ObjectAnimator.ofInt(mViewUp,"bottom",bottomLength2 - 200);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(downViewTop, downViewBottom, downViewTop2, downViewBottom2);
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
        if(animationIn != null && animationIn.hasEnded()){
            mViewUp.startAnimation(animationIn);
        }
    }

    private void initAnimation(){

        animationOut = new AlphaAnimation(1f,1f);
        animationOut.setDuration(200);
        animationOut.setFillAfter(true);
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
        animationIn.setFillAfter(true);
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

    /**
     * 展示title栏
     */
    public void showTitle() {
        if(isAnimatorRunning()){
            return;
        }
        mViewUp.setVisibility(View.VISIBLE);
        int bottomLine = DisplayUtil.dip2px(mViewUp.getContext(), 45);
        ValueAnimator va = createDropAnim(mViewUp, 0, bottomLine);
        va.addListener(new AnimatorListenerAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mViewOpp.setVisibility(View.VISIBLE);
            }
        });
        va.setDuration(300);
        va.start();
    }

    /**
     * 隐藏title栏
     */
    public void hideTitle() {
        if(isAnimatorRunning()){
            return;
        }
        int bottomLine = DisplayUtil.dip2px(mViewUp.getContext(), 45);

        ValueAnimator va = createDropAnim(mViewUp, bottomLine, 0);
        va.setDuration(300);
        va.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mViewOpp.setVisibility(View.GONE);
                mViewUp.setVisibility(View.INVISIBLE);

            }
        });
        va.start();
    }

    private ValueAnimator mValueAnimator;
    /**
     *
     * @param view
     * @param start 初始状态值
     * @param end 结束状态值
     * @return
     */
    private ValueAnimator createDropAnim(final  View view, final int start, int end) {
        LOGGER.d("TitleHolder", "height = " + view.getHeight() + ", " + "width = " + view.getWidth() + ", " + mViewDown.getHeight());
        mValueAnimator = ValueAnimator.ofInt(start, end);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();//根据时间因子的变化系数进行设置高度
                view.layout(0, 0- DisplayUtil.dip2px(mViewUp.getContext(), 45) + value, 1440, 180 - DisplayUtil.dip2px(mViewUp.getContext(), 45) + value);
                mViewDown.layout(0 , value, 1440 , 2292 + DisplayUtil.dip2px(mViewUp.getContext(), 45));
                mViewOpp.layout(0, 0- DisplayUtil.dip2px(mViewUp.getContext(), 45) + value, 0, 180 - DisplayUtil.dip2px(mViewUp.getContext(), 45) + value);
            }
        });
        return mValueAnimator;
    }


    private boolean isAnimatorRunning() {
        if(mValueAnimator == null){
            return false;
        }
        if(mValueAnimator != null && mValueAnimator.isRunning()){
            return true;
        }
        return false;
    }


    public String getTitleSie() {
        return ":" + mViewUp.getHeight() + ":" + mViewUp.getWidth();
//        LOGGER.d("TitleHolder", "height = " + mViewUp.getHeight() + ", " + "width = " + mViewUp.getWidth() + ", " + mViewDown.getHeight());

    }
}
