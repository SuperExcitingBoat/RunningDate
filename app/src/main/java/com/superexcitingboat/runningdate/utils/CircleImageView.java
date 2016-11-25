package com.superexcitingboat.runningdate.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by PinkD on 2016/8/19.
 * CircleImageView
 */
public class CircleImageView extends ImageView {
    private Path circle;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circle = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float radius = (float) min(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        circle.addCircle(radius, radius, radius, Path.Direction.CW);
        canvas.clipPath(circle);
        super.onDraw(canvas);
    }

    private double min(double a, double b) {
        return a < b ? a : b;
    }
}
