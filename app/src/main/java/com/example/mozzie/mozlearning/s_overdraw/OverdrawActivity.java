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
import android.view.View;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.p_recycleview.RecycleviewActivity;

/**
 * Created by mozzie on 17/9/11.
 */

public class OverdrawActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OverdrawDrawable overdraw = new OverdrawDrawable();
        getWindow().setBackgroundDrawable(overdraw);

        setContentView(R.layout.activity_carshiftprice_layout);

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(overdraw, "colorValue", 0, 255);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
//        objectAnimator.start();
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
