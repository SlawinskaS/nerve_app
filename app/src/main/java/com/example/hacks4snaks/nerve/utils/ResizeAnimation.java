package com.example.hacks4snaks.nerve.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by fglow on 07.03.2018.
 */

public class ResizeAnimation extends Animation {
    final int targetHeight;
    final int targetWidth;
    View view;
    int startHeight;
    int startWidth;

    public ResizeAnimation(View view, int targetHeight, int startHeight, int targetWidth, int startWidth) {
        this.view = view;
        this.targetHeight = targetHeight;
        this.startHeight = startHeight;
        this.targetWidth = targetWidth;
        this.startWidth = startWidth;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
//        int newHeight = (int) (startHeight + targetHeight * interpolatedTime);
//        int newWidth = (int) (startWidth + targetWidth * interpolatedTime);
        //to support decent animation, change new heigt as Nico S. recommended in comments
        int newHeight = (int) (startHeight+(targetHeight - startHeight) * interpolatedTime);
        int newWidth = (int) (startWidth+(targetWidth - startWidth) * interpolatedTime);
        view.getLayoutParams().height = newHeight;
        view.getLayoutParams().width = newWidth;
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
