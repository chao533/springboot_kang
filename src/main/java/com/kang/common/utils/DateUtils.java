package com.kang.common.utils;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>Title: DateUtils</p>  
 * <p>Description: 日期实用工具类</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
public class DateUtils {
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYY_MM_DD_TIME_MIN = "yyyy-MM-dd HH:mm";
	public static final String TIME = "HH:mm:ss";
	public static final String TIME_MIN = "HH:mm";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMM = "yyyyMM";
	public static final String YYYY_MM = "yyyy-MM";
	public static final String MM_DD = "MM-dd";
	public static int weeks = 0;

	/**
	 * <p>Title: parseDate</p>  
	 * <p>Description: 日期格式转换为字符串</p>  
	 * @param date 日期
	 * @param formatStr 日期格式
	 * @return 转换后的日期字符串
	 */
	public static String parseDate(Date date, String formatStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
		if (date == null) {
			return null;
		}
		return dateFormat.format(date);
	}
	
	/**
	 * <p>Title: toDate</p>  
	 * <p>Description: 将字符串转换为日期格式</p>  
	 * @param strDateTime 日期字符串
	 * @param dateTimeFormat 日期格式
	 * @return 日期
	 */
	public static Date toDate(String strDateTime, String dateTimeFormat) {
		if ((strDateTime == null) || (strDateTime.length() == 0)
				|| (dateTimeFormat == null) || (dateTimeFormat.length() == 0)) {
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
		Date date = dateFormat.parse(strDateTime, new ParsePosition(0));

		if (date == null) {
			return null;
		}
		return date;
	}
	
	/**
	 * <p>Title: getDate</p>  
	 * <p>Description: 根据时分秒获取date时间</p>  
	 * @param date  日期
	 * @param hour 时
	 * @param min 分
	 * @param sec 秒
	 * @return 具体的日期（包含指定的时分秒）
	 */
	public static Date getDate(Date date, int hour, int min, int sec) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(10, hour);
		cal.set(12, min);
		cal.set(13, sec);
		return cal.getTime();
	}

	/**
	 * <p>Title: toTimestamp</p>  
	 * <p>Description: 将日期字符串转换为Timestamp格式</p>  
	 * @param dateTimeStr 日期字符串
	 * @param dateTimeFormat 格式
	 * @return
	 */
	public static Timestamp toTimestamp(String dateTimeStr,String dateTimeFormat) {
		return toTimestamp(toDate(dateTimeStr, dateTimeFormat));
	}
	

	/**
	 * <p>Title: toTimestamp</p>  
	 * <p>Description: 将日期Date转换为Timestamp</p>  
	 * @param date 日期
	 * @return
	 */
	public static Timestamp toTimestamp(Date date) {
		if (date == null) {
			return null;
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * <p>Title: isSmallerThan</p>  
	 * <p>Description: 日期比较，小于</p>  
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return 是/否
	 */
	public static boolean isSmallerThan(Date startTime, Date endTime) {
		if ((startTime == null) || (endTime == null)) {
			return true;
		}
		if (startTime.getTime() > endTime.getTime()) {
			return false;
		}
		return true;
	}
	

	/**
	 * <p>Title: addDate</p>  
	 * <p>Description: 日期增加</p>  
	 * @param type
	 * @param date
	 * @param num
	 * @return
	 */
	public static final Date addDate(int type, Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(type, num);
		return cal.getTime();
	}

	/**
	 * <p>Title: addDaysToDate</p>  
	 * <p>Description: 增加天</p>  
	 * @param date
	 * @param numDays
	 * @return
	 */
	public static Date addDaysToDate(Date date, int numDays) {
		if (date == null) {
			return null;
		}
		return addDate(5, date, numDays);
	}
	

	/**
	 * <p>Title: addHoursToDate</p>  
	 * <p>Description: 增加小时</p>  
	 * @param date
	 * @param numHours
	 * @return
	 */
	public static Date addHoursToDate(Date date, int numHours) {
		if (date == null) {
			return null;
		}
		return addDate(11, date, numHours);
	}

	/**
	 * <p>Title: addMinutesToDate</p>  
	 * <p>Description: 增加分钟</p>  
	 * @param date
	 * @param numMins
	 * @return
	 */
	public static Date addMinutesToDate(Date date, int numMins) {
		if (date == null) {
			return null;
		}
		return addDate(12, date, numMins);
	}

	/**
	 * <p>Title: addMonthsToDate</p>  
	 * <p>Description: 增加月</p>  
	 * @param date
	 * @param numMonths
	 * @return
	 */
	public static Date addMonthsToDate(Date date, int numMonths) {
		if (date == null) {
			return null;
		}
		return addDate(2, date, numMonths);
	}

	/**
	 * <p>Title: addYearsToDate</p>  
	 * <p>Description: 增加年</p>  
	 * @param date
	 * @param numYears
	 * @return
	 */
	public static Date addYearsToDate(Date date, int numYears) {
		if (date == null) {
			return null;
		}
		return addDate(1, date, numYears);
	}

	/**
	 * <p>Title: compareMonth</p>  
	 * <p>Description: 月份比较</p>  
	 * @param st
	 * @param end
	 * @return
	 */
	public static int compareMonth(Date st, Date end) {
		int y = Math.abs((getYear(end) < 0 ? 0 : getYear(end))
				- (getYear(st) < 0 ? 0 : getYear(st)));
		int m = 0;
		if (y > 0) {
			y--;
			m = Math.abs(12 - getMonth(st) + getMonth(end));
		} else {
			m = Math.abs(getMonth(end) - getMonth(st));
		}
		return y * 12 + m;
	}

	/**
	 * <p>Title: compare</p>  
	 * <p>Description: 时间比较</p>  
	 * @param start
	 * @param end
	 * @return
	 */
	public static long compare(Date start, Date end) {
		if ((start != null) && (end != null)) {
			return end.getTime() - start.getTime();
		}
		return 0L;
	}

	/**
	 * <p>Title: getYear</p>  
	 * <p>Description: 获得当前的年份</p>  
	 * @return
	 */
	public static int getYear() {
		return getYear(new Date());
	}

	/**
	 * <p>Title: getYear</p>  
	 * <p>Description: 获得制定日期的年份</p>  
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return -1;
		}
		return DateToCalendar(date).get(1);
	}

	/**
	 * <p>Title: getMonth</p>  
	 * <p>Description: 获得当前月份</p>  
	 * @return
	 */
	public static int getMonth() {
		return getMonth(new Date());
	}

	/**
	 * 获得指定日期的月份
	 * 
	 * @author: binyang4
	 * @createTime: 2014年9月28日 下午10:04:31
	 * @param date
	 * @return int
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return 0;
		}
		return DateToCalendar(date).get(2) + 1;
	}

	/**
	 * 获得当前的天
	 * 
	 * @author: binyang4
	 * @createTime: 2014年9月28日 下午10:04:51
	 * @return int
	 */
	public static int getDay() {
		return getDay(new Date());
	}

	/**
	 * 获得指定日期的天
	 * 
	 * @author: binyang4
	 * @createTime: 2014年9月28日 下午10:05:09
	 * @param da
	 * @return int
	 */
	public static int getDay(Date da) {
		if (da == null) {
			return 0;
		}
		return DateToCalendar(da).get(5);
	}

	/**
	 * Date转换为Calendar
	 * 
	 * @author: binyang4
	 * @createTime: 2014年9月28日 下午10:06:18
	 * @param date
	 * @return Calendar
	 */
	public static Calendar DateToCalendar(Date date) {
		Calendar cc = Calendar.getInstance();
		cc.setTime(date);
		return cc;
	}

	/**
	 * 获取上个星期的周日
	 * 
	 * @author: binyang4
	 * @createTime: 2014年9月28日 下午10:06:48
	 * @param date
	 * @return Date
	 */
	public static Date getUpWeekDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cc = Calendar.getInstance();
		cc.setTime(date);
		int week = cc.get(7);
		return addDaysToDate(date, 1 - week);
	}

	/**
	 * 获取星期一
	 * 
	 * @author: binyang4
	 * @createTime: 2014年9月28日 下午10:07:50
	 * @param date
	 * @return Date
	 */
	public static Date getMonday(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cc = Calendar.getInstance();
		cc.setTime(date);
		int week = cc.get(7);
		return addDaysToDate(date, 2 - week);
	}

	/**
	 * 
	 * @author: binyang4
	 * @createTime: 2014年9月28日 下午10:09:38
	 * @param date
	 * @return int
	 */
	public static int getWeek(Date date) {
		if (date == null) {
			return -1;
		}
		Calendar cc = Calendar.getInstance();
		cc.setTime(date);
		int week = cc.get(7);
		if (week == 1)
			week = 7;
		else {
			week--;
		}
		return week;
	}

	/**
	 * 获取相应位数的随机数
	 * 
	 * @author: binyang4
	 * @createTime: 2014年9月28日 下午10:10:06
	 * @param lo
	 * @return String
	 */
	public static String getRandNum(int lo) {
		if (lo < 1) {
			lo = 4;
		}
		StringBuffer temp = new StringBuffer();
		Random rand = new Random();
		for (int i = 0; i < lo; i++) {
			temp.append(String.valueOf(rand.nextInt(10)));
		}
		return temp.toString();
	}

	/**
	 * 当前日期加四位随机数
	 * 
	 * @author: binyang4
	 * @createTime: 2014年9月28日 下午10:11:58
	 * @return String
	 */
	public static String getDataName() {
		return parseDate(new Date(), YYYYMMDDHHMMSS) + getRandNum(4);
	}

	/**
	 * 无格式日期字符串
	 * 
	 * @author: binyang4
	 * @createTime: 2014年9月28日 下午10:12:54
	 * @param date
	 * @return String
	 */
	public static String formatDS(String date) {
		if (date == null) {
			return "";
		}
		return date.replace("-", "").replace(":", "").replace(" ", "");
	}

	/**
	 * 
	 * @author: yhzhao
	 * @createTime: 2016年4月19日 下午2:26:04
	 * @param date
	 * @return List<String>
	 */
	@SuppressWarnings("deprecation")
	public static List<String> getBeforeMonth(Date date) {
		List<String> monthList = new ArrayList<String>();

		int month = date.getMonth() + 1;
		for (int i = 1; i <= month; i++) {
			if (i < 10) {
				monthList.add("0" + i);
			} else {
				monthList.add("" + i);
			}
		}
		return monthList;
	}

	/**
	 * 秒数转换成(分:秒)
	 * 
	 * @param time
	 * @return
	 */
	public static String secToTime(int time) {
		String timeStr = null;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			second = time % 60;
			timeStr = unitFormat(minute) + ":" + unitFormat(second);

		}
		return timeStr;
	}

	/**
	 * 补0操作
	 * 
	 * @param i
	 * @return
	 */
	private static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	/**
	 * 
	 * 获得当前日期与本周一相差的天数
	 * 
	 * @author: yhzhao
	 * @createTime: 2014年12月15日 下午1:22:09
	 * @return int
	 */
	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return -6;
		} else {
			return 2 - dayOfWeek;
		}
	}
	

	/**
	 * 
	 * 获得本周星期一的日期
	 * 
	 * @author: yhzhao
	 * @createTime: 2014年12月15日 下午1:28:44
	 * @return String
	 */
	public static String getCurrentMonday() {
		weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	/**
	 * 获得当前时间
	 * 
	 * @author: yhzhao
	 * @createTime: 2014年12月15日 下午3:00:23
	 * @return String
	 */
	public static String getNowDay() {
		GregorianCalendar currentDate = new GregorianCalendar();
		Date nowDate = currentDate.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		String nowDay = sdf.format(nowDate);
		return nowDay;
	}

	/**
	 * 获取这个月1号
	 * 
	 * @author: yhzhao
	 * @createTime: 2014年12月15日 下午3:02:49
	 * @return String
	 */
	public static String getCurrentMonthHead() {
		Calendar c = Calendar.getInstance();
		c.set(GregorianCalendar.DAY_OF_MONTH, 1);
		Date MonthHead = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		String preMonthHead = sdf.format(MonthHead);
		return preMonthHead;
	}

	/**
	 * 获取今年1号
	 * 
	 * @author: yhzhao
	 * @createTime: 2014年12月15日 下午3:02:49
	 * @return String
	 */
	public static String getCurrentYearHead() {
		int months = 0;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + months);
		c.set(Calendar.DAY_OF_YEAR, 1);
		Date MonthHead = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
		String preMonthHead = sdf.format(MonthHead);
		return preMonthHead;
	}

	/**
	 * 秒转换为分钟
	 * 
	 * @param
	 * @return
	 */
	public static String secToMin(String secStr) {
		if (secStr == null || "".equals(secStr)) {
			return "";
		}

		int minute = 0;
		int second = 0;
		int sec = Integer.parseInt(secStr);

		minute = sec / 60;
		second = sec % 60;
		StringBuilder sb = new StringBuilder(minute);
		sb.append("分");
		if (second > 0) {
			sb.append(second);
			sb.append("秒");
		}
		return sb.toString();

	}
	
	/**
	* 拼接Week    1,2,3
	* @author: weixu
	* @createTime: 2016年12月29日 下午2:51:05
	* @param beginDate
	* @param endDate
	* @return String
	*/
	public static String printWeeks(String beginDate,String endDate){
		Date begin = DateUtils.toDate(beginDate, DateUtils.YYYY_MM_DD);
		Date end = DateUtils.toDate(endDate, DateUtils.YYYY_MM_DD);
		String result = "";
		int week = 0;
		Integer count = 0;
		do {
			if (count >= 7) {
				break;
			}
			week = DateUtils.getWeek(begin);
			count++;
			result += week + ",";
			begin = DateUtils.addDaysToDate(begin, 1);
		} while (begin.compareTo(end) <= 0);
		if (result !=null)
			result = result.substring(0, result.length() - 1);
		return result;
	}
	
	/**
	* 计算星期出现次数，从开始到结束，星期一（5），星期二（4）.。。。
	* @author: weixu
	* @createTime: 2016年9月26日 下午2:06:29
	* @param beginDate
	* @param endDate
	* @return Map<Integer,Integer>
	*/
	public static Map<Integer,Integer> sumWeekCount(String beginDate,String endDate){
		Date begin = DateUtils.toDate(beginDate, DateUtils.YYYY_MM_DD);
		Date end = DateUtils.toDate(endDate, DateUtils.YYYY_MM_DD);
		Map<Integer,Integer> result = new HashMap<>();
		int week = 0;
		Integer count = 0;
		do {
			week = DateUtils.getWeek(begin);
			count = result.get(week);
			if (count == null) count = 0;
			count++;
			result.put(week, count);
			begin = DateUtils.addDaysToDate(begin, 1);
		} while (begin.compareTo(end) <= 0);
		return result;
	}
//	
//	/**
//	 * 获取当天的星期数
//	 * @param t
//	 * @return
//	 */
//	public static Integer getWeek(Date t){
//		if(t==null){
//			return null;
//		}
//		Calendar cal = Calendar.getInstance();
//		 cal.setTime(t);
//		 Integer w = cal.get(Calendar.DAY_OF_WEEK);
//	     return w;
//	}

	/**
	* 获得开始日期和结束日期
	* @author: weixu
	* @createTime: 2017年2月7日 上午9:36:54
	* @param date
	* @param pattern
	* @return Map<String,String>
	*/
	public static Map<String,String> getMonthStartEnd(Date date,String pattern){
		SimpleDateFormat df=new SimpleDateFormat(pattern);
		Map<String,String> result=new HashMap<String,String>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		String start=df.format(calendar.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String end=df.format(calendar.getTime());
		result.put("start",start);
		result.put("end",end);
		return result;

	}
	

}
