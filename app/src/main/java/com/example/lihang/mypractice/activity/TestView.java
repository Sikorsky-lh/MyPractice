package com.example.lihang.mypractice.activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.lihang.mypractice.R;

/**
 * Created by lihang on 2017/10/10.
 */

public class TestView extends View {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.TestView);
        typedArray.getInt(R.styleable.TestView_duration,0);
    }
}
