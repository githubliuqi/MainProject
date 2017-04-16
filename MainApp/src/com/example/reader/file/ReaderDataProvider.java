package com.example.reader.file;

import java.util.ArrayList;

import com.example.reader.file.NovelFileParser.Chaptar;
import com.example.reader.file.NovelFileParser.Chaptar.PageText;

import android.text.TextUtils;
/**
 *  负责 将 NovelData 格式化 成以页为单位的 数据（包括字体大小等）
 * @author liuqi
 *
 */
public class ReaderDataProvider {
	
	private ArrayList<ReaderPageData> mDatas ;
	
	private NovelData mNovelData;
	
	private static int mTextSize = 15; // 字体大小
	private static int mLineWidth = 300; // 页显示宽度 ，px
	private static int mLineCount = 15; // 行数
	private static float mLineSpace = 5; // 行距
	
	public void bind(NovelData novelData)
	{
		if (mNovelData != novelData)
		{
			mDatas = null;
			mNovelData = novelData;
		}
	}
	
	private void translate()
	{
		if (null == mNovelData || null == mNovelData.mChaptars)
		{
			return;
		}
		mDatas = new ArrayList<ReaderPageData>();
		int pageCount = 0;
		for (int i = 0 ; i < mNovelData.mChaptars.size(); i++)
		{
			ArrayList<ReaderPageData> datas = translateChaptar(mNovelData.mChaptars.get(i), pageCount);
		    if (null != datas)
		    {
		    	mDatas.addAll(datas);
		    }
		}
		ReaderPageData.mPageCountOfNovel = pageCount; // 小说总页数
	}
	
	private ArrayList<ReaderPageData> translateChaptar(Chaptar chaptar, int pageCount)
	{
		if (null == chaptar || TextUtils.isEmpty(chaptar.mText))
		{
			return null;
		}
		ArrayList<ReaderPageData> datas = new ArrayList<ReaderPageData>();
		int length = 255;
		int i = length;
		for( i = length; i < chaptar.mText.length(); i+=length)
		{
			String text = chaptar.mText.substring(i - length, i);
			ReaderPageData pageText = new ReaderPageData();
			pageText.mChaptar = chaptar.mTitle;
			pageText.mText = text;
			datas.add(pageText);
			pageText.mIndexAtChaptar = datas.size()-1;
			pageText.mIndexAtNovel = pageCount + pageText.mIndexAtChaptar;
			pageCount++;
		}
		if (i == length)
		{
			ReaderPageData pageText = new ReaderPageData();
			pageText.mChaptar = chaptar.mTitle;
			pageText.mText = chaptar.mText;
			datas.add(pageText);
			pageText.mIndexAtChaptar = datas.size()-1;
			pageText.mIndexAtNovel = pageCount + pageText.mIndexAtChaptar;
			pageCount++;
		}
		if (i >= chaptar.mText.length())
		{
			ReaderPageData pageText = new ReaderPageData();
			pageText.mChaptar = chaptar.mTitle;
			pageText.mText = chaptar.mText.substring(i-length);
			datas.add(pageText);
			pageText.mIndexAtChaptar = datas.size()-1;
			pageText.mIndexAtNovel = pageCount + pageText.mIndexAtChaptar;
			pageCount++;
		}
		for (ReaderPageData data : datas)
		{
			data.mPageCountOfChapatar = datas.size();
		}
		return datas;
	}
	
	public ArrayList<ReaderPageData> getData()
	{
		if (null != mDatas)
		{
			return mDatas;
		}
	
		translate();
		return mDatas;
	}
	
	public void update(int textSize, int lineWith, int lineCount, int lineSpace)
	{
		mTextSize = textSize;
		mLineWidth = lineWith;
		mLineCount = lineCount;
		mLineSpace = lineSpace;
		translate();
	}
}
