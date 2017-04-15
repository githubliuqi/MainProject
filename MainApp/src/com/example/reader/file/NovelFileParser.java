package com.example.reader.file;

import java.io.File;

public class NovelFileParser {

	private File mFile;
	public NovelFileParser(File file)
	{
		mFile = file;
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
	}
	
	
}
