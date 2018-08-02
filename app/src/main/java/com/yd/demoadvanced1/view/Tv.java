package com.yd.demoadvanced1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yd.demoadvanced1.R;

public class Tv extends View {

    Paint mTextPaint;
    Paint mBorderPaint;
    int textColor;
    float textSzie;
    String textString = "TEXT";
    float textHeight;

    public Tv(Context context) {
        this(context,null);
    }

    public Tv(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Tv(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.tv_styleable);
        textString = ta.getString(R.styleable.tv_styleable_text_string);
        textSzie = ta.getDimensionPixelSize(R.styleable.tv_styleable_text_size,15);
        textColor = ta.getColor(R.styleable.tv_styleable_text_color, Color.BLACK);
        textHeight = ta.getDimension(R.styleable.tv_styleable_text_height, 50f);
        ta.recycle();
        mTextPaint = new Paint();
        mBorderPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(textSzie);

        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(Color.BLACK);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(2);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(150, widthMeasureSpec);
        int height = getMySize(150, heightMeasureSpec);

//        if (width < height) {
//            height = width;
//        } else {
//            width = height;
//        }
        Log.i("onMeasure", "getWidth: "+getWidth()+"  getMeasuredWidth"+getMeasuredWidth()+"  getTop"+getTop()+"  getLeft"+getLeft()+"  getHeight:"+getHeight());
        setMeasuredDimension(width, height);
    }


    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                // 如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            //如果测量模式是最大取值为size
            //我们将大小取最大值,你也可以取其他值
            case MeasureSpec.AT_MOST:
                mySize = defaultSize;
                break;
            // 如果是固定的大小，那就不要去改变它
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }
        return mySize;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("onDraw1", "getWidth： "+getWidth()+"  getMeasuredWidth："+getMeasuredWidth()+"  getTop："+getTop()+"  getLeft："+getLeft()+"  getHeight:"+getHeight());
        Log.i("onDraw2", "getPaddingLeft： "+getPaddingLeft()+"  getPaddingRight："+getPaddingRight()+"  getPaddingTop："+getPaddingTop()+"  getPaddingBottom："+getPaddingBottom());
        Log.i("onDraw3", "textHeight： "+textHeight);

        canvas.drawText(textString, getWidth()/2-mTextPaint.measureText(textString)/2, getHeight()/2-(mTextPaint.descent()+mTextPaint.ascent())/2, mTextPaint);
        Log.i("onDraw4", "canvas.getHeight： "+canvas.getHeight());
        Log.i("onDraw5", "getHeight()： "+getHeight());
        // 画空心矩形
        canvas.drawRect(getPaddingLeft(), getPaddingTop() , getWidth(), getHeight(), mBorderPaint);
    }
}
