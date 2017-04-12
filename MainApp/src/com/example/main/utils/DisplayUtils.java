package com.example.main.utils;

import android.content.Context;

public class DisplayUtils {

	public static int dip2xp(Context context, float dipValue)
	{
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue*density + 0.5F);
	}
	
	public static int px2dip(Context context, float pxValue)
	{
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / (density+0.5F));
	}
	
	public static int px2sp(Context context, float pxValue)
	{
		float density = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / (density+0.5F));
	}
	
	public static int sp2xp(Context context, float dipValue)
	{
		float density = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (dipValue*density + 0.5F);
	}
	
}
