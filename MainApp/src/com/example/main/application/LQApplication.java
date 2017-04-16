package com.example.main.application;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Application;

public class LQApplication extends Application {
	
	private static boolean SUPPORT_SKIN_CHANGE = true;
	private static SkinStyle SKIN_STYLE = SkinStyle.SKIN_STYLE_LIGHT;
	
	
	private ArrayList<AppSkinChangeListener> mSkinChangeListeners ;
	
	
	public LQApplication() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSupportSkinChange(boolean support)
	{
		SUPPORT_SKIN_CHANGE = false;
		if (null != mSkinChangeListeners){
			for (Iterator iterator = mSkinChangeListeners.iterator(); iterator.hasNext();) {
				AppSkinChangeListener appSkinChangeListener = (AppSkinChangeListener) iterator.next();
				appSkinChangeListener.onSupportSkinChanged(support);
			}
		}
	}
	
	public void addAppSkinChangeListener(AppSkinChangeListener listener)
	{
		if (null == mSkinChangeListeners)
		{
			mSkinChangeListeners = new ArrayList<AppSkinChangeListener>();
		}
		mSkinChangeListeners.add(listener);
	}
	
	public void removeAppSkinChangeListener(AppSkinChangeListener listener)
	{
		if (null != mSkinChangeListeners)
		{
			if (mSkinChangeListeners.contains(listener))
			{
				mSkinChangeListeners.remove(listener);
			}
		}
	}
	
	public void removeAllAppSkinChangeListener()
	{
		if (null != mSkinChangeListeners)
		{
			mSkinChangeListeners.clear();
			mSkinChangeListeners = null;
		}
	}
	
	public void setSkip(SkinStyle style)
	{
		SKIN_STYLE = style;
		if (!SUPPORT_SKIN_CHANGE)
		{
			return ;
		}
		if (null != mSkinChangeListeners){
			for (Iterator iterator = mSkinChangeListeners.iterator(); iterator.hasNext();) {
				AppSkinChangeListener appSkinChangeListener = (AppSkinChangeListener) iterator.next();
				appSkinChangeListener.onSkinChanged(style);
			}
		}
	}
	
	public SkinStyle getSkinStyle()
	{
		return SKIN_STYLE;
	}
	
	public boolean getSupportSkinChange()
	{
		return SUPPORT_SKIN_CHANGE;
	}
	
	
}
