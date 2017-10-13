package com.example.lihang.mypractice.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.lihang.mypractice.R;

import java.util.Timer;
import java.util.TimerTask;

public class SurfaceActivity extends AppCompatActivity {

    private SurfaceHolder holder;
    private Paint paint;

    final int X_OFF=5;
    final int Height=480;
    final int WIDTH=1520;

    private int cx;
    int centerY=Height/2;

    Timer timer=new Timer();
    TimerTask task=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);

        SurfaceView surfaceView= (SurfaceView) findViewById(R.id.show);
        holder=surfaceView.getHolder();


        paint=new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);

        Button sin= (Button) findViewById(R.id.sin);
        Button cos= (Button) findViewById(R.id.cos);

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                drawBack(holder);
                cx=X_OFF;
                if(task!=null){
                    task.cancel();
                }
                task=new TimerTask() {
                    @Override
                    public void run() {
                        int cy= (int) (view.getId()==R.id.sin? centerY-100*Math.cos((cx-5)*2*Math.PI/150)
                                                        :centerY-100*Math.sin((cx-5)*2*Math.PI/150));
                        Canvas canvas=holder.lockCanvas(new Rect(cx,cy-2,cx+2,cy+2));
                        canvas.drawPoint(cx,cy,paint);
                        cx++;
                        if(cx>WIDTH){
                            task.cancel();
                            task=null;
                        }
                        holder.unlockCanvasAndPost(canvas);
                    }
                };
                timer.schedule(task,0,10);
            }
        };

        sin.setOnClickListener(listener);
        cos.setOnClickListener(listener);

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                drawBack(surfaceHolder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                task.cancel();
            }
        });
    }

    private void drawBack(SurfaceHolder holder){
        Canvas canvas=holder.lockCanvas();
        canvas.drawColor(Color.WHITE);
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);

        canvas.drawLine(X_OFF,centerY,WIDTH,centerY,paint);
        canvas.drawLine(X_OFF,40,X_OFF,Height,paint);

        holder.unlockCanvasAndPost(canvas);

        holder.lockCanvas(new Rect(0,0,0,0));
        holder.unlockCanvasAndPost(canvas);
    }

}
