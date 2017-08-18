package cn.teamstack.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by zhouli on 2015/10/9.
 */
public class DateUtil extends Date {
    private static DateUtil instance = null;

    public DateUtil() {

    }

    /**
     * 日期格式转换成字符串类型
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.CHINA);
        String nowTime = dateFormat.format(date);
        return nowTime;
    }

    /**
     * 日期格式转换成字符串类型
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String nowTime = dateFormat.format(date);
        return nowTime;
    }

    /**
     * 字符串转换成日期格式
     *
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date, String format)
            throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.CHINA);
        Date nowTime = dateFormat.parse(date);
        return nowTime;
    }

    /**
     * 字符串转换成日期格式
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date)
            throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date nowTime = dateFormat.parse(date);
        return nowTime;
    }

    public static Date parseTime(String time)
            throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        Date nowTime = dateFormat.parse(time);
        return nowTime;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowTime() {
        return getNowTime("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据格式化参数获取当前时间
     *
     * @param dateFormatString
     * @return
     */
    public static String getNowTime(String dateFormatString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString,
                Locale.CHINA);
        String nowTime = dateFormat.format(new Date());
        return nowTime;
    }


    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }


    public static Date addDays(Date date, int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }


    public static String strformat(String str, int length, String flag) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(flag);
        }
        return sb.toString();
    }

    /**
     * 计算给定时间后的日期
     *
     * @param date
     * @param days
     * @return
     * @throws ParseException
     */
    public static String getTime(String date, int days) {
        try {
            Date date1 = parseDate(date, "yyyy-MM-dd HH:mm:ss");
            Date date2 = addDays(date1, days);
            return formatDate(date2);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算给定时间后的日期
     *
     * @param date
     * @param days
     * @return
     * @throws ParseException
     */
    public static String getTime(String date, int days, String format) {
        try {
            Date date1 = parseDate(date, format);
            Date date2 = addDays(date1, days);
            return formatDate(date2, format);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断是否超期（createDate+day）和当期时间比较 。大则表示超期
     *
     * @param createDate
     * @param day
     * @return
     */
    public static boolean isExtend(String createDate, int day) {
        Date date1 = new Date(System.currentTimeMillis());
        Date date2;
        try {
            date2 = parseDate(createDate, "yyyyMMddHHmmss");
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        Date date3 = addDays(date2, day);
        return date1.after(date3);
    }

    public static boolean isExtend(String createDate, String format, int day) {
        Date date1 = new Date(System.currentTimeMillis());
        Date date2;
        try {
            date2 = parseDate(createDate, format);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        Date date3 = addDays(date2, day);
        return date1.after(date3);
    }

    /**
     * 计算2个日期的时间差
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getTimeDelta(Date date1, Date date2) {
        int c = (int) ((date1.getTime() - date2.getTime()) / 1000);
        return c;
    }

    public static int getTimeDelta(Date date) {
        return getTimeDelta(new Date(), date);
    }

    /**
     * 计算当前时间和指定日期的时间差
     *
     * @param dateStr
     * @return
     */
    public static int getTimeDelta(String dateStr) {
        Date date = null;
        try {
            date = parseDate(dateStr, "yyyyMMddHHmmss");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getTimeDelta(new Date(), date);
    }

    public static int getTimeDelta(String dateStr, String format) {
        Date date = null;
        try {
            date = parseDate(dateStr, format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getTimeDelta(new Date(), date);
    }

    public static String getNewTimeByMinute(int hour) {//延迟小时数
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, hour);
        String newtime = format.format(c.getTime());
        return newtime;
    }

    /**
     * 获取制定时间 当日最早时间
     *
     * @param date
     * @return
     */
    public static Date getMinTimeByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static DateUtil getInstance() {
        if (instance == null) {
            instance = new DateUtil();
        }
        return instance;
    }


    public static Calendar getCurrentCalendar() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式

        String dateTime = dateFormat.format(now);
        return Calendar.getInstance();//可以对每个时间域单独修改

    }

    public static int getCurrentYear() {
        Calendar calendar = getCurrentCalendar();
        return calendar.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        Calendar calendar = getCurrentCalendar();
        return calendar.get(Calendar.MONTH);
    }

    public static int getCurrentDay() {
        Calendar calendar = getCurrentCalendar();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getCurrentHour() {
        Calendar calendar = getCurrentCalendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 获取某年某月的天数
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断日期是否有效,包括闰年的情况
     *
     * @param date YYYY-mm-dd
     * @return
     */
    public static boolean isDate(String date) {
        StringBuffer reg = new StringBuffer(
                "^((\\d{2}(([02468][048])|([13579][26]))-?((((0?");
        reg.append("[13578])|(1[02]))-?((0?[1-9])|([1-2][0-9])|(3[01])))");
        reg.append("|(((0?[469])|(11))-?((0?[1-9])|([1-2][0-9])|(30)))|");
        reg.append("(0?2-?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12");
        reg.append("35679])|([13579][01345789]))-?((((0?[13578])|(1[02]))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))");
        reg.append("-?((0?[1-9])|([1-2][0-9])|(30)))|(0?2-?((0?[");
        reg.append("1-9])|(1[0-9])|(2[0-8]))))))");
        Pattern p = Pattern.compile(reg.toString());
        return p.matcher(date).matches();
    }

    /**
     * 功能：转换为Calendar。
     *
     * @return Calendar
     */
    public Calendar toCalendar() {
        Calendar c = Calendar.getInstance();
        c.setTime(this);
        return c;
    }

    /**
     * 功能：判断日期是否和当前date对象在同一天。
     *
     * @param date 比较的日期
     * @return boolean 如果在返回true，否则返回false。
     */
    public boolean isSameDay(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("日期不能为null");
        }
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);
        return this.isSameDay(cal2);
    }

    /**
     * 功能：判断日期是否和当前date对象在同一天。
     *
     * @param cal 比较的日期
     * @return boolean 如果在返回true，否则返回false。
     */
    public boolean isSameDay(Calendar cal) {
        if (cal == null) {
            throw new IllegalArgumentException("日期不能为null");
        }
        //当前date对象的时间
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(this);
        return (cal1.get(Calendar.ERA) == cal.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR));
    }


    /**
     * 功能：将当前日期的秒数进行重新设置。
     *
     * @param second 秒数
     * @return 设置后的日期
     * @author 沙琪玛 QQ：862990787
     * Jul 31, 2013 2:42:36 PM
     */
    public Date setSecondNew(int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(this);
        c.set(Calendar.SECOND, second);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：将当前日期的分钟进行重新设置。
     *
     * @param minute 分钟数
     * @return 设置后的日期
     * @author 沙琪玛 QQ：862990787
     * Jul 31, 2013 2:42:36 PM
     */
    public Date setMinuteNew(int minute) {
        Calendar c = Calendar.getInstance();
        c.setTime(this);
        c.set(Calendar.MINUTE, minute);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：将当前日期的小时进行重新设置。
     *
     * @param hour 小时数 (24小时制)
     * @return 设置后的日期
     */
    public Date setHourNew(int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(this);
        c.set(Calendar.HOUR_OF_DAY, hour);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：将当前日期的天进行重新设置。
     *
     * @param day 某一天
     * @return 设置后的日期
     */
    public Date setDayNew(int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(this);
        c.set(Calendar.DATE, day);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：将当前日期的月进行重新设置。
     *
     * @param month 某一月
     * @return 设置后的日期
     */
    public Date setMonthNew(int month) {
        Calendar c = Calendar.getInstance();
        c.setTime(this);
        c.set(Calendar.MONTH, month - 1);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：将当前日期的年进行重新设置。
     *
     * @param year 某一年
     * @return 设置后的日期
     */
    public Date setYearNew(int year) {
        Calendar c = Calendar.getInstance();
        c.setTime(this);
        c.set(Calendar.YEAR, year);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：得到当月有多少天。
     *
     * @return int
     */
    public int daysNumOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this);
        return cal.getActualMaximum(Calendar.DATE);
    }


    /**
     * 功能：当前时间增加毫秒数。
     *
     * @param milliseconds 正值时时间延后，负值时时间提前。
     * @return Date
     */
    public Date addMilliseconds(int milliseconds) {
        Calendar c = Calendar.getInstance();
        c.setTime(this);
        c.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND) + milliseconds);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：当前时间增加秒数。
     *
     * @param seconds 正值时时间延后，负值时时间提前。
     * @return Date
     */
    public static Date addSeconds(int seconds) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.SECOND, c.get(Calendar.SECOND) + seconds);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：当前时间增加分钟数。
     *
     * @param minutes 正值时时间延后，负值时时间提前。
     * @return Date
     */
    public static Date addMinutes(int minutes) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + minutes);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：当前时间增加小时数。
     *
     * @param hours 正值时时间延后，负值时时间提前。
     * @return Date
     */
    public static Date addHours(int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR, c.get(Calendar.HOUR) + hours);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：当前时间增加天数。
     *
     * @param days 正值时时间延后，负值时时间提前。
     * @return Date
     */
    public static Date addDays(int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DATE, c.get(Calendar.DATE) + days);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：当前时间增加月数。
     *
     * @param months 正值时时间延后，负值时时间提前。
     * @return Date
     */
    public static Date addMonths(int months) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + months);
        return new Date(c.getTimeInMillis());
    }

    /**
     * 功能：当前时间增加年数。注意遇到2月29日情况，系统会自动延后或者减少一天。
     *
     * @param years 正值时时间延后，负值时时间提前。
     * @return Date
     */
    public static Date addYears(int years) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) + years);
        return new Date(c.getTimeInMillis());
    }


    /**
     * 根据日期得到星期几,得到数字。<br/>
     * 7, 1, 2, 3, 4, 5, 6
     *
     * @return Integer 如：6
     */
    public int dayOfWeekInt() {
        Integer dayNames[] = {7, 1, 2, 3, 4, 5, 6};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek < 0)
            dayOfWeek = 0;
        return dayNames[dayOfWeek];
    }

}
