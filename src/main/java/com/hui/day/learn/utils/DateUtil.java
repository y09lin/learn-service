package com.hui.day.learn.utils;

import com.hui.day.learn.response.codes.Default0Code;
import com.hui.day.learn.response.exception.GlobalException;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 适合各种时间转换的 DateUtil
 * 后续改为 joda-time 来实现
 */
public class DateUtil {

	public final static String FORMAT_DATE_TIME_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public final static String FORMAT_DATE_TIME_YMDHM = "yyyy-MM-dd HH:mm";

	public final static String FORMAT_DATE_TIME_YMDHMS_ZN = "yyyy年MM月dd日 HH:mm:ss";
	
	public final static String FORMAT_DATE_YMD = "yyyy-MM-dd";

	public static void main(String[] args){
	}

    /**
     * date 转化成 String(yyyy-MM-dd)
     */
    public static String toStringYMD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_YMD);
        return sdf.format(date);
    }

    public static String toString(Date date, String format){
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	return sdf.format(date);
	}

    /**
     * date 转化成 String(yyyy-MM-dd HH:mm:ss)
     */
    public static String toStringYMDHMS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_TIME_YMDHMS);
        return sdf.format(date);
    }

    /**
     * date 转化成 String(yyyyMMddHHmmss)
     */
    public static String toStringYMDHMSNoBridge(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    /**
     * date 转化成 String(yyyyMMddHHmmss)
     */
    public static String toStringYMDHMSSNoBridge(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(date);
    }

    /**
     * string 转化成 date
     */
    public static Date toDateYMD(String str) {
    	if(StringUtils.isEmpty(str)) {
    		return null;
    	}
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_YMD);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            throw new GlobalException(Default0Code.DATE_ERROR, e);
        }
    }

    /**
     * string 转化成 date
     */
    public static Date toDateYMDHMS(String str) {
    	if(StringUtils.isEmpty(str)) {
    		return null;
    	}
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_TIME_YMDHMS);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            throw new GlobalException(Default0Code.DATE_ERROR, e);
        }
    }

    /**
	 * 时间相减（毫秒）
	 */
	public static long reduceTimeMil(Date time1, Date time2) {
		try {
			long t1 = time1.getTime();
			long t2 = time2.getTime();
			if(t1-t2 < 0) {
				return -1;
			}
			return t1-t2;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

    /**
	 * 时间相减（毫秒）
	 */
	public static long reduceTimeMil(String time1, String time2) {
		try {
			long t1 = toDateYMDHMS(time1).getTime();
			long t2 = toDateYMDHMS(time2).getTime();
			if(t1-t2 < 0) {
				return -1;
			}
			return t1-t2;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 时间相减（秒）
	 */
	public static long reduceTimeSec(String time1, String time2) {
		return reduceTimeMil(time1, time2)/1000;
	}

    /**
	 * 时间相减（分），四舍五入
	 */
	public static long reduceTimeMin(String time1, String time2) {
		long second = reduceTimeSec(time1, time2);
		if(second%60 > 0) {
			return (second/60+1);
		}
		return (second/60);
	}

    /**
     * 获取当前时间戳
     */
    public static Long getTimeStamp() {

        try {
            Date date = new Date();

            Long id = date.getTime();
            return id;

        } catch (Exception e) {
            throw new GlobalException(Default0Code.DATE_ERROR, e);
        }
    }
}