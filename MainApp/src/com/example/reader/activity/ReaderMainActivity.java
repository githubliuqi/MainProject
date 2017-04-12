package com.example.reader.activity;

import com.example.main.R;
import com.example.reader.view.fragment.MainFragment;
import com.example.reader.view.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;
public class ReaderMainActivity extends SlidingFragmentActivity {

	private static final String MAIN_FRAGMENT_TAG = "main";
	private static final String MENU_FRAGMENT_TAG = "menu";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main); //主界面
		
		setBehindContentView(R.layout.menu_fragment); // 侧面菜单
		
		WindowManager wm = this.getWindowManager();
		 
	     int width = wm.getDefaultDisplay().getWidth();
	     int height = wm.getDefaultDisplay().getHeight();
		
		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setBehindOffset((int) (width*0.7));
		
		initFragment();
	}

	private void initFragment()
	{
		MainFragment mainFragment = new MainFragment();
		MenuFragment menuFragment = new MenuFragment();
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction  transaction= manager.beginTransaction();
		transaction.replace(R.id.fm_main, mainFragment, MAIN_FRAGMENT_TAG);
		transaction.replace(R.id.fm_menu, menuFragment, MENU_FRAGMENT_TAG);
		transaction.commit();
		
	}
	
}
