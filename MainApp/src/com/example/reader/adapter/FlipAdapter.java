package com.example.reader.adapter;

import java.util.List;

import com.example.main.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FlipAdapter extends BaseAdapter {

	private Context mContext ;
	private List<String> mTextList;
	
	public FlipAdapter(Context mContext,List<String> textList) {
		super();
		this.mContext = mContext;
		mTextList = textList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTextList.size();
	}


	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mTextList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertiew, ViewGroup parent) {
		
		View mView = View.inflate(mContext, R.layout.book_page, null);
		TextView mTextViewTitle = (TextView) mView.findViewById(R.id.page_title);
		TextView mTextViewContent = (TextView) mView.findViewById(R.id.page_content);
		TextView mTextViewBottom = (TextView) mView.findViewById(R.id.page_bottom);
		mTextViewContent.setText(mTextList.get(position));
		return mView;
	}

}
