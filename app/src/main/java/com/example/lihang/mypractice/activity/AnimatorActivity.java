package com.example.lihang.mypractice.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.lihang.mypractice.R;
import com.example.lihang.mypractice.cview.MyAnimationView;

public class AnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        LinearLayout ll= (LinearLayout) findViewById(R.id.animator_container);
        ll.addView(new MyAnimationView(this));
    }
}
