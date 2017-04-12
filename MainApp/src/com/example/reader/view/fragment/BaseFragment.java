package com.example.reader.view.fragment;

import java.math.MathContext;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

public class BaseFragment extends Fragment {
	Context mContext ;
	Activity mActivity;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = getContext();
		mActivity = getActivity();
	}
	@Override
	public View getView() {
		TextView view = new TextView(getContext());
		view.setText("hello");
		view.setBackgroundColor(Color.GREEN);
		return view ;
	}
	
	

}
