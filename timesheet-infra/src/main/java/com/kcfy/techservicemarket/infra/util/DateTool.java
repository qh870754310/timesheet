package com.kcfy.techservicemarket.infra.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTool {

	/**
	   * 转化时间从长整形为指定格式日期
	   *
	   * @param format
	   * @param time
	   * @return
	   */
	  public static String convertLongToString(String format, Long time) {
	    if(time==null){
	        return "";
	    }
	    SimpleDateFormat df = new SimpleDateFormat(format);
	    Timestamp now = new Timestamp(time);
	    return df.format(now);
	  }

	  /**
	   * 转化时间从指定格式日期为长整形
	   *
	   * @param format
	   * @param time
	   * @return
	   */
	  public static Long convertStringToLong(String format, String time) throws ParseException {
	    if(time==null||time.trim().equals("")){
	        return null;
	    }
	    SimpleDateFormat fmt = new SimpleDateFormat(format);
	    Date d = fmt.parse(time);
	    return d.getTime();
	  }

	public static String convertDateToString(String format, Date date) {
		if(date==null){
			return "";
		}
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(date);
	}

	public static Date convertStringToDate(String format, String date) {
		if(date==null||date.trim().equals("")){
			return null;
		}
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getFirstDayOfThisYear() {
		Calendar calendarThisYear = Calendar.getInstance();
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, calendarThisYear.get(Calendar.YEAR));
		Date firstDayOfThisYear = calendar.getTime();
		return firstDayOfThisYear;
	}
	public static List<Date> findDatesBetween(String dBegin, String dEnd) {
		return findDatesBetween(convertStringToDate("yyyy-MM-dd",dBegin),convertStringToDate("yyyy-MM-dd",dEnd));
	}
	public static List<Date> findDatesBetween(Date dBegin, Date dEnd) {
		List lDate = new ArrayList();
		lDate.add(dBegin);
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(calBegin.getTime());
		}
		return lDate;
	}

	public static List<Date> getAllDateOfThisYearToNow() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return findDatesBetween(getFirstDayOfThisYear(), calendar.getTime());
	}

	public static boolean isWorkDay(String date) {

		return isWorkDay(convertStringToDate("yyyy-MM-dd",date));

	}
	public static boolean isWorkDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return false;
		}
		return true;
	}
}
