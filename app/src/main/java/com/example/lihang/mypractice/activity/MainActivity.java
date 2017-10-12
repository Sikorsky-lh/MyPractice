package com.example.lihang.mypractice.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lihang.mypractice.R;
import com.example.lihang.mypractice.SImpleEnumUse;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private EditText et_main;
    private Button btn;

    public static final int MSG=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Timer timer=new Timer();
        final ImageView imageView= (ImageView) findViewById(R.id.et);
//        final ClipDrawable clipDrawable= (ClipDrawable) imageView.getDrawable();
//
//        final Handler handler=new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//                if(msg.what==MSG){
//                    clipDrawable.setLevel(clipDrawable.getLevel()+200);
//                }
//            }
//        };

        btn= (Button) findViewById(R.id.btn);
//        et_main= (EditText) findViewById(R.id.et);
        final Animation anim= AnimationUtils.loadAnimation(MainActivity.this,R.anim.my_anmi);
//        anim.setFillAfter(true);

        final String str=getResources().getString(R.string.app_name);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn instanceof Button){
                    Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
                }
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        Message msg=new Message();
//                        msg.what=MSG;
//                        handler.sendMessage(msg);
//                        if(clipDrawable.getLevel()>=10000){
//                            this.cancel();
//                        }
//                    }
//                },0,100);

                ObjectAnimator colorAnim= (ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this,R.animator.color_anim);
                colorAnim.setEvaluator(new ArgbEvaluator());
                colorAnim.setTarget(findViewById(R.id.main_container));
                colorAnim.start();
                imageView.startAnimation(anim);
            }
        });

        ObjectAnimator animator=ObjectAnimator.ofFloat(btn,"alpha",1f,0f);
        animator.start();
        animator.setDuration(3000);
        animator.setRepeatCount(4);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(MainActivity.this,"按钮消失了",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent();
        switch (item.getItemId()){
            case R.id.next:
                intent.setClass(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.path_effect:
                intent.setClass(MainActivity.this,PathActivity.class);
                startActivity(intent);
                break;
            case R.id.anim_drawable:
                intent.setClass(MainActivity.this,AnimDrawableActivity.class);
                startActivity(intent);
                break;
            case R.id.animator:
                intent.setClass(MainActivity.this,AnimatorActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
