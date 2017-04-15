package com.example.main.file;

import java.io.File;

import android.content.Context;

public class BaseFileHelper {
	

	public static File getFileDir(Context context)
	{
		if (null != context)
		{
			return null;
		}
		return context.getFilesDir();
	}
	
}
