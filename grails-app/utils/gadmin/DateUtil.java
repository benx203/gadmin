package gadmin;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 *
 */
public class DateUtil {

    private static final String[] PATTERNS = new String[] { "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm:ss.0", "yyyy/MM/dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd HH:mm", "yyyy.MM.dd HH:mm", "yyyy-MM-dd HH", "yyyy/MM/dd HH", "yyyy.MM.dd HH", "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd","yyyy-MM"};
    private static final String NORMAL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String PATH_PATTERN = "yyyy_MM_dd_HH_mm_ss";
    private static final String SHORT_PATH_PATTERN = "yyMMddHHmmss";
    private static final String SHORT_PATTERN = "yyyy-MM-dd";
    private static final String SLASH_PATTERN = "yyyy/MM/dd";
    private static final String NO_SS_PATTERN = "yyyy-MM-dd HH:mm";
    private static final String MDHM_PATTERN = "MM-dd HH:mm";
    private static final String YY_MM_PATTERN = "yyyy-MM";
    private static final String HH_MM_PATTERN = "HH:mm";
    private static final String START_PATTERN = "yyyy-MM-dd 00:00:00";
    private static final String END_PATTERN = "yyyy-MM-dd 23:59:59";
    private static final String oldestDateStr = "1970-1-1";
    private static final Date oldestDate = stringToDate(oldestDateStr);

    /**
     * 将字符串转换成日期类型,自动匹配格式
     *
     * @param date
     * @return
     */
    public static Date stringToDate(String date) {
        if(StringUtil.isEmpty(date)){
            return null;
        }
        try {
            return DateUtils.parseDate(date, PATTERNS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串格式转日期
     *
     * @param date
     * @param parsePatterns
     * @return
     */
    public static Date stringToDate(String date, String... parsePatterns) {
        try {
            return DateUtils.parseDate(date, parsePatterns);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 日期转字符串:yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    @Deprecated
    public static String dateToString(Date date) {
        return dateToStr(date);
    }

    public static String dateToStr(Date date) {
        if (date == null) {
            return "";
        }
        return dateToString(date, NORMAL_PATTERN);
    }

    public static String dateToMDHMStr(Date date) {
        if (date == null) {
            return "";
        }
        return dateToString(date, MDHM_PATTERN);
    }

    public static String dateToStrNoSS(Date date) {
        if (date == null) {
            return "";
        }
        return dateToString(date, NO_SS_PATTERN);
    }

    public static Date noSSStrToDate(String string) {
        return stringToDate(string,NO_SS_PATTERN);
    }

    public static String dateToStr() {
        return dateToStr(new Date());
    }

    public static String dateToYYMMStr(Date date) {
        return dateToString(date, YY_MM_PATTERN);
    }

    public static String dateToHHmmStr(Date date) {
        return dateToString(date, HH_MM_PATTERN);
    }

    /**
     * 日期转字符串:yyyy-MM-dd
     * @param date
     * @return
     */
    public static String dateToShortString(Date date) {
        return dateToString(date, SHORT_PATTERN);
    }

    public static String dateToShortStr(Date date) {
        return dateToShortString(date);
    }

    public static String dateToShortStr() {
        return dateToShortString(new Date());
    }

    /**
     * 得到当前时间的字符串（供jsp页面初始化时间字段）
     * @return
     */
    public static String defaultDateStr() {
        return dateToStr(new Date());
    }

    public static String defaultDateStrNoSS() {
        return dateToStrNoSS(new Date());
    }

    /**
     * 增加n天后的日期
     *
     * @param date
     * @param n
     * @return
     */
    public static Date addDay(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);// 增加n天
        return calendar.getTime();
    }

    /** 增加工作时间（忽略非工作时间） */
    public static Date addWorkDay(Date date, int n) {
        Date newDay = date;
        for (int i = 0; i < n; i++) {
            do{
                newDay = addDay(newDay, 1);
            }while (isWordDay(newDay));
        }
        return newDay;
    }

    /** 是否是工作时间 */
    public static boolean isWordDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        return week == 1 || week == 7;
    }

    public static Date addMM(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, n);// 增加n分钟
        return calendar.getTime();
    }

    public static Date getYesterday() {
        return addDay(new Date(),-1);
    }

    /**
     * 增加n个月后的日期
     *
     * @param date
     * @param n
     * @return
     */
    public static Date addMonth(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, n);// 增加n个月
        return calendar.getTime();
    }

    /**
     * 获取当前月第一天
     *
     * @return
     */
    public static Date firstDayOfMonth() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return c.getTime();
    }

    /**
     * 获取当前年份
     * @return
     */
    public static int getYear(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR);
    }

    /**
     * 根据当前时间得到文件路径 yyyy_MM_dd_HH_mm_ss
     * @return
     */
    public static String getPath(){
        return dateToString(new Date(), PATH_PATTERN);
    }

    public static String getShortPath(){
        return dateToString(new Date(), SHORT_PATH_PATTERN);
    }

    /** yyMMddHHmmss-->36 radix,eg:gb3b1g1n */
    public static String shortTimeStamp(){
        String str = getShortPath();
        return StringUtil.longTo36Str(str);
    }

    /**
     * 根据当前时间得到文件路径 yyyy/MM/dd
     * @return
     */
    public static String getPathWithSlash(){
        return dateToString(new Date(), SLASH_PATTERN);
    }

    /** 得到两个时间的间隔天数 */
    public static double getDiffDays(Date start,Date end){
        long diff = end.getTime() - start.getTime();
        double days = diff / (1000 * 60 * 60 * 24.0);
        return Math.round(days * 10)/10.0;
    }

    /** 得到只有年月日的日期 */
    public static Date getYYMMDDDate(Date date){
        String string =  dateToString(date, SHORT_PATTERN);
        return stringToDate(string,SHORT_PATTERN);
    }

    /** 得到当天的开始时间 */
    public static Date getDayStart(Date date){
        String string =  dateToString(date, START_PATTERN);
        return stringToDate(string,START_PATTERN);
    }

    /** 得到当天的结束时间 */
    public static Date getDayEnd(Date date){
        String string =  dateToString(date, END_PATTERN);
        return stringToDate(string,NORMAL_PATTERN);// 注意：不能使用END_PATTERN，BUG？
    }

    public static Date getDayEnd(String dateStr){
        return getDayEnd(stringToDate(dateStr));
    }

    /** 得到最早的时间(作为时间判断的起点) */
    public static Date getOldestDate(){
        return oldestDate;
    }

    public static String getOldestDateStr(){
        return oldestDateStr;
    }

    public static String getNowStr(){
        return dateToStr();
    }

    /** 判断两天是否同一天 */
    public static Boolean isSameDay(Date date1,Date date2){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }
}
