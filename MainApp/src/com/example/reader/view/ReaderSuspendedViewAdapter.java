package com.example.reader.view;

import com.example.main.R;
import com.example.main.utils.DisplayUtils;
import com.example.reader.file.ReaderDataProvider;
import com.example.reader.file.ReaderPageData;
import com.example.reader.util.ReaderConfig;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ReaderSuspendedViewAdapter extends BaseAdapter {

	//private String[]  mData= {"FlipPager0","FlipPager1","FlipPager2"};
	private Context mContext;
	private DataWraper[] mData;
	private ReaderDataProvider mDataProvider;
	public ReaderSuspendedViewAdapter(Context context) {
		mContext = context;
//		mData = new DataWraper[10];
//		for(int i = 0; i < mData.length; i++ )
//		{
//			mData[i] = new DataWraper();
//			mData[i].mText = "data"+i;
//		}
	}
	
	public void setDataProvider(ReaderDataProvider dataProvider) {
		mDataProvider = dataProvider;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (null == mDataProvider || null == mDataProvider.getData())
		{
			return 0;
		}
		return mDataProvider.getData().size();
	}

	@Override
	public Object getItem(int position) {
		if (null == mDataProvider || null == mDataProvider.getData())
		{
			return null;
		}
		return mDataProvider.getData().get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        
		FlipPagerView view = null;
		if (null != convertView)
		{
			view = (FlipPagerView)convertView;
		}else
		{
			view = new FlipPagerView(mContext);
		}
		//view.setData((DataWraper) getItem(position));
		view.bindData((ReaderPageData) getItem(position));
		return view;
	}
	
	class FlipPagerView extends LinearLayout
	{

		public TextView mTitleView; // 章 标题
		public TextView mTextView;  // 内容
		public TextView mPageNumber; // 页码
		public FlipPagerView(Context context) {
			super(context);
			init();
		}
		
		private void init()
		{
			//setGravity(Gravity.CENTER);
			setOrientation(LinearLayout.VERTICAL);
			setBackgroundResource(R.drawable.reader_novel_nav_conent_skin_bg_parchment);
			setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			int padding = DisplayUtils.dip2xp(mContext, 10);
			setPadding(padding, padding, padding, padding);
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0);
			
			mTitleView = new TextView(mContext);
			mTitleView.setText("章标题");
			mTitleView.setTextColor(ReaderConfig.PAGE_CHARPATR_TEXT_COLOR);
			mTitleView.setTextSize(ReaderConfig.PAGE_CHAPTAR_TEXT_SIZE);
			mTitleView.setGravity(Gravity.CENTER);
			//mTitleView.setBackgroundColor(Color.RED);
			params.weight = 1;
			addView(mTitleView);
			mTextView = new TextView(mContext);
			mTextView.setTextColor(ReaderConfig.PAGE_TEXT_COLOR);
			mTextView.setTextSize(ReaderConfig.PAGE_TEXT_SIZE);
			mTextView.setLineSpacing(DisplayUtils.dip2xp(mContext, ReaderConfig.PAGE_LINE_SPACE), 1);
			params.weight = 8;
			addView(mTextView, params);
		    
		    mPageNumber = new TextView(mContext);
		    mPageNumber.setText("页码");
		    mPageNumber.setTextSize(12);
		    mPageNumber.setTextColor(Color.GRAY);
		    mPageNumber.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
		    params.weight = 1;
		    addView(mPageNumber);
		}
		
//		public void setData(DataWraper data)
//		{
//			mTextView.setText(data.mText);
//		}
		
		public void bindData(ReaderPageData pageData)
		{
			mTitleView.setText(pageData.mChaptar);
			mTextView.setText(pageData.mText);
			mPageNumber.setText(pageData.mIndexAtChaptar+1+"/"+pageData.mPageCountOfChapatar);
		}
	}
	
	class DataWraper
	{
		String mText;
	}


	
}
