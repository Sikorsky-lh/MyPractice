package com.example.lihang.mypractice.cview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.example.lihang.mypractice.ShapeHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihang on 2017/10/11.
 */

public class MyAnimationView extends View implements ValueAnimator.AnimatorUpdateListener {

	static final float BALL_SIZE = 50F;

	static final float FULL_TIME = 1000;

	public List<ShapeHolder> balls = new ArrayList<>();

	public MyAnimationView(Context context) {
		super(context);
		setBackgroundColor(Color.WHITE);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() != MotionEvent.ACTION_DOWN && event.getAction() != MotionEvent.ACTION_MOVE) {
			return false;
		}

		ShapeHolder newBall = addBall(event.getX(), event.getY());
		float startY = newBall.getY();
		float endY = getHeight() - BALL_SIZE;

		float startX = event.getX();
		float endX = getWidth() - BALL_SIZE;

		float h = getHeight();
		float eventY = event.getY();

		float w = getWidth();
		float eventX = event.getY();

		int duration = (int) (FULL_TIME * (1 - eventY / h));


		ValueAnimator fallAnim = ObjectAnimator.ofFloat(newBall, "y", startY, endY);
//        ValueAnimator fallAnim=ObjectAnimator.ofFloat(newBall,"x",startX,endX);
		fallAnim.setDuration(duration);
		fallAnim.setInterpolator(new AccelerateInterpolator());
		fallAnim.addUpdateListener(this);
        fallAnim.setRepeatCount(10);
        fallAnim.setRepeatMode(ValueAnimator.REVERSE);


		ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha", 1f, 0f);
		fadeAnim.setDuration(20);
		fadeAnim.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				balls.remove(((ObjectAnimator) animation).getTarget());
			}
		});

		AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(fallAnim).before(fadeAnim);

		animatorSet.start();
		return true;
	}

	@Override
	public void onAnimationUpdate(ValueAnimator valueAnimator) {
		this.invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (ShapeHolder shapeHolder : balls) {
			canvas.save();
			canvas.translate(shapeHolder.getX(), shapeHolder.getY());
			shapeHolder.getShape().draw(canvas);
			canvas.restore();
		}
		invalidate();
	}

	private ShapeHolder addBall(float x, float y) {
		OvalShape circle = new OvalShape();
		circle.resize(BALL_SIZE, BALL_SIZE);
		ShapeDrawable drawable = new ShapeDrawable(circle);

		ShapeHolder shapeHolder = new ShapeHolder(drawable);
		shapeHolder.setX(x - BALL_SIZE / 2);
		shapeHolder.setY(y - BALL_SIZE / 2);

		int red = (int) (Math.random() * 225);
		int green = (int) (Math.random() * 225);
		int blue = (int) (Math.random() * 225);

		int color = 0xff000000 | red << 16 | green << 8 | blue;

		Paint paint = drawable.getPaint();

		int darkColor = 0xff000000 | red / 4 << 16 | green / 4 << 8 | blue / 4;

		RadialGradient gradient = new RadialGradient(37.5f, 12.5f, BALL_SIZE, color, darkColor, Shader.TileMode.CLAMP);
		paint.setShader(gradient);

//        shapeHolder.setPaint(paint);
		balls.add(shapeHolder);

		return shapeHolder;
	}

	private ValueAnimator getSquashAnim(ShapeHolder newBall, String propertyName, int duration, float... values) {
		ValueAnimator squashAnim = ObjectAnimator.ofFloat(newBall, propertyName, values[0], values[1]);
		squashAnim.setDuration(duration / 4);
		squashAnim.setRepeatCount(1);
		squashAnim.setRepeatMode(ValueAnimator.REVERSE);
		squashAnim.setInterpolator(new DecelerateInterpolator());
		return squashAnim;
	}
}
