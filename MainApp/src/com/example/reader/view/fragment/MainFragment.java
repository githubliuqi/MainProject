package com.example.reader.view.fragment;

import com.example.main.R;
import com.example.main.activity.BaseActivity;
import com.example.main.utils.DisplayUtils;
import com.example.main.view.utils.DrawableHelper;
import com.example.reader.activity.ReadActivity;
import com.example.reader.activity.ReadActivity2;

import android.R.menu;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends BaseFragment {

	String[] menus = {"先秦","秦史","汉史","三国史","魏晋南北朝","隋史","唐史","宋史", "元史" ,"明史" ,"清史","民国"};
	GridView mGridView ;
	@Override
	public View getView() {
		mGridView = new GridView(mContext);
		mGridView.setBackgroundColor(Color.WHITE);
		mGridView.setNumColumns(3);
		mGridView.setAdapter(new BookGridAdapter());
		mGridView.setOnItemClickListener(new BookGridListener());
		return mGridView;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return getView();
	}
	
	class BookGridAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return menus.length+1;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			if (position == menus.length )
			{
				return null;
			}
			return menus[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		    
			View view2 = mActivity.getLayoutInflater().inflate(R.layout.reader_book_grid_item, null);
			
			TextView view = new TextView(mContext);
			//view.setBackground(DrawableHelper.getRoundCornerDrawable(10, 1, Color.TRANSPARENT	, Color.GRAY));
			int size = DisplayUtils.dip2xp(mContext, 100) ;
			view.setWidth(size);
			view.setHeight(size);
			view.setGravity(Gravity.CENTER);
			Drawable drawable = getResources().getDrawable(R.drawable.reader_filesystem_icon_text);
			if (position < getCount() - 1)
		    {
				view.setText(menus[position]);
		    }else {
		    	drawable = getResources().getDrawable(R.drawable.common_btn_add);
			}
			drawable.setBounds(0, 0, size, size);
			view.setBackgroundColor(Color.GREEN);
			//view.setBackground(drawable);
			//view.setScaleY(1.0F*size/drawable.getIntrinsicHeight());
			return view;
		}
		
	}
	
	class BookGridListener implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    if (position != menus.length)
		    {
		    	mActivity.startActivity(new Intent(mContext, ReadActivity2.class));
		    }
		}
		
	}
	 
	
}
