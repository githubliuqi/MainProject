package com.example.main.dialog;

import com.example.main.R;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

public class LQProgressDialog extends TranslucentDialog {

	public LQProgressDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getContentView() {
		ProgressBar progressBar = new ProgressBar(mContext);
		progressBar.setBackgroundResource(R.drawable.main_progressbar);
		return progressBar;
	}

}
