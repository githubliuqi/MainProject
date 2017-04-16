package com.example.reader.file;

import com.example.main.file.BaseFileHelper;

import android.text.TextUtils;

public class ReaderFileHelper extends BaseFileHelper{

	/**
	 * 判断 此行数据 是否是 章标题
	 * @param string
	 * @return
	 */
	public static boolean isChaptar(String string)
	{
		boolean isChaptar = false;
		if (TextUtils.isEmpty(string))
		{
			return false;
		}
		String text = string.trim();
		if (text.contains("第") && text.contains("章"))
		{
			return true;
		}
		return isChaptar;
	}
	
	
	
}
