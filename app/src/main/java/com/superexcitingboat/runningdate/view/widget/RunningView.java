package com.superexcitingboat.runningdate.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.superexcitingboat.runningdate.R;

import static android.graphics.Paint.Cap.ROUND;

/**
 * Created by xushuzhan on 2016/11/22.
 */

public class RunningView extends ImageView {
    private Paint mPaint;
    private String time = "00:00:00";
    private int width;
    private int height;

    public RunningView(Context context) {
        super(context);
        mPaint = new Paint();
        width = getWidth();
        height = getHeight();
    }

    public RunningView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        width = getWidth();
        height = getHeight();
    }

    public RunningView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        width = getWidth();
        height = getHeight();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RunningView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mPaint = new Paint();
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        mPaint.setColor(getResources().getColor(R.color.shi_jian));
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(width / 8);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float textHeight = (-fontMetrics.ascent - fontMetrics.descent) / 2;
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setStrokeCap(ROUND);
        canvas.drawText(time, width / 2, height / 2 + textHeight, mPaint);
    }

    public void setTime(String time) {
        this.time = time;
        invalidate();
    }
}
