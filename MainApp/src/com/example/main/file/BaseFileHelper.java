package com.example.main.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.text.TextUtils;

public class BaseFileHelper {
	

	public static File getFileDir(Context context)
	{
		if (null != context)
		{
			return null;
		}
		return context.getFilesDir();
	}
	
	/**
	 * 获取txt文件 编码格式
	 * @param txtFile
	 * @return
	 */
	public static String getEncode(File txtFile)
	{
		if (null == txtFile || !txtFile.exists() || !txtFile.isFile())
		{
			return null;
		}
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(txtFile);
			byte[] head3bytes = new byte[3];
			fis.read(head3bytes, 0, 3);
			
			if (head3bytes[0] == 0xEF && head3bytes[1] == 0xBB && head3bytes[2] == 0xBF )
			{
				return "UTF-8";
			}else if (head3bytes[0] == 0xFF && head3bytes[1] == 0xFE)
			{
				return "Unicode";
			}else if (head3bytes[0] == 0xFE && head3bytes[1] == 0xFF)
			{ // Unicode big endian
				return "Unicode";
			}else {
				return "GBK";
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (null != fis)
			{
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static String getString(String text, String encode)
	{
		if (TextUtils.isEmpty(text) || TextUtils.isEmpty(encode))
		{
			return null;
		}
		if ("GBK".equals(encode))
		{
			byte[] bt = {(byte) 0xA1,(byte) 0xA1};// GBK下 空格编码
			try {
				String str = new String(bt,"GBK");
				text = text.replace(str, ""); // 去掉 GK编码下前2空格字符
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return text;
	}
}
