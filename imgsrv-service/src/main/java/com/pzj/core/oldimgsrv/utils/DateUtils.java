package com.pzj.core.oldimgsrv.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String LONG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String CNLONG_DATE_PATTERN = "yyyy年M月dd日 H点m分s秒";
	public static final String CNSHORT_DATE_PATTERN = "yyyy年M月dd日";
	public static final String DATE_PATTERN_PLAIN = "yyyyMMdd";
	public static final String YEAR_PATTERN_PLAIN = "yyyy";
	public static final int SEASON_SPRING = 1;
	public static final int SEASON_SUMMER = 2;
	public static final int SEASON_AUTUMN = 3;
	public static final int SEASON_WINTER = 4;
	public static final String LONG_DATE_PATTERN_PLAIN = "yyyyMMddHHmmss";
	private static final SimpleDateFormat dateFormatG = new SimpleDateFormat(
			"yyyy-MM-dd");

	private static final SimpleDateFormat longDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static final SimpleDateFormat cnLongDateFormat = new SimpleDateFormat(
			"yyyy年M月dd日 H点m分s秒");

	public static String format(Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			ret = dateFormatG.format(v);
		} catch (IllegalArgumentException localIllegalArgumentException) {
		}
		return ret;
	}

	public static String longDate(Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			ret = longDateFormat.format(v);
		} catch (IllegalArgumentException localIllegalArgumentException) {
		}
		return ret;
	}

	public static String cnLongDate(Object v) {
		if (v == null)
			return null;
		String ret = "";
		try {
			ret = cnLongDateFormat.format(v);
		} catch (IllegalArgumentException localIllegalArgumentException) {
		}
		return ret;
	}

	public static String formatDate(String format, Object v) {
		if (v == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(v);
	}

	public static Date parseDate(String dateTime, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isEndOfMonth(Date currDate) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(currDate);
		if (calendar.get(5) == calendar.getActualMaximum(5)) {
			return true;
		}
		return false;
	}

	public static Date getYesTaday() {
		Calendar cal = Calendar.getInstance();
		if ((cal.get(2) == 0) && (cal.get(6) == 1)) {
			cal.roll(1, -1);
		}
		cal.roll(6, -1);
		return cal.getTime();
	}

	public static Date getAppointDate(Date date, int daysum) {
		Calendar calstart = Calendar.getInstance();
		calstart.setTime(date);
		calstart.add(7, daysum);
		return calstart.getTime();
	}

	public static String getAppointDateString(Date date, int daysum) {
		return longDate(getAppointDate(date, daysum));
	}

	public static long getQuot(Date date1, Date date2) {
		long quot = 0L;
		quot = date1.getTime() - date2.getTime();
		quot = quot / 1000L / 60L / 60L / 24L;
		return Math.abs(quot);
	}

	public static Date getDateByType(Date date, String format) {
		return parseDate(longDate(date), format);
	}

	public static Date getThisWeekDay(int dayNo, boolean flag) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(flag ? 1 : 2);
		c.add(5, -(c.get(7) - c.getFirstDayOfWeek() + 1) + dayNo);
		return c.getTime();
	}

	public static Integer getAgeByBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();

		if (birthday == null) {
			return null;
		}

		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(1);
		int monthNow = cal.get(2) + 1;
		int dayOfMonthNow = cal.get(5);

		cal.setTime(birthday);
		int yearBirth = cal.get(1);
		int monthBirth = cal.get(2) + 1;
		int dayOfMonthBirth = cal.get(5);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}
		return Integer.valueOf(age);
	}

	public static Date getMaxDate(ArrayList<Date> dates) {
		if (dates.isEmpty()) {
			return null;
		}
		Date dateFirst = null;
		for (int i = 0; i < dates.size(); i++) {
			if (dates.get(i) != null) {
				dateFirst = (Date) dates.get(i);
				break;
			}
		}

		ArrayList deleteDate = new ArrayList();
		for (int i = 1; i < dates.size(); i++) {
			if (dates.get(i) == null)
				deleteDate.add(null);
			else if (((Date) dates.get(i)).before(dateFirst)) {
				deleteDate.add((Date) dates.get(i));
			}
		}
		if (deleteDate.isEmpty()) {
			if (dates.size() == 1) {
				return (Date) dates.get(0);
			}
			dates.remove(dateFirst);
		} else {
			dates.removeAll(deleteDate);
		}
		return getMaxDate(dates);
	}

	public static Date getBeforeMonth(String date, int before) {
		Date d = null;
		if (before == 0) {
			d = parseDate(date, "yyyy-MM-dd");
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(parseDate(date, "yyyy-MM-dd"));
			c.add(2, before);
			d = c.getTime();
		}
		return d;
	}

	public static Date getBeforeDay(String date, int before) {
		Date d = null;
		if (before == 0) {
			d = parseDate(date, "yyyy-MM-dd");
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(parseDate(date, "yyyy-MM-dd"));
			c.add(6, before);
			d = c.getTime();
		}
		return d;
	}

	public static int getSeasonInt(Date date) {
		String nowYear = formatDate("yyyy", date);

		Date date1 = parseDate(nowYear + "-02-04", "yyyy-MM-dd");
		Date date2 = parseDate(nowYear + "-05-05", "yyyy-MM-dd");
		Date date3 = parseDate(nowYear + "-08-07", "yyyy-MM-dd");
		Date date4 = parseDate(nowYear + "-11-07", "yyyy-MM-dd");

		if (date.before(date1))
			return 4;
		if (date.before(date2))
			return 1;
		if (date.before(date3))
			return 2;
		if (date.before(date4)) {
			return 3;
		}
		return 4;
	}

	public static int getWeekOfDate(Date date) {
		int[] weekDays = { 0, 1, 2, 3, 4, 5, 6 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(7) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	public static boolean isValidDate(String str, String format) {
		boolean convertSuccess = true;

		SimpleDateFormat date = new SimpleDateFormat(format);
		try {
			date.setLenient(false);
			date.parse(str);
		} catch (ParseException e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}

	public static boolean isOutNowTime(String time) {
		boolean boll = false;
		if (isValidDate(time, "HH:mm:ss")) {
			Date nowDate = parseDate(formatDate("HH:mm:ss", new Date()),
					"HH:mm:ss");
			if (parseDate(time, "HH:mm:ss").after(nowDate))
				boll = true;
		}
		if (isValidDate(time, "yyyy-MM-dd HH:mm:ss")) {
			Date nowDate = parseDate(
					formatDate("yyyy-MM-dd HH:mm:ss", new Date()),
					"yyyy-MM-dd HH:mm:ss");
			if (parseDate(time, "yyyy-MM-dd HH:mm:ss").after(nowDate))
				boll = true;
		}
		if (isValidDate(time, "yyyy-MM-dd")) {
			Date nowDate = parseDate(formatDate("yyyy-MM-dd", new Date()),
					"yyyy-MM-dd");
			if (parseDate(time, "yyyy-MM-dd").after(nowDate))
				boll = true;
		}
		return boll;
	}

	public static int getMonthDays(Date date, int num) {
		int total = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int days = cal.getActualMaximum(5);

		int dayofMonth = cal.get(5);
		total = days - dayofMonth;
		for (int i = 1; i <= num; i++) {
			cal.add(2, 1);

			int dayNextMonth = cal.getActualMaximum(5);
			total += dayNextMonth;
		}
		return total;
	}

	public static List<String> getDaysByStartTimeToEndTime(String startTime,
			String endTime, String format) {
		if ((StringUtils.isEmpty(startTime)) || (StringUtils.isEmpty(endTime)))
			return null;
		if (parseDate(startTime, "yyyy-MM-dd").getTime() > parseDate(endTime,
				"yyyy-MM-dd").getTime())
			return null;
		List list = new ArrayList();
		if ((formatDate(format, parseDate(startTime, format))
				.equals(formatDate(format, parseDate(endTime, format))))
				&& (!list.contains(formatDate(format,
						parseDate(startTime, format))))) {
			list.add(formatDate(format, parseDate(startTime, format)));
		}
		while (parseDate(startTime, "yyyy-MM-dd").getTime() <= parseDate(
				endTime, "yyyy-MM-dd").getTime()) {
			if (!list
					.contains(formatDate(format, parseDate(startTime, format))))
				list.add(formatDate(format, parseDate(startTime, format)));
			startTime = format(getAppointDate(
					parseDate(startTime, "yyyy-MM-dd"), 1));
		}
		return list;
	}

}