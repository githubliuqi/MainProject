package com.example.main.dialog;



import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat.LayoutParams;
import android.view.Gravity;
/**
 * 全屏 半透明 遮罩效果的dialog
 * @author liuqi
 *
 */
import android.view.View;
import android.widget.LinearLayout;
public abstract class TranslucentDialog extends BaseDialog {

	public TranslucentDialog(Context context) {
		super(context,android.R.style.Theme_Translucent_NoTitleBar);
		init();
	}
	
	private void init()
	{
       LinearLayout linearLayout = new LinearLayout(mContext);
       LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
       linearLayout.setLayoutParams(params);
       linearLayout.setBackgroundColor(0x7F777777);
       linearLayout.setGravity(Gravity.CENTER);
	   View view = getContentView();
	   if (null != view)
	   {
		   linearLayout.addView(view);
	   }
	   setContentView(linearLayout);
	}
	
	
	public abstract View getContentView();
	
}
