package com.example.main.view;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class MainToast {

	public static void showToast(final Context context,final String text)
	{
		if (null == context) {
			return;
		}
		Handler mUIHandler = new Handler(context.getMainLooper());
		if (null == mUIHandler)
		{
			return;
		}
		mUIHandler.post(new Runnable() {
			
			@Override
			public void run() {
				Toast.makeText(context, text, 0).show();
			}
		});
		
	}
}
