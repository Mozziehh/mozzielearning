package com.example.mozzie.mozlearning.n_listview;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.example.mozzie.mozlearning.b_utils.LOGGER;

/**
 * Created by mozzie on 17/7/24.
 */

public class TabHolder {
    private static final String TAG = TabHolder.class.getSimpleName();
    private ViewGroup mTabView;
    private int mLastItemPos;
    private boolean mIsTabShpw; //tab是否展示
    private boolean isForbidScroll; //单个tab就不存在展示问题
    private Animation mDownanimation,mUpanimation;

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

    public void showTab(){
        if(isForbidScroll){
            return;
        }
        if(mTabView.getVisibility() != View.VISIBLE){
            mTabView.setClickable(false);
            mTabView.setEnabled(false);
            mUpanimation = new TranslateAnimation(0, 0, mTabView.getHeight(), 0);
            mUpanimation.setDuration(200);
            mTabView.startAnimation(mUpanimation);
            mUpanimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    LOGGER.d(TAG, "showTab");
                    mTabView.setVisibility(View.VISIBLE);
                    mTabView.setClickable(true);
                    mTabView.setEnabled(true);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mIsTabShpw = true;
        }
    }

    public void hideTab(){
        mTabView.setClickable(false);
        mTabView.setEnabled(false);
        mDownanimation = new TranslateAnimation(0, 0, 0, mTabView.getHeight());
        mDownanimation.setDuration(200);
        mTabView.startAnimation(mDownanimation);
        mDownanimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mTabView.setVisibility(View.GONE);
                LOGGER.d(TAG, "hideTab");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTabView.setClickable(true);
                mTabView.setEnabled(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mIsTabShpw = false;
    }

    public void cancelAnimation() {
        LOGGER.d(TAG, "cancelAnimation");
        if(mDownanimation != null && mTabView != null && mTabView.getAnimation() != null){
            LOGGER.d(TAG, "canceldAnimation");
            mTabView.getAnimation().cancel();
            mTabView.clearAnimation();
            mDownanimation.setAnimationListener(null);
            mTabView.setAnimation(null);
        }
        if(mUpanimation != null  && mTabView != null && mTabView.getAnimation() != null){
            LOGGER.d(TAG, "canceluAnimation");
            mTabView.getAnimation().cancel();
            mTabView.clearAnimation();
            mUpanimation.setAnimationListener(null);
            mTabView.setAnimation(null);
        }
    }
}
