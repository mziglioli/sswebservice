package com.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getDateByNextDay(Integer dayOfWeekNum) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_WEEK, dayOfWeekNum);
		return cal.getTime();
	}

	public static boolean isValidTime(String strHour, String strMins) {
		return true;
	}
}