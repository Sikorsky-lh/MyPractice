package com.example.lihang.mypractice.activity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lihang.mypractice.R;

public class AnimDrawableActivity extends AppCompatActivity implements OnClickListener{
    AnimationDrawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_drawable);

        ImageView imageView= (ImageView) findViewById(R.id.img_anim);

        Button play= (Button) findViewById(R.id.play);
        Button stop= (Button) findViewById(R.id.stop);

        play.setOnClickListener(this);
        stop.setOnClickListener(this);

        drawable= (AnimationDrawable) imageView.getBackground();

        Drawable addDrawable=getResources().getDrawable(R.drawable.plane);
        drawable.addFrame(addDrawable,300);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.play:
                drawable.start();
                break;
            case R.id.stop:
                drawable.stop();
                break;
        }
    }
}
