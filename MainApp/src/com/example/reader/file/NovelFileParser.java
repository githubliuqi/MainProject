package com.example.reader.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NovelFileParser {

	
	public NovelFileParser()
	{
		
	}
	
	public NovelData parse(File file)
	{
		if (null == file || !file.exists() || !file.isFile())
		{
			return null;
		}
		NovelData novelData = new NovelData();
		ArrayList<Chaptar> chaptars = new ArrayList<NovelFileParser.Chaptar>();
		
		novelData.mNovelName = file.getName();
		
		InputStreamReader isReader = null;
		BufferedReader reader = null;
		try {
			// FileInputStream fis = new FileInputStream(file);
			isReader = new InputStreamReader(new FileInputStream(file), "GBK");
			reader = new BufferedReader(isReader);
			String line = null;
			int lineCount = 0;
			StringBuilder sb = new StringBuilder();
			while (null != (line = reader.readLine())) {
				boolean ok = ReaderFileHelper.isChaptar(line);
				if (ok) {
					if (chaptars.size() == 0 && sb.toString().length() >0)
					{// 前言 : 第一章前面有内容就是前言
						Chaptar chaptar = new Chaptar();
						chaptar.mTitle = "前言";
						//chaptar.mLineNumber = lineCount;
						chaptar.mText = sb.toString();
						chaptars.add(chaptar);
						
						Chaptar chaptar2 = new Chaptar();
						chaptar2.mTitle = line;
						chaptars.add(chaptar2);
					}else
					{
						Chaptar lastChaptar = chaptars.get(chaptars.size()-1);
						lastChaptar.mText = sb.toString();
						Chaptar chaptar = new Chaptar();
						chaptar.mTitle = line;
						chaptar.mLineNumber = lineCount;
						chaptars.add(chaptar);
					}
					sb = new StringBuilder();
				}else
				{
					sb.append(line);
				}
				lineCount++;
			}
			reader.close();
			isReader.close();
			Chaptar lastChaptar = chaptars.get(chaptars.size()-1);
			lastChaptar.mText = sb.toString();
			novelData.mChaptars = chaptars;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
				if (null != isReader) {
					isReader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        return novelData;
	}
	
	
	
	
	
	public String getNovelName()
	{
		return "小说名称";
	}
	
	/**
	 * 章
	 * @author liuqi
	 *
	 */
	class Chaptar
	{
		String mTitle; // 章标题
		int mFrom;   // 起始位置，字节
		int mLength; // 长度，字节
		int mLineNumber;
		String mText;
		
		private ArrayList<PageText> mPageTexts;
		
		public ArrayList<PageText> getPageText()
		{
			if (null != mPageTexts)
			{
				return mPageTexts;
			}
			ArrayList<PageText> pageTexts= new ArrayList<PageText>();
			int length = 255;
			int i = length;
			for( i = length; i < mText.length(); i+=length)
			{
				String text = mText.substring(i - length, i);
				PageText pageText = new PageText();
				pageText.mText = text;
				pageTexts.add(pageText);
			}
			if (i == length)
			{
				PageText pageText = new PageText();
				pageText.mText = mText;
				pageTexts.add(pageText);
			}
			if (i >= mText.length())
			{
				PageText pageText = new PageText();
				pageText.mText = mText.substring(i-length);
				pageTexts.add(pageText);
			}
			mPageTexts = pageTexts;
			return pageTexts;
		}
		
		
		class PageText
		{
			String mText;
			int mTextSize;
			int mLineWidth;
			float mLineSpace;
			int mLineCount;
			
			public PageText getStandard()
			{
				PageText pageText = new PageText();
				
				return pageText;
			}
		}
		
	}
	
	
}
