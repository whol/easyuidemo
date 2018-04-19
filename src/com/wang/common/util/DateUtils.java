package com.wang.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理工具类
 * 
 */
public class DateUtils {
	
	private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 
	 * 功能描述：将日期转成字符串
	 *
	 * @param date
	 * @return
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String format(Date date){
		if(null==date){
			return "";
		}
		return format.format(date);
	}
	/**
	 * 功能描述：将Timestamp转成字符串
	 * @param timestamp
	 * @return
	 */
	public static String getTimestringByStamp(Date timestamp){
		if (null==timestamp) {
			return "";
		}
		return format(timestamp);
		
	}
	/**
	 * 
	 * 功能描述：获取当前的时间，精确到毫秒
	 *
	 * @return
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getNanoTime(){
		return System.nanoTime()+"";
	}
	
	/** 
	 * 
	 * 功能描述：将字符串转换成日期
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Date parse(String date) throws ParseException{
		if(date.isEmpty()){
			return null;
		}
		return format.parse(date);
	}
}
