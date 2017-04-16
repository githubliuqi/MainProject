package com.example.main.view.stackview;

import com.example.main.view.MainToast;
import com.example.reader.util.ReaderConfig;

import android.R.color;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class FlipPager extends FrameLayout implements OnTouchListener, AnimationListener{

	private BaseAdapter mAdapter;
	private LinearLayout mTopView;
	private LinearLayout mBottomView;
	private Context mContext;
	private GestureDetector mGestureDetector;
	private int mPageType = ReaderConfig.PAGE_TYPE_LEFT_AND_RIGHT;
	private int mPageDuration = ReaderConfig.PAGE_DURATION;
	private int mCurrentPageIndex = 0; // 当前页的索引
	private int mPagerWidth = 0;
	private int mPagerHeight = 0;
	
	private OnTapListener mOnTapListener;
	public FlipPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		init();
	}
	
	@SuppressWarnings("deprecation")
	private void init()
	{  // 设置全屏
	   setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	   setBackgroundColor(color.white);
	   // 设置滑动手势
	   mGestureDetector = new GestureDetector(mContext,new LQSimpleOnGestureListener(this));
	   setLongClickable(true); // 滑动手势判断需要设置
	   setOnTouchListener(this);// 手势事件是在触摸事件中调用的
	   //setPageType(ReaderConfig.PAGE_TYPE_TOP_AND_BOTTOM);
//	   ViewParent vp = getParent();
//	   if (null != vp)vp.requestDisallowInterceptTouchEvent(true);
	}
	
	public void setAdpater(BaseAdapter adapter)
	{
		mAdapter = adapter;
		int count = adapter.getCount();
		if (null == adapter || 0 >= count)
		{
			removeAdapter();
			return;
		}
		if (1 == count)
		{
			if (null == mTopView)
			{
				mTopView = new LinearLayout(mContext);
				//mTopView.setAnimation(mAnimation);
				addView(mTopView);
			}
			if (null != mBottomView)
			{
				removeView(mBottomView);
			}
		}else
		{
			if (null == mBottomView)
			{
				mBottomView = new LinearLayout(mContext);
				addView(mBottomView);
			}
			if (null == mTopView)
			{
				mTopView = new LinearLayout(mContext);
				addView(mTopView);
			}
		}
		//mTopView.addView(mAdapter.getView(0, null, null));
		pageTo(0);
	}
	
	private void removeAdapter()
	{
		mAdapter = null;
		removeAllViews();
		mTopView = null;
		mBottomView = null;
	}
	
	public void setPageType(int type)
	{
		if (type >= 0 && type <= 1)
		{
			mPageType = type;
		}
		
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		mPagerWidth = right - left;
		mPagerHeight = bottom - top;
	}
	
	/**
	 * 翻页处理
	 * @param isNext 是否是下一页，true表示下一页，false表示上一页
	 */
	private void page(boolean isNext)
	{
		
		if (null == mAdapter || 0 >= mAdapter.getCount())
		{
			return;
		}
		if (!isNext && mCurrentPageIndex == 0)
		{
			MainToast.showToast(mContext, "没有了哦，亲！");
			return;
		}
		int pageSize = mAdapter.getCount();
		if (isNext && mCurrentPageIndex == pageSize - 1) {
			MainToast.showToast(mContext, "没有了哦，亲！");
			return;
		}
		mCurrentPageIndex = isNext ? mCurrentPageIndex+1 : mCurrentPageIndex - 1 ;
		TranslateAnimation animation = new TranslateAnimation(0,0, 0, 0);;
		switch (mPageType) {
		case ReaderConfig.PAGE_TYPE_LEFT_AND_RIGHT:
		{
			int animationToX = isNext ? -mPagerWidth : mPagerWidth;
			animation = new TranslateAnimation(0,animationToX, 0, 0);
		}
			break;
		case ReaderConfig.PAGE_TYPE_TOP_AND_BOTTOM:
		{
			int animationToY = isNext ? -mPagerHeight: mPagerHeight;
			animation = new TranslateAnimation(0,0, 0, animationToY);
		}
			break;
		default:
			break;
		}
		
		View view = mBottomView.getChildCount() > 0 ? mBottomView.getChildAt(0) : null; 
		mBottomView.removeAllViews();
		mBottomView.addView(mAdapter.getView(mCurrentPageIndex, view, null));
		animation.setDuration(mPageDuration);
		animation.setAnimationListener(this);
		mTopView.setAnimation(animation);
		animation.start();
	}
	
	
	
	/**
	 * 显示指定页
	 * @param index
	 */
	public void pageTo(int index)
	{
		if (null == mAdapter || 0 >= mAdapter.getCount())
		{
			return;
		}
		int pageSize = mAdapter.getCount();
		if (index < 0 || index >= pageSize) {
			//MainToast.showToast(mContext, "没有了哦，亲！");
			return;
		}
		mCurrentPageIndex = index;
		View view = mTopView.getChildCount() > 0 ? mTopView.getChildAt(0) : null; 
		mTopView.removeAllViews();
		mTopView.addView(mAdapter.getView(mCurrentPageIndex, view, null));
	}
	

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		if (null != mGestureDetector)
		{
			return mGestureDetector.onTouchEvent(event);
		}
		return true; //返回true，否则手势事件执行不到
	}
	
	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		bringChildToFront(mBottomView);
		LinearLayout temp = mTopView;
		mTopView = mBottomView; 
		mBottomView = temp;
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}
	
	public void setOnTapListener(OnTapListener listener)
	{
		mOnTapListener = listener;
	}
	
	protected void onSingleTapUp()
	{
		
	}
	
	class LQSimpleOnGestureListener extends SimpleOnGestureListener
	{
		private FlipPager mView;
		public LQSimpleOnGestureListener(FlipPager view) {
			// TODO Auto-generated constructor stub
			mView = view;
		}
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			// TODO Auto-generated method stub
			//MainToast.showToast(mContext, "滑动 velocityX = "+velocityX+",velocityY = "+velocityY);
			switch (mPageType) {
			case ReaderConfig.PAGE_TYPE_LEFT_AND_RIGHT:
				page(velocityX < 0);
				break;
			case ReaderConfig.PAGE_TYPE_TOP_AND_BOTTOM:
				page(velocityY < 0);
				break;
			default:
				break;
			}
			return true;
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
			mView.onSingleTapUp();
			//return super.onSingleTapUp(e);
			if (null != mOnTapListener)
			{
				mOnTapListener.onSingleTapUp();
			}
			return false;
		}
		
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// TODO Auto-generated method stub
			//MainToast.showToast(mContext, "FlipPager onSingleTapConfirmed");
			return super.onSingleTapConfirmed(e);
		}
	}
	
	interface OnTapListener
	{
		public void onSingleTapUp();
	}
	
}
