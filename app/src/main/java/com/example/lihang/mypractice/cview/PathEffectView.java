package com.example.lihang.mypractice.cview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lihang on 2017/10/11.
 */

public class PathEffectView extends View {
    Paint mPaint;
    Path path;

    float phase;
    PathEffect[] effects=new PathEffect[7];
    int[] colors;
    public PathEffectView(Context context) {
        super(context);
        initPaint();
        path=new Path();
        path.moveTo(0,0);
        for(int i=1;i<=40;i++){
            path.lineTo(i*20, (float) (Math.random()*60));
        }
        colors=new int[]{Color.BLACK,Color.BLUE,Color.YELLOW,Color.RED, Color.GREEN,Color.GRAY,Color.CYAN, Color.MAGENTA};
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        effects[0]=null;
        effects[1]=new CornerPathEffect(10);
        effects[2]=new DiscretePathEffect(3.0f,5.0f);
        effects[3]=new DashPathEffect(new float[]{20,20,5,10},phase);
        Path p=new Path();
        p.addRect(0,0,8,8, Path.Direction.CCW);

        effects[4]=new PathDashPathEffect(p,12,phase,PathDashPathEffect.Style.ROTATE);
        effects[5]=new ComposePathEffect(effects[2],effects[3]);
        effects[6]=new SumPathEffect(effects[4],effects[3]);

        canvas.translate(8,8);
        for(int i=0;i<effects.length;i++){
            mPaint.setPathEffect(effects[i]);
            mPaint.setColor(colors[i]);
            canvas.drawPath(path,mPaint);
            canvas.translate(0,60);
        }

        phase+=0.1;
        invalidate();
    }


    private void initPaint(){
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
    }
}
