package com.example.lihang.mypractice.activity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lihang.mypractice.R;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
	private EditText et_main;
	private Button btn;

	public static final int MSG = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Timer timer = new Timer();
		final ImageView imageView = (ImageView) findViewById(R.id.et);

		btn = (Button) findViewById(R.id.btn);
//        et_main= (EditText) findViewById(R.id.et);
		final Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.my_anmi);
//        anim.setFillAfter(true);

		final String str = getResources().getString(R.string.app_name);

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (btn instanceof Button) {
					Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
				}

				ObjectAnimator colorAnim = (ObjectAnimator) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.color_anim);
				colorAnim.setEvaluator(new ArgbEvaluator());
				colorAnim.setTarget(findViewById(R.id.main_container));
				colorAnim.start();
				imageView.startAnimation(anim);

				deleteFile("data");
			}
		});

		ObjectAnimator animator = ObjectAnimator.ofFloat(btn, "alpha", 1f, 0f);
		animator.start();
		animator.setDuration(1000);
		animator.setRepeatCount(6);
		animator.setRepeatMode(ValueAnimator.REVERSE);
		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				Toast.makeText(MainActivity.this, "按钮消失了", Toast.LENGTH_SHORT).show();
			}
		});

		SharedPreferences pref = getSharedPreferences("file_path", MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		Set<String> listSet = new HashSet<>();
		Collections.addAll(listSet, fileList());
//        for (String s:fileList()){
//			listSet.add(s);
//        }
		editor.putStringSet("list", listSet);
		editor.putString("path", getFilesDir().getPath());
		editor.putBoolean("is_list", true);
		editor.putString("test", "test");
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
			case R.id.next:
				intent.setClass(MainActivity.this, SecondActivity.class);
				startActivity(intent);
				break;
			case R.id.path_effect:
				intent.setClass(MainActivity.this, PathActivity.class);
				startActivity(intent);
				break;
			case R.id.anim_drawable:
				intent.setClass(MainActivity.this, AnimDrawableActivity.class);
				startActivity(intent);
				break;
			case R.id.animator:
				intent.setClass(MainActivity.this, AnimatorActivity.class);
				startActivity(intent);
				break;
			case R.id.surface:
				intent.setClass(MainActivity.this, SurfaceActivity.class);
				startActivity(intent);
				break;
			case R.id.file:
				intent.setClass(MainActivity.this, FileActivity.class);
				startActivity(intent);
				break;
			case R.id.message:
				intent.setClass(MainActivity.this,MessageActivity.class);
				startActivity(intent);
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if(exit()){
			finish();
		}else {
			Toast.makeText(MainActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
		}
	}

	long lastTime=0;
	long curTime=0;
	private boolean exit(){
		lastTime=curTime;
		curTime=System.currentTimeMillis();
		return curTime-lastTime<2000;
	}

}
