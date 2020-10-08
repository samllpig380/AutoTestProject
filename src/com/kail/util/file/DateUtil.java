package com.kail.util.file;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 
 * <p>
 * 功能描述: [日期处理类]
 * </p>
 * 
 * @author yhao
 * @date:2014-10-22/下午4:10:04
 * @version
 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
 */
public class DateUtil {

	/**
	 * 
	 * <p>功能描述:[字符串转日期]</p>
	 * @param format
	 * @param strDate
	 * @return
	 * @author:yhao
	 * @date:2014-10-31/下午4:04:20
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static Date stringToDate(String format, String strDate){
		SimpleDateFormat df;
		Date date = null;
		df = new SimpleDateFormat(format);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 
	 * <p>
	 * 功能描述:[获取默认格式的字符串]
	 * </p>
	 * 
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:12:52
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static String getDate() {
		return getDate("yyyyMMddHHmmss");
	}

	/**
	 * 
	 * <p>
	 * 功能描述:[获取指定格式的字符串]
	 * </p>
	 * 
	 * @param fmt
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:12:04
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static String getDate(String fmt) {
		Date myDate = new Date(System.currentTimeMillis());
		SimpleDateFormat sDateformat = new SimpleDateFormat(fmt);
		return sDateformat.format(myDate).toString();
	}

	/**
	 * 
	 * <p>
	 * 功能描述:[获取指定日期的Calendar]
	 * </p>
	 * 
	 * @param strdate
	 * @param fmt
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:14:03
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static Calendar getCal(String strdate, String fmt) {
		Calendar cal = null;
		try {
			if ((strdate == null) || (fmt == null)) {
				return cal;
			}
			SimpleDateFormat nowDate = new SimpleDateFormat(fmt);
			Date d = nowDate.parse(strdate, new ParsePosition(0));
			if (d == null) {
				return cal;
			}
			cal = Calendar.getInstance();
			cal.clear();
			cal.setTime(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cal;
	}
	
	
	/**
	 * 
	 * <p>功能描述:[获取java.Util.Date]</p>
	 * @param stringDate
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:34:39
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static Date getUtilDate(String stringDate)
	{
		SimpleDateFormat df;
		Date date = null;
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		try {
			date = df.parse(stringDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		return date;
	}

	/**
	 * 
	 * <p>
	 * 功能描述:[根据字符串日期值以及格式得到此日期是当年的第几周]
	 * </p>
	 * 
	 * @param strdate
	 * @param fmt
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:16:13
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static int getWeekOfYear(String strdate, String fmt) {
		int ret = -1;
		try {
			if ((strdate == null) || (fmt == null)) {
				return ret;
			}
			Calendar cal = getCal(strdate, fmt);
			if (cal == null) {
				return ret;
			}
			ret = cal.get(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 
	 * <p>
	 * 功能描述:[根据日期得到此日期是所在周的第几天]
	 * </p>
	 * 
	 * @param strdate
	 * @param oldfmt
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:17:21
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static String getDayOfWeek(String strdate, String oldfmt) {
		String sWeek = null;
		try {
			if ((strdate == null) || (oldfmt == null)) {
				return sWeek;
			}
			Calendar cal = getCal(strdate, oldfmt);
			if (cal == null) {
				return sWeek;
			}
			int iWeek = cal.get(7);
			sWeek = "" + ((iWeek - 1 != 0) ? iWeek - 1 : 7);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sWeek;
	}
	/**
	 * 
	 * <p>功能描述:[指定日期前指定分钟的日期]</p>
	 * @param deftime
	 * @param oldfmt
	 * @param timediff
	 * @param newfmt
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:19:00
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static String getBeforeTimeByMinute(String deftime, String oldfmt,
			int timediff, String newfmt) {
		String rq = null;
		try {
			if ((deftime == null) || (deftime.equals(""))) {
				return rq;
			}
			Calendar cal = getCal(deftime, oldfmt);
			if (cal == null) {
				return rq;
			}
			cal.add(12, -timediff);
			SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
			rq = sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rq;
	}
	
	/**
	 * 
	 * <p>功能描述:[指定日期前指定天数的日期]</p>
	 * @param deftime
	 * @param oldfmt
	 * @param timediff
	 * @param newfmt
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:42:15
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	
	public static String getBeforeTimeByDay(String deftime, String oldfmt,
			int timediff, String newfmt) {
		String rq = null;
		try {
			if ((deftime == null) || (deftime.equals(""))) {
				return rq;
			}
			Calendar cal = getCal(deftime, oldfmt);
			if (cal == null) {
				return rq;
			}
			cal.add(Calendar.DAY_OF_MONTH, -timediff);
			SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
			rq = sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rq;
	}

	
	/**
	 * 
	 * <p>功能描述:[指定日期前指定月份的日期]</p>
	 * @param deftime
	 * @param oldfmt
	 * @param timediff
	 * @param newfmt
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:20:54
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static String getBeforeTimeByMonth(String deftime,
			String oldfmt, int timediff, String newfmt) {
		String rq = null;
		try {
			if ((deftime == null) || (deftime.equals(""))) {
				return rq;
			}
			Calendar cal = getCal(deftime, oldfmt);
			if (cal == null) {
				return rq;
			}
			cal.add(2, -timediff);
			SimpleDateFormat sdf = new SimpleDateFormat(newfmt);
			rq = sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rq;
	}

	/**
	 * 
	 * <p>功能描述:[获取日期差值日期]</p>
	 * @param fmt
	 * @param day
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:21:47
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static String getDateByDay(String fmt,int day) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, day);
		return new SimpleDateFormat(fmt).format(cal.getTime());
	}
	
	/**
	 * 
	 * <p>功能描述:[日期格式转化]</p>
	 * @param mydate
	 * @param oldfmt
	 * @param newfmt
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:22:39
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static  String fmtDate(String mydate, String oldfmt,
			String newfmt) {
		String restr = null;
		try {
			if ((mydate == null) || (oldfmt == null) || (newfmt == null)) {
				return restr;
			}
			SimpleDateFormat newDate = new SimpleDateFormat(newfmt);
			Calendar cal = getCal(mydate, oldfmt);
			if (cal == null) {
				return restr;
			}
			restr = newDate.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return restr;
	}

	/**
	 * 
	 * <p>功能描述:[得到给定时间之后的毫秒数的日期]</p>
	 * @param date
	 * @param timespan
	 * @param newfmt
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:23:20
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static  String getAfterDateByMillisecond(Date date,
			long timespan, String newfmt) {
		long milli_second = date.getTime();
		Date new_Date = new Date(milli_second + timespan);
		SimpleDateFormat sDateformat = new SimpleDateFormat(newfmt);
		return sDateformat.format(new_Date).toString();
	}
	
	/**
	 * 
	 * <p>功能描述:[获取当前时间的日期]</p>
	 * @param format
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:24:11
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static  String getCurrentTimeByFormat(String format) {
		if (format == null || "".equals(format)) {
			return "";
		}
		String result = "";
		String nowDate = getDate("yyyy-MM-dd HH:mm:ss.SSS");
		if ("yyyy".equalsIgnoreCase(format)) {
			result = nowDate.substring(0, 4);
		} else if ("MM".equals(format)) {
			result = nowDate.substring(nowDate.indexOf("-") + 1,
					nowDate.lastIndexOf("-"));
		} else if ("dd".equalsIgnoreCase(format)) {
			result = nowDate.substring(nowDate.lastIndexOf("-") + 1,
					nowDate.indexOf(" "));
		} else if ("HH".equalsIgnoreCase(format)) {
			result = nowDate.substring(nowDate.indexOf(" ") + 1,
					nowDate.indexOf(":"));
		} else if ("mm".equals(format)) {
			result = nowDate.substring(nowDate.indexOf(":") + 1,
					nowDate.lastIndexOf(":"));
		} else if ("ss".equalsIgnoreCase(format)) {
			result = nowDate.substring(nowDate.lastIndexOf(":") + 1,
					nowDate.indexOf("."));
		} else if ("SSS".equalsIgnoreCase(format)) {
			result = nowDate.substring(nowDate.indexOf(".") + 1);
		}
		return result;
	}
	/**
	 * 
	 * <p>功能描述:[获取两个日期相差天数]</p>
	 * @param adtDate1
	 * @param adtDate2
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:35:09
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	public static int daysBetweenByDay(Date adtDate1, Date adtDate2) {
		long quot = adtDate1.getTime() - adtDate2.getTime();
		return (int) (quot / 86400000);
	}
	
	/**
	 * 
	 * <p>功能描述:[获取两个日期相差天数]</p>
	 * @param str1
	 * @param str2
	 * @return
	 * @author:yhao
	 * @date:2014-10-22/下午4:35:37
	 * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
	 */
	
	public static int daysBetweenByDay(String str1, String str2) {
		Date adtDate1 = DateUtil.getUtilDate(str1);
		Date adtDate2 = DateUtil.getUtilDate(str2);
		long quot = adtDate1.getTime() - adtDate2.getTime();
		return (int) (quot / 86400000);
	}
}
