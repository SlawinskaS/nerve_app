package com.example.hacks4snaks.nerve.utils;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by fglow on 07.03.2018.
 */

public class PulseAnimation extends Animation {
    int targetStart;
    int targetTop;
    View view;
    int startStart;
    int startTop;
    int tmpStart;
    int tmpTop;

    int targetHeight;
    int startHeight;
    int tmpHeight;
    Resources r;

    public PulseAnimation(View view) {
        this.r = view.getResources();
        this.view = view;
        this.targetHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 26, r.getDisplayMetrics());
        this.startHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, r.getDisplayMetrics());
        this.targetStart = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 56, r.getDisplayMetrics());
        this.startStart = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 29, r.getDisplayMetrics());
        this.targetTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, r.getDisplayMetrics());
        this.startTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, r.getDisplayMetrics());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newStart = (int) (startStart+(targetStart - startStart) * interpolatedTime);
        int newTop = (int) (startTop+(targetTop - startTop) * interpolatedTime);
        int newHeight = (int) (startHeight+(targetHeight - startHeight) * interpolatedTime);

        if(newStart >= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, r.getDisplayMetrics())) {
            this.targetHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, r.getDisplayMetrics());
            this.startHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, r.getDisplayMetrics());
            this.targetTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, r.getDisplayMetrics());
            this.startTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics());
        } else if(newStart >= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, r.getDisplayMetrics())) {
            this.targetHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, r.getDisplayMetrics());
            this.startHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, r.getDisplayMetrics());
            this.targetTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics());
            this.startTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, r.getDisplayMetrics());
        } else {
            this.targetHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36, r.getDisplayMetrics());
            this.startHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, r.getDisplayMetrics());
            this.targetTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, r.getDisplayMetrics());
            this.startTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31, r.getDisplayMetrics());
        }

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(newStart,newTop,0,0);
        view.setLayoutParams(layoutParams);
        view.getLayoutParams().height = newHeight;
        view.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics());
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
