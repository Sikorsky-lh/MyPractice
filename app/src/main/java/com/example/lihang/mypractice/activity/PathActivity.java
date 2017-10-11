package com.example.lihang.mypractice.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.lihang.mypractice.R;
import com.example.lihang.mypractice.cview.PathEffectView;

public class PathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);

        LinearLayout ll= (LinearLayout) findViewById(R.id.path_container);
        ll.addView(new PathEffectView(this));
    }
}
