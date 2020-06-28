package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertor {
	public static java.sql.Date javaToSqlConvert(Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
	
	public static Date sqlToJavaConvert() {
		return new Date();
	}
	
	public static Date stringToJavaDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1= new Date();
		try {
			date1 = formatter.parse(date);
			return date1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}
	
	public static String javaToStringDate() {
		return new String();
	}
}
