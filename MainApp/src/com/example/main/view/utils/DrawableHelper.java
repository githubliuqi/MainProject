package com.example.main.view.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class DrawableHelper {
	
	public static Drawable getRoundCornerDrawable(float radius,int stokeWidth, int  stokeColor, int backColor)
	{
		GradientDrawable gd = new GradientDrawable();
		gd.setCornerRadius(radius);
		gd.setStroke(stokeWidth, stokeColor);
		gd.setColor(backColor);
		return gd;
	}

}
