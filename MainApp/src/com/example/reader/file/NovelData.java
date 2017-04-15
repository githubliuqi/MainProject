package com.example.reader.file;

import java.util.ArrayList;

import com.example.reader.file.NovelFileParser.Chaptar;
/**
 * 只是小说章节内容解析，不涉及字体大小等
 * @author liuqi
 *
 */
public class NovelData {

	public String mNovelName; // 小说名称
	public String mPreText  ; // 前言
	public ArrayList<Chaptar> mChaptars; // 章节信息
	
	public NovelData() {
		// TODO Auto-generated constructor stub
	}
	
	
	public NovelData setNovelName(String novelName)
	{
		mNovelName = novelName;
		return this;
	}
	
	public NovelData setChaptars(ArrayList<Chaptar> chaptars)
	{
		mChaptars = chaptars;
		return this;
	}
	
	
	
}
