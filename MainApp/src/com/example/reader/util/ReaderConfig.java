package com.example.reader.util;

import android.graphics.Color;

public class ReaderConfig {

	// 翻页配置
	public static final int PAGE_TYPE_LEFT_AND_RIGHT = 0; // 左右翻页
	public static final int PAGE_TYPE_TOP_AND_BOTTOM = 1; // 上下翻页
	public static int PAGE_TYPE = PAGE_TYPE_LEFT_AND_RIGHT;
	
	// 页配置
	public static int PAGE_CHARPATR_TEXT_COLOR = Color.BLACK;
	public static int PAGE_CHARPATR_TEXT_COLOR_SMALL  = Color.GRAY;
	public static int PAGE_TEXT_COLOR = Color.BLACK;
	public static int PAGE_CHAPTAR_TEXT_SIZE = 26;
	public static int PAGE_CHAPTAR_TEXT_SIZE_SMALL = 10;
	public static int PAGE_TEXT_SIZE = 20;
	public static int PAGE_LINE_SPACE = 5; // dp
	public static int PAGE_LINE_COUNT = 15; //
	
	
	// 翻页动画时间
	public static final int PAGE_DURATION = 300;
	
	// ReaderActivity 
	public static final int READER_HEAD_TITLE_HEIGHT = 100 ;// dp
}
