package com.example.reader.activity;

import java.io.File;

import com.example.main.R;
import com.example.main.activity.BaseActivity;
import com.example.main.application.SkinStyle;
import com.example.main.utils.DisplayUtils;
import com.example.main.view.MainToast;
import com.example.main.view.stackview.FlipPager;
import com.example.main.view.stackview.SuspendedView;
import com.example.reader.file.NovelData;
import com.example.reader.file.NovelFileParser;
import com.example.reader.file.ReaderDataProvider;
import com.example.reader.util.ReaderConfig;
import com.example.reader.view.ReaderSuspendedViewAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class ReadActivity2 extends BaseActivity implements OnClickListener{

	
	private PopupWindow mTopWindow;
	private PopupWindow mBottomWindow;
	private FlipPager mFlipPager;
	private ReaderSuspendedViewAdapter mAdaptar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = this;
		View view = getView();
		setContentView(view);
		//view.setOnClickListener(this);
		//view.setLongClickable(true);
		
		init();
	}
	
	private void init()
	{
		final NovelFileParser parser = new NovelFileParser();
		File rootSD = Environment.getExternalStorageDirectory() ;// 获取手机外部SD卡（手机自带，不是用户插入的SD卡）
		final File file = new File(rootSD, "Document/明朝那些事儿(书本网).txt");
		//showProgress();
		
		AsyncTask<File, Integer , ReaderDataProvider> task = new AsyncTask<File, Integer, ReaderDataProvider>()
		{
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				showProgress();
			}
			@Override
			protected ReaderDataProvider doInBackground(File... params) {
				NovelData novelData = parser.parse(file);
				ReaderDataProvider dataProvider = new ReaderDataProvider();
				dataProvider.bind(novelData);
				dataProvider.update(ReaderConfig.PAGE_TEXT_SIZE, 400, ReaderConfig.PAGE_LINE_COUNT, ReaderConfig.PAGE_LINE_SPACE);
				return dataProvider;
			}
			
			@Override
			protected void onPostExecute(ReaderDataProvider dataProvider) {
				// TODO Auto-generated method stub
				super.onPostExecute(dataProvider);
				mAdaptar.setDataProvider(dataProvider);
				//mAdaptar.notifyDataSetChanged();
				mFlipPager.setAdpater(mAdaptar);
				closeProgress();
			}
			
		};
		task.execute(file);
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				NovelData novelData = parser.parse(file);
//				ReaderDataProvider dataProvider = new ReaderDataProvider();
//				dataProvider.bind(novelData);
//				dataProvider.update(ReaderConfig.PAGE_TEXT_SIZE, 400, ReaderConfig.PAGE_LINE_COUNT, ReaderConfig.PAGE_LINE_SPACE);
//				mAdaptar.setDataProvider(dataProvider);
//				//mAdaptar.notifyDataSetChanged();
//				mFlipPager.setAdpater(mAdaptar);
//				closeProgress();
//				
//			}
//		}).start();
	}

	private View getView2()
	{
		SuspendedView view = new ReaderSuspendedView(mContext);
		return view;
	}
	
	private View getView()
	{
		LinearLayout ll = new LinearLayout(this);
		
		mFlipPager = new FlipPager(this)
		{
			@Override
			protected void onSingleTapUp() {
				// TODO Auto-generated method stub
				super.onSingleTapUp();
				MainToast.showToast(mContext, "FlipPager onSingleTap");
				//ReadActivity2.this.showDialog(R.layout.activity_main);
				if (null == mTopWindow)
				{
					mTopWindow = showTopPopupWindow(getTopView());
				}else
				{
					mTopWindow.dismiss();
					mTopWindow = null;
				}
				
				if (null == mBottomWindow)
				{
					mBottomWindow = showBottomPopupWindow(getBottomView());
				}else
				{
					mBottomWindow.dismiss();
					mBottomWindow = null;
				}
			}
		};
		
		mAdaptar = new ReaderSuspendedViewAdapter(this);
		
		mFlipPager.setAdpater(mAdaptar);
		
		ll.addView(mFlipPager);
		
		return ll;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		MainToast.showToast(this, "ReadActivity2 onTouchEvent");
	    super.onTouchEvent(event);
	    return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		MainToast.showToast(this, "ReadActivity2 onClick");
	}
	
	class ReaderSuspendedView extends SuspendedView
	{

		private ReaderSuspendedView mSelf;
		public ReaderSuspendedView(Context context) {
			super(context);
			mSelf = this;
		}
		@Override
		public View getContectView() {

			return super.getContectView();
		}
		
		@Override
		public View getTopView() {
			// TODO Auto-generated method stub
			return getTopView();
		}
		
		@Override
		public View getBottomView() {
			// TODO Auto-generated method stub
			return getBottomView();
		}
	}
	
	private View getTopView() {
		// TODO Auto-generated method stub
		//return super.getTopView();
		LinearLayout linearLayout = new LinearLayout(mContext);
		LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		linearLayout.setLayoutParams(llParams);
		int padding = DisplayUtils.dip2xp(mContext, 10);
		//linearLayout.setPadding(padding, padding, 0, padding);
		linearLayout.setGravity(Gravity.CENTER_VERTICAL);
		linearLayout.setBackgroundResource(R.drawable.reader_titlebar_bkg_normal);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//		ImageButton imgBtn = new ImageButton(mContext);
//		imgBtn.setBackgroundResource(R.drawable.common_titlebar_btn_back);
//		LinearLayout.LayoutParams llParams_imgbtn = new LinearLayout.LayoutParams(DisplayUtils.dip2xp(mContext, 18), DisplayUtils.dip2xp(mContext, 25));
		//linearLayout.addView(imgBtn,llParams_imgbtn);
		
		Button button = new Button(mContext);
		button.setText("书架");
		button.setTextSize(14);
		button.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
		button.setTextColor(Color.BLACK);
		//button.setPadding(0, -10, 0, -10);
		button.setBackground(null);
		//button.setBackgroundColor(Color.GREEN);
		Drawable drawable = getResources().getDrawable(R.drawable.common_titlebar_btn_back);
		drawable.setBounds(0,0,DisplayUtils.dip2xp(mContext, 18),DisplayUtils.dip2xp(mContext, 25));
		button.setCompoundDrawables(drawable, null, null, null);
		LinearLayout.LayoutParams llParams_button = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ReadActivity2.this.finish();
			}
		});
		linearLayout.addView(button,llParams_button);
		
		return linearLayout;
	}
	
	private View getBottomView()
	{
		LayoutInflater inflater = getLayoutInflater();
		LinearLayout view  = (LinearLayout) inflater.inflate(R.layout.reader_bottom, null);
		view.setBackgroundResource(R.drawable.reader_titlebar_bkg_normal);	
		int[] btnIds = 
			{   R.id.menu_btn_chapterlist,
				R.id.menu_btn_download,
				R.id.menu_btn_light_mode,
				R.id.menu_btn_settings
			};
		int[] btnDrawableToptIds = 
			{  R.drawable.reader_content_bottom_bar_chapterlist,
			   R.drawable.reader_content_bottom_bar_download,
			   R.drawable.reader_nav_content_bottom_bar_night_mode,
			   R.drawable.reader_contentpage_bottom_first_setting
		    };
		for (int i = 0; i<btnIds.length;i++)
		{
			Button btn = (Button) view.findViewById(btnIds[i]);
		    Drawable drawable = getResources().getDrawable(btnDrawableToptIds[i]);
		    int size = DisplayUtils.dip2xp(mContext, 25);
		    drawable.setBounds(0, 0, size, size);
		    btn.setCompoundDrawables(null, drawable, null, null);
		}
		return view;
	}
	
	@Override
	public void onSkinChanged(SkinStyle style) {
		// TODO Auto-generated method stub
		super.onSkinChanged(style);
		if (SkinStyle.SKIN_STYLE_BLACK == style) // 夜间模式
		{
			
		}
	}
	

}
