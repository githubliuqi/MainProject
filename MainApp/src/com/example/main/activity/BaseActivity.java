package com.example.main.activity;

import com.example.main.utils.MainConfig;
import com.example.reader.util.ReaderConfig;

import com.example.main.R;
import com.example.main.dialog.BaseDialog;
import com.example.main.dialog.LQProgressDialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;

public class BaseActivity extends Activity {

	protected Context mContext;
	protected View mContentView;
	private Dialog mProgressDialog;
	protected Handler mUIHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
		mUIHandler = new Handler();
		if (fullScreen()) {
			// 无title
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			// 全屏
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			// 此两段代码必须设置在setContentView()方法之前
		}
	}
	
	@Override
	public void setContentView(View view) {
		// TODO Auto-generated method stub
		super.setContentView(view);
		mContentView = view;
	}
	
	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		super.setContentView(layoutResID);
	}
	private  View getRootView()  
    {  
        return ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);  
    }  
	protected boolean fullScreen()
	{
		return true;
	}
	
	
	protected PopupWindow showTopPopupWindow(View view)
	{
	   if (null == view)
	   {
		   return null;
	   }
	   final PopupWindow  topWindow = new PopupWindow(view,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
	   //topWindow.setOutsideTouchable(true);
	   //topWindow.setBackgroundDrawable(new BitmapDrawable());
	   //view.setAnimation(getEnterAnimation(view));
	   topWindow.setAnimationStyle(R.style.Rreader_Main_Animation_Top);
	   topWindow.showAtLocation(getRootView(), Gravity.TOP, 0, 0);
	   return topWindow;
	}
	
	protected PopupWindow showBottomPopupWindow(View view)
	{
	   if (null == view)
	   {
		   return null;
	   }
	   final PopupWindow  window = new PopupWindow(view,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
	   window.setAnimationStyle(R.style.Reader_Main_Animation_Bottom);
	   window.showAtLocation(getRootView(), Gravity.BOTTOM, 0, 0);
	   return window;
	}
	
	protected Animation getEnterAnimation(View view)
	{
		if (null == view)
		{
			return null;
		}
		view.measure(0, 0);
		TranslateAnimation animation = new TranslateAnimation(0, 0, -view.getMeasuredHeight(), 0);
		animation.setDuration(MainConfig.MAIN_ANIMATION_DURATION);
		return animation;
	}
	
	protected Animation getOuterAnimation(View view)
	{
		if (null == view)
		{
			return null;
		}
		view.measure(0, 0);
		TranslateAnimation animation = new TranslateAnimation(0, 0, view.getMeasuredHeight(), 0);
		animation.setDuration(MainConfig.MAIN_ANIMATION_DURATION);
		return animation;
	}
	
	
	protected void showProgress() {

	/*	mUIHandler.post(() -> {
			if (null == mProgressDialog) {
				mProgressDialog = new LQProgressDialog(mContext);
			}
			if (!mProgressDialog.isShowing()) {
				mProgressDialog.show();
			}
		});*/

		mUIHandler.post(new Runnable() {

			@Override
			public void run() {
				if (null == mProgressDialog) {
					mProgressDialog = new LQProgressDialog(mContext);
				}
				if (!mProgressDialog.isShowing()) {
					mProgressDialog.show();
				}
			}
		});
	}
	
	protected void closeProgress()
	{
		
		mUIHandler.post(new Runnable() {
			
			@Override
			public void run() {
				if (null != mProgressDialog)
				{
					mProgressDialog.dismiss();
				}
			}
		});
		/*mUIHandler.post(()->{
			if (null != mProgressDialog)
			{
				mProgressDialog.dismiss();
			}
		});*/
	}
	
}

/**
 * PopupWindow  http://blog.csdn.net/zxz_tsgx/article/details/51597616
 * 
 * android 4.0/5.0  PopupWindow 设置背景图了，点击弹窗外面，弹窗才会关闭
 * android 6.0      PopupWindow 无论是否设置了背景图，弹窗都会关闭。
 */
