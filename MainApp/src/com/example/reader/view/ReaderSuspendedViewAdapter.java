package com.example.reader.view;

import com.example.main.R;

import android.content.Context;
import android.graphics.Color;
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
	public ReaderSuspendedViewAdapter(Context context) {
		mContext = context;
		mData = new DataWraper[10];
		for(int i = 0; i < mData.length; i++ )
		{
			mData[i] = new DataWraper();
			mData[i].mText = "data"+i;
		}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData[position];
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
		view.setData((DataWraper) getItem(position));
		return view;
	}
	
	class FlipPagerView extends LinearLayout
	{

		public TextView mTextView;
		public FlipPagerView(Context context) {
			super(context);
			init();
		}
		
		private void init()
		{
			setGravity(Gravity.CENTER);
			setOrientation(LinearLayout.VERTICAL);
			setBackgroundResource(R.drawable.reader_novel_nav_conent_skin_bg_parchment);
			setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			mTextView = new TextView(mContext);
			mTextView.setTextColor(Color.BLACK);
		    addView(mTextView);
		}
		
		public void setData(DataWraper data)
		{
			mTextView.setText(data.mText);
		}
	}
	
	class DataWraper
	{
		String mText;
	}
}
