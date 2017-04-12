package com.example.reader.view.fragment;

import com.example.reader.view.fragment.MainFragment.BookGridAdapter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuFragment extends BaseFragment {

	String[] menus = {"宋史", "元史" ,"明史" ,"清史"};
	ListView mListView;
	@Override
	public View getView() {
		mListView = new ListView(mContext);
		mListView.setBackgroundColor(Color.WHITE);
		mListView.setAdapter(new MenuListAdapter());
		return mListView;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return getView();
	}
	
	class MenuListAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return menus.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return menus[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = new TextView(mContext);
			view.setHeight(100);
			view.setBackgroundColor(Color.GREEN);
			//view.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
			view.setGravity(Gravity.CENTER);
			view.setText(menus[position]);
			return view;
		}
		
	}
		
}
