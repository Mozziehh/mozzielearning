package com.example.mozzie.mozlearning.b_utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.mozzie.mozlearning.R;

import static com.example.mozzie.mozlearning.b_utils.BubbleUtils.sp2px;

/**
 * Created by mozzie on 17/8/2.
 */

public class BubbleRangeSeekBar extends View {
    /**
     * 画笔
     */
    private Paint paint = new Paint();
    /**
     * 底部背景的上下左右
     */
    private int lineTop, lineBottom, lineLeft, lineRight;
    /**
     * 底部背景的圆角
     */
    private int lineCorners;
    /**
     * 底部背景的宽度
     */
    private int lineWidth;
    /**
     * 底部背景的高度
     */
    private int mHeight; //View的高度
    /**
     * 浮点型的矩形
     */
    private RectF line = new RectF();
    /**
     * 滑动时的颜色
     */
    private int colorLineSelected;
    /**
     * 滑动时线条边缘
     */
    private int colorLineEdge;
    /**
     * 左侧的seekbar
     */
    private SeekBar leftSB = new SeekBar();
    /**
     * 右侧的seekbar
     */
    private SeekBar rightSB = new SeekBar();
    /**
     * 当前触摸的控件
     */
    private SeekBar currTouch;
    /**
     * 触摸事件的回调
     */
    private OnRangeChangedListener callback;
    /**
     * seekbar触摸按钮资源图片id
     */
    private int seekBarResId;
    /**
     * 偏移量
     */
    private float offsetValue;
    /**
     * 最大最小值
     */
    private float maxValue, minValue;
    /**
     * 默认间隔数
     */
    private int cellsCount = 1;
    /**
     * 底部文字相关
     */
    private float cellsPercent;
    private float reserveValue;
    private int reserveCount;
    private float reservePercent;
    private int mSpacerCount=6;
    private int textSize=50;
    //雨滴相关
    private int mDropTextColor;
    private int mDropTextSize;
    private float mCurrentX, mCurrentY;
    private boolean isDrawDrop,isDropMove;

    private float mCurrentPercent = 0;
    /**
     * 滑动变化监听借口
     */
    public interface OnRangeChangedListener {
        void onRangeChanged(BubbleRangeSeekBar view, float min, float max);
    }

    /**
     * 设置监听
     * @param listener
     */
    public void setOnRangeChangedListener(OnRangeChangedListener listener) {
        callback = listener;
    }

    /**
     * 构造函数
     */
    public BubbleRangeSeekBar(Context context) {
        this(context, null);
    }

    /**
     * 构造函数
     * @param context
     * @param attrs
     * 设置自定义布局和样式
     */
    public BubbleRangeSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.BubbleRangeSeekBar);
        seekBarResId = t.getResourceId(R.styleable.BubbleRangeSeekBar_seekBarResId, R.drawable.car_price_drawer);
        colorLineSelected = t.getColor(R.styleable.BubbleRangeSeekBar_lineColorSelected, 0xFF4BD962);
        colorLineEdge = t.getColor(R.styleable.BubbleRangeSeekBar_lineColorEdge, 0xFFD7D7D7);
        mDropTextSize = t.getInt(R.styleable.BubbleRangeSeekBar_bsb_bubble_drop_size, sp2px(20));
        mDropTextColor = t.getColor(R.styleable.BubbleRangeSeekBar_bsb_bubble_drop_color, 0xFFFFFFFF);

        float min = t.getFloat(R.styleable.BubbleRangeSeekBar_min, 0);
        float max = t.getFloat(R.styleable.BubbleRangeSeekBar_max, 1);
        float reserve = t.getFloat(R.styleable.BubbleRangeSeekBar_reserve, 0);
        int cells = t.getInt(R.styleable.BubbleRangeSeekBar_cells, 1);
        setRules(min, max, reserve, cells);
        t.recycle();
    }

    /**
     * 状态保存
     * @return
     */
    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.minValue = minValue - offsetValue;
        ss.maxValue = maxValue - offsetValue;
        ss.reserveValue = reserveValue;
        ss.cellsCount = cellsCount;
        float[] results = getCurrentRange();
        ss.currSelectedMin = results[0];
        ss.currSelectedMax = results[1];
        return ss;
    }

    /**
     * 状态保存
     * @return
     */
    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        float min = ss.minValue;
        float max = ss.maxValue;
        float reserve = ss.reserveValue;
        int cells = ss.cellsCount;
        setRules(min, max, reserve, cells);
        float currSelectedMin = ss.currSelectedMin;
        float currSelectedMax = ss.currSelectedMax;
        setValue(currSelectedMin, currSelectedMax);
    }

    public void setValue(float min, float max) {
        min = min + offsetValue;
        max = max + offsetValue;

        if (min < minValue) {
            throw new IllegalArgumentException("setValue() min < (preset min - offsetValue) . #min:" + min + " #preset min:" + minValue + " #offsetValue:" + offsetValue);
        }
        if (max > maxValue) {
            throw new IllegalArgumentException("setValue() max > (preset max - offsetValue) . #max:" + max + " #preset max:" + maxValue + " #offsetValue:" + offsetValue);
        }

        if (reserveCount > 1) {
            if ((min - minValue) % reserveCount != 0) {
                throw new IllegalArgumentException("setValue() (min - preset min) % reserveCount != 0 . #min:" + min + " #preset min:" + minValue + "#reserveCount:" + reserveCount + "#reserve:" + reserveValue);
            }
            if ((max - minValue) % reserveCount != 0) {
                throw new IllegalArgumentException("setValue() (max - preset min) % reserveCount != 0 . #max:" + max + " #preset min:" + minValue + "#reserveCount:" + reserveCount + "#reserve:" + reserveValue);
            }
            leftSB.currPercent = (min - minValue) / reserveCount * cellsPercent;
            rightSB.currPercent = (max - minValue) / reserveCount * cellsPercent;
        } else {
            leftSB.currPercent = (min - minValue) / (maxValue - minValue);
            rightSB.currPercent = (max - minValue) / (maxValue - minValue);
        }

        invalidate();
    }

    private static class SavedState extends BaseSavedState {
        private float minValue;
        private float maxValue;
        private float reserveValue;
        private int cellsCount;
        private float currSelectedMin;
        private float currSelectedMax;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            minValue = in.readFloat();
            maxValue = in.readFloat();
            reserveValue = in.readFloat();
            cellsCount = in.readInt();
            currSelectedMin = in.readFloat();
            currSelectedMax = in.readFloat();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeFloat(minValue);
            out.writeFloat(maxValue);
            out.writeFloat(reserveValue);
            out.writeInt(cellsCount);
            out.writeFloat(currSelectedMin);
            out.writeFloat(currSelectedMax);
        }
    }

    /**
     * 获取最大值
     * @return
     */
    public float getMaxValue(){
        return maxValue;
    }

    /**
     * 获取最小值
     * @return
     */
    public float getMinValue(){
        return minValue;
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightSize * 1.8f > widthSize) {
            setMeasuredDimension(widthSize, (int) (widthSize / 1.8f));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 当view发生变化的时候需要做的事
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     * 设置背景的上下左右，然后调用两个seekbar的onSizeChanged
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight=h;
        int seekBarRadius = h / 10; //半径为高度的五分之一

        lineLeft = seekBarRadius;
        lineRight = w - seekBarRadius;
        lineTop = DisplayUtil.dip2px(getContext(), 82);
        lineBottom = DisplayUtil.dip2px(getContext(), 86);
        lineWidth = lineRight - lineLeft;
        line.set(lineLeft, lineTop, lineRight, lineBottom);
        lineCorners = (int) ((lineBottom - lineTop) * 0.45f);

        leftSB.onSizeChanged(seekBarRadius, seekBarRadius + DisplayUtil.dip2px(getContext(), 82), h, lineWidth, cellsCount > 1, seekBarResId, getContext());
        rightSB.onSizeChanged(seekBarRadius, seekBarRadius + DisplayUtil.dip2px(getContext(), 82), h, lineWidth, cellsCount > 1, seekBarResId, getContext());

        if (cellsCount == 1) {
            rightSB.left += leftSB.widthSize;
            rightSB.right += leftSB.widthSize;
        }
    }

    /**
     * 画圆角矩形
     * @param canvas
     * 画seekbar线条，画底部文字
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 画背景线
         */
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(colorLineEdge);
        canvas.drawRoundRect(line, lineCorners, lineCorners, paint);

        /**
         * 绘制底部数值文字
         */
        paint.setColor(Color.parseColor("#555555"));
        float dif=(maxValue-minValue)/mSpacerCount;
        paint.setTextSize(DisplayUtil.dip2px(getContext(), 14));
        paint.setAntiAlias(true);
        if(dif < 1.0) {
            mSpacerCount = (int)(maxValue-minValue);
            dif = 1.0f;
        }
        float spacerWidth=(float)lineWidth/mSpacerCount;
        int textBottom =mHeight/24*18;
        for(int i=0;i<mSpacerCount+1;i++){
            float textleft=lineLeft*0.8f+spacerWidth*i;
            int text=(int)(minValue+dif*i);
            String numtext=text+"万";
            if(i==mSpacerCount){
                numtext="不限";
            }
            float numtextWidth=paint.measureText(numtext);
            if(i == 0) {
                canvas.drawText(numtext+"",textleft,textBottom,paint);
            } else if(i < mSpacerCount){
                canvas.drawText(numtext+"",textleft - DisplayUtil.dip2px(getContext(), 12),textBottom,paint);
            } else {
                canvas.drawText(numtext+"",textleft - DisplayUtil.dip2px(getContext(), 16),textBottom,paint);
            }
        }

        /**
         * 画选择线
         */
        paint.setColor(colorLineSelected);
        canvas.drawRect(leftSB.left + leftSB.widthSize / 2 + leftSB.lineWidth * leftSB.currPercent, lineTop,
                rightSB.left + rightSB.widthSize / 2 + rightSB.lineWidth * rightSB.currPercent, lineBottom, paint);

        leftSB.draw(canvas);
        rightSB.draw(canvas);

        /**
         * 画小雨滴
         */
        if(isDrawDrop){
            drawDrop(canvas);
        }
    }

    /**
     * 画小雨滴
     * @param canvas
     */
    private void drawDrop(final Canvas canvas) {

        float minLeft = leftSB.left + leftSB.widthSize / 2 + leftSB.lineWidth * leftSB.currPercent;
        float maxRight = rightSB.left + rightSB.widthSize / 2 + rightSB.lineWidth * rightSB.currPercent;

        float bitmapLeft = lineWidth * mCurrentPercent;
        float textLeft = lineWidth * mCurrentPercent;

        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.car_price_drag_tips);
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) 0.75);
        float scaleHeight = ((float) 0.75);
        matrix.postScale(scaleWidth, scaleHeight);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        if(currTouch == leftSB){
            canvas.drawBitmap(bitmap, bitmapLeft - DisplayUtil.dip2px(getContext(), 6), DisplayUtil.dip2px(getContext(), 56) - leftSB.widthSize, paint);
        }else{
            canvas.drawBitmap(bitmap, bitmapLeft > lineRight ? lineRight - leftSB.widthSize/2 : bitmapLeft - DisplayUtil.dip2px(getContext(), 6), DisplayUtil.dip2px(getContext(), 56) - leftSB.widthSize, paint);
        }

        if (bitmap.isRecycled()) { // 判断图片是否回收
            bitmap.recycle(); // 强制回收图片
        }

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
        paint.setTextSize(DisplayUtil.dip2px(getContext(), 20));
        String currentTag = "";

        if(currTouch == leftSB){
            int currentRange = (int)(getCurrentRange()[0]);
            currentTag = (String.valueOf(currentRange));
            if(currentRange < 10){
                canvas.drawText(currentTag, textLeft + DisplayUtil.dip2px(getContext(), 10), DisplayUtil.dip2px(getContext(), 56), paint);
            }else{
                canvas.drawText(currentTag, textLeft < lineLeft ? lineLeft : textLeft + DisplayUtil.dip2px(getContext(), 5), DisplayUtil.dip2px(getContext(), 56), paint);
            }
        }else{
            int currentRange = (int)(getCurrentRange()[1]);
            currentTag = (String.valueOf(currentRange));
            if(currentRange < 10){
                canvas.drawText(currentTag, textLeft + DisplayUtil.dip2px(getContext(), 10), DisplayUtil.dip2px(getContext(), 56), paint);
            }else{
                canvas.drawText(currentTag, textLeft < lineLeft ? lineLeft: textLeft + DisplayUtil.dip2px(getContext(), 5), DisplayUtil.dip2px(getContext(), 56), paint);
            }
        }

    }

    /**
     * 处理触摸事件
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                break;
            }
            case MotionEvent.ACTION_UP: {
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 处理触摸事件，触发左右滑动时使用
     * @param
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                isDrawDrop = true;
                boolean touchResult = false;
                if (rightSB.currPercent >= 1 && leftSB.collide(event)) {
                    currTouch = leftSB;
                    touchResult = true;
                } else if (rightSB.collide(event)) {
                    currTouch = rightSB;
                    touchResult = true;
                } else if (leftSB.collide(event)) {
                    currTouch = leftSB;
                    touchResult = true;
                }
                return touchResult;
            case MotionEvent.ACTION_MOVE:
                float percent = 0;
                float x = event.getX();
                float y = event.getY();
                mCurrentX = x;
                mCurrentY = y;
                isDrawDrop = true;
                currTouch.material = currTouch.material >= 1 ? 1 : currTouch.material + 0.1f;

                if (currTouch == leftSB) {
                    if (cellsCount > 1) {
                        if (x < lineLeft) {
                            percent = 0;
                        } else {
                            percent = (x - lineLeft) * 1f / (lineWidth);
                        }
                        int touchLeftCellsValue = Math.round(percent / cellsPercent);
                        int currRightCellsValue = Math.round(rightSB.currPercent / cellsPercent);
                        percent = touchLeftCellsValue * cellsPercent;

                        while (touchLeftCellsValue > currRightCellsValue - reserveCount) {
                            touchLeftCellsValue--;
                            if (touchLeftCellsValue < 0) break;
                            percent = touchLeftCellsValue * cellsPercent;
                        }
                    } else {
                        if (x < lineLeft) {
                            percent = 0;
                        } else {
                            percent = (x - lineLeft) * 1f / (lineWidth - rightSB.widthSize);
                        }

                        if (percent > rightSB.currPercent - reservePercent) {
                            percent = rightSB.currPercent - reservePercent;
                        }
                    }
                    leftSB.slide(percent);
                } else if (currTouch == rightSB) {
                    if (cellsCount > 1) {
                        if (x > lineRight) {
                            percent = 1;
                        } else {
                            percent = (x - lineLeft) * 1f / (lineWidth);
                        }
                        int touchRightCellsValue = Math.round(percent / cellsPercent);
                        int currLeftCellsValue = Math.round(leftSB.currPercent / cellsPercent);
                        percent = touchRightCellsValue * cellsPercent;

                        while (touchRightCellsValue < currLeftCellsValue + reserveCount) {
                            touchRightCellsValue++;
                            if (touchRightCellsValue > maxValue - minValue) break;
                            percent = touchRightCellsValue * cellsPercent;
                        }
                    } else {
                        if (x > lineRight) {
                            percent = 1;
                        } else {
                            percent = (x - lineLeft - leftSB.widthSize) * 1f / (lineWidth - leftSB.widthSize);
                        }
                        if (percent < leftSB.currPercent + reservePercent) {
                            percent = leftSB.currPercent + reservePercent;
                        }
                    }
                    rightSB.slide(percent);
                }

                if (callback != null) {
                    float[] result = getCurrentRange();
                    callback.onRangeChanged(this, result[0], result[1]);
                }

//                isDrawDrop = !(percent == 0);
                mCurrentPercent = percent;
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                currTouch.materialRestore();

                if (callback != null) {
                    float[] result = getCurrentRange();
                    callback.onRangeChanged(this, result[0], result[1]);
                }
                isDrawDrop = false;
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * seekbar内部类
     */
    private class SeekBar {
        RadialGradient shadowGradient;
        Paint defaultPaint;
        int lineWidth;
        int widthSize, heightSize;
        float currPercent;
        int left, right, top, bottom;
        Bitmap bmp;

        float material = 0;
        ValueAnimator anim;
        final TypeEvaluator<Integer> te = new TypeEvaluator<Integer>() {
            @Override
            public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
                int alpha = (int) (Color.alpha(startValue) + fraction * (Color.alpha(endValue) - Color.alpha(startValue)));
                int red = (int) (Color.red(startValue) + fraction * (Color.red(endValue) - Color.red(startValue)));
                int green = (int) (Color.green(startValue) + fraction * (Color.green(endValue) - Color.green(startValue)));
                int blue = (int) (Color.blue(startValue) + fraction * (Color.blue(endValue) - Color.blue(startValue)));
                return Color.argb(alpha, red, green, blue);
            }
        };

        /**
         * 每次调用draw方法时会触发该逻辑
         * @param centerX
         * @param centerY
         * @param hSize
         * @param parentLineWidth
         * @param cellsMode
         * @param bmpResId
         * @param context
         * 设置滑动的效果
         */
        void onSizeChanged(int centerX, int centerY, int hSize, int parentLineWidth, boolean cellsMode, int bmpResId, Context context) {
            heightSize = hSize/10*2; //设置直径为高度的2/5
            widthSize = (int) (heightSize * 0.9f);
            left = centerX - widthSize / 2;
            right = centerX + widthSize / 2;
            top = centerY - heightSize / 2;
            bottom = centerY + heightSize / 2;

            if (cellsMode) {
                lineWidth = parentLineWidth;
            } else {
                lineWidth = parentLineWidth - widthSize;
            }

            if (bmpResId > 0) {
                Bitmap original = BitmapFactory.decodeResource(context.getResources(), bmpResId);
                Matrix matrix = new Matrix();
                float scaleWidth = (float)0.75;
                float scaleHeight = (float)0.75;
                matrix.postScale(scaleWidth, scaleHeight);
                bmp = Bitmap.createBitmap(original, 0, 0, original.getWidth(), original.getHeight(), matrix, true);
            } else {
                defaultPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                int radius = (int) (widthSize * 0.5f);
                int barShadowRadius = (int) (radius * 0.95f);
                int mShadowCenterX = widthSize / 2;
                int mShadowCenterY = heightSize / 2;
                shadowGradient = new RadialGradient(mShadowCenterX, mShadowCenterY, barShadowRadius, Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP);
            }
        }

        /**
         * 触摸时手不离卡屏幕划出依然生效
         * @param event
         * @return
         */
        boolean collide(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            int offset = (int) (lineWidth * currPercent);
            int addTouchRange = 2*widthSize;
            return x > left + offset- addTouchRange && x < right + offset+ addTouchRange && y > top - addTouchRange && y < bottom + addTouchRange;
        }

        void slide(float percent) {
            if (percent < 0) percent = 0;
            else if (percent > 1) percent = 1;
            currPercent = percent;
        }

        void draw(Canvas canvas) {
            int offset = (int) (lineWidth * currPercent);
            canvas.save();
            canvas.translate(offset, 0);
            if (bmp != null) {
                canvas.drawBitmap(bmp, left - DisplayUtil.dip2px(getContext(), 8), top - DisplayUtil.dip2px(getContext(), 20), null);
            } else {
                canvas.translate(left, 0);
                drawDefault(canvas);
            }
            canvas.restore();
        }

        private void drawDefault(Canvas canvas) {
            int centerX = widthSize / 2;
            int centerY = heightSize / 2;
            int radius = (int) (widthSize * 0.5f);
            // draw shadow
            defaultPaint.setStyle(Paint.Style.FILL);
            canvas.save();
            canvas.translate(0, radius * 0.25f);
            canvas.scale(1 + (0.1f * material), 1 + (0.1f * material), centerX, centerY);
            defaultPaint.setShader(shadowGradient);
            canvas.drawCircle(centerX, centerY, radius, defaultPaint);
            defaultPaint.setShader(null);
            canvas.restore();
            // draw body
            defaultPaint.setStyle(Paint.Style.FILL);
            defaultPaint.setColor(te.evaluate(material, 0xFFFFFFFF, 0xFFE7E7E7));
            canvas.drawCircle(centerX, centerY, radius, defaultPaint);
            // draw border
            defaultPaint.setStyle(Paint.Style.STROKE);
            defaultPaint.setStrokeWidth(4);
            defaultPaint.setColor(0xFFFF552E);
            canvas.drawCircle(centerX, centerY, radius, defaultPaint);
        }

        private void materialRestore() {
            if (anim != null) anim.cancel();
            anim = ValueAnimator.ofFloat(material, 0);
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    material = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    material = 0;
                    invalidate();
                }
            });
            anim.start();
        }
    }

    public void setRules(float min, float max) {
        setRules(min, max, reserveCount, cellsCount);
    }

    public void setRules(float min, float max, float reserve, int cells) {
        if (max <= min) {
            throw new IllegalArgumentException("setRules() max must be greater than min ! #max:" + max + " #min:" + min);
        }
        if (min < 0) {
            offsetValue = 0 - min;
            min = min + offsetValue;
            max = max + offsetValue;
        }
        minValue = min;
        maxValue = max;

        if (reserve < 0) {
            throw new IllegalArgumentException("setRules() reserve must be greater than zero ! #reserve:" + reserve);
        }
        if (reserve >= max - min) {
            throw new IllegalArgumentException("setRules() reserve must be less than (max - min) ! #reserve:" + reserve + " #max - min:" + (max - min));
        }
        if (cells < 1) {
            throw new IllegalArgumentException("setRules() cells must be greater than 1 ! #cells:" + cells);
        }
        cellsCount = cells;
        cellsPercent = 1f / cellsCount;
        reserveValue = reserve;
        reservePercent = reserve / (max - min);
        reserveCount = (int) (reservePercent / cellsPercent + (reservePercent % cellsPercent != 0 ? 1 : 0));
        if (cellsCount > 1) {
            if (leftSB.currPercent + cellsPercent * reserveCount <= 1 && leftSB.currPercent + cellsPercent * reserveCount > rightSB.currPercent) {
                rightSB.currPercent = leftSB.currPercent + cellsPercent * reserveCount;
            } else if (rightSB.currPercent - cellsPercent * reserveCount >= 0 && rightSB.currPercent - cellsPercent * reserveCount < leftSB.currPercent) {
                leftSB.currPercent = rightSB.currPercent - cellsPercent * reserveCount;
            }
        } else {
            if (leftSB.currPercent + reservePercent <= 1 && leftSB.currPercent + reservePercent > rightSB.currPercent) {
                rightSB.currPercent = leftSB.currPercent + reservePercent;
            } else if (rightSB.currPercent - reservePercent >= 0 && rightSB.currPercent - reservePercent < leftSB.currPercent) {
                leftSB.currPercent = rightSB.currPercent - reservePercent;
            }
        }
        invalidate();
    }

    public float[] getCurrentRange() {
        float range = maxValue - minValue;
        return new float[]{-offsetValue + minValue + range * leftSB.currPercent,
                -offsetValue + minValue + range * rightSB.currPercent};
    }
}


