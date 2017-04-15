package com.example.main.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

public  class BaseDialog extends Dialog {

	protected Context mContext;
	public BaseDialog(Context context) {
		super(context);
		mContext = context;
	}
	
	public BaseDialog(Context context, int style) {
		super(context,style);
		mContext = context;
	}
	
	
}
