package com.example.main.view.stackview;

import com.example.main.view.MainToast;

import android.content.Context;
import android.graphics.Color;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SuspendedView extends FrameLayout implements OnTouchListener {

	private Context mContext;
	//private RelativeLayout mRelativeLayout;
	private GestureDetector mGestureDetector;
	private View mTopView;
	private View mBottomView;
	private View mContentView;
	private static final int ANIMATION_DURATON = 250;
	public SuspendedView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		init();
	}
	
	
	private void init()
	{
	   setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	   setBackgroundColor(Color.RED);
	   //mRelativeLayout = new RelativeLayout(mContext);
	   //addView(mRelativeLayout);
	   //mRelativeLayout.setBackgroundColor(Color.BLUE);
	   mContentView = getContectView();
	   mTopView = getTopView();
	   mBottomView = getBottomView();
	   if (null != mContentView)
	   {
		   LayoutParams params_content = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		   addView(mContentView,params_content);
	   }
	   if (null != mTopView) {
		   mTopView.measure(0, 0);
		   int h = mTopView.getMeasuredHeight();
		   LayoutParams params_top = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		   params_top.topMargin = -h;
		   addView(mTopView, params_top);
	   }
	   if (null != mBottomView) {
		   mBottomView.measure(0, 0);
		   int h = mBottomView.getMeasuredHeight();
		   LayoutParams params_bottom = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		   params_bottom.gravity = Gravity.BOTTOM;
		   params_bottom.bottomMargin = -h;
		   addView(mBottomView, params_bottom);
		   //mBottomView.setVisibility(View.GONE);
	   }
	   //addSubViews();
	   setLongClickable(true);
	   setOnTouchListener(this);
	   mGestureDetector = new GestureDetector(mContext, new LQSimpleOnGestureListener(this));
	}

	public void onSingleTap()
	{
		//MainToast.showToast(mContext, "SuspendedFlipPager onSingleTapUp");
		toggleSuspendedView();
	}
	
	private void toggleSuspendedView()
	{
		if (null != mTopView) {
			mTopView.measure(0, 0);
			int h = mTopView.getMeasuredHeight();
			TranslateAnimation animation = null;
			FrameLayout.LayoutParams params_top = (LayoutParams) mTopView.getLayoutParams();
			params_top.topMargin =  params_top.topMargin == 0 ? -h : 0;
			mTopView.setLayoutParams(params_top);
			if (0 == params_top.topMargin)
			{
				animation = new TranslateAnimation(0, 0, -h, 0);
			}else
			{
				animation = new TranslateAnimation(0, 0, h, 0);
			}
			
			//animation.setAnimationListener(new LQAnimationListener(mTopView));
			animation.setDuration(ANIMATION_DURATON);
			mTopView.setAnimation(animation);
			animation.start();
			//mTopView.startAnimation(animation);
		}
		if (null != mBottomView)
		{
			mBottomView.measure(0, 0);
			int h = mBottomView.getMeasuredHeight();
			TranslateAnimation animation = null;
			FrameLayout.LayoutParams params_bottom = (LayoutParams) mBottomView.getLayoutParams();
			params_bottom.bottomMargin =  params_bottom.bottomMargin == 0 ? -h : 0;
			mBottomView.setLayoutParams(params_bottom);
			if (0 == params_bottom.bottomMargin)
			{
				animation = new TranslateAnimation(0, 0, h, 0);
			}else
			{
				animation = new TranslateAnimation(0, 0, -h, 0);
			}
			animation.setDuration(ANIMATION_DURATON);
			mBottomView.setAnimation(animation);
			animation.start();
		}
		
	}

	public View getContectView()
	{
		return null;
	}
	
	public View getTopView()
	{
		
		LinearLayout  linearLayout  = new LinearLayout(mContext);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 100);
		linearLayout.setLayoutParams(params);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setGravity(Gravity.CENTER);
		linearLayout.setBackgroundColor(Color.WHITE);
		TextView view = new TextView(mContext);
		view.setBackgroundColor(Color.BLUE);
		view.setText("top view");
		Button button = new Button(mContext);
		button.setText("button");
		linearLayout.addView(view);
		linearLayout.addView(button);
		return linearLayout;
	}
	
	public View getBottomView()
	{
		TextView view = new TextView(mContext);
		view.setBackgroundColor(Color.BLUE);
		view.setText("Bottom view");
		return view;
	}
	
	class LQSimpleOnGestureListener extends SimpleOnGestureListener
	{
		private SuspendedView mView;
		public LQSimpleOnGestureListener(SuspendedView view) {
           mView = view;
		}
		
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			//MainToast.showToast(mContext, "FlipPager onDown");
			return super.onDown(e);
		}
		
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			//MainToast.showToast(mContext, "FlipPager onSingleTapUp");
			mView.onSingleTap();
			mView.toggleSuspendedView();
			return super.onSingleTapUp(e);
		}
		
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// TODO Auto-generated method stub
			//MainToast.showToast(mContext, "FlipPager onSingleTapConfirmed");
			return super.onSingleTapConfirmed(e);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (null != mGestureDetector)
		{
			mGestureDetector.onTouchEvent(event);
		}
		return false;
	}
	


	class LQAnimationListener implements AnimationListener
	{

		private View mView;
		public LQAnimationListener(View view) {
			mView = view;
		}
		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			int visibility = mView.getVisibility();
			mView.setVisibility(View.GONE == visibility ? View.VISIBLE : View.GONE);
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			int visibility = mView.getVisibility();
			mView.setVisibility(View.GONE == visibility ? View.VISIBLE : View.GONE);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
