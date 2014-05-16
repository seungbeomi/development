/**
 * Copyright (c) 2013 BS Information System
 */
package kr.co.bsisys.fw.util;

import java.sql.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.util.Assert;

/**
 * <pre>
 * 시  스  템 : 
 * 프로그램ID : DateUtil.java
 * 프로그램명 : 
 * 설      명 : 
 * 작  성  자 : BS정보시스템/손승범
 * 작  성  일 : 2013. 11. 25.
 * </pre>
 */
public class DateUtil {
  
  public static final String FORMAT_YMD = "yyyyMMdd";
  public static final String FORMAT_YM = "yyyyMM";
  public static final String FORMAT_YMD_WITH_DOT = "yyyy.MM.dd";
  
  // for jodatime
  public static final String FORMAT_DATETIME_YMD = "yMd";
  public static final String FORMAT_DATETIME_YMD_WITH_DOT = "y.M.d";
  
  public static String before(Long duration) {
    return new DateTime().plus(duration).toString(FORMAT_YMD);
  }
  
  public static String before(Long duration, String format) {
    return new DateTime().plus(duration).toString(format);
  }
  
  public static String minus(Long duration) {
    return new DateTime().minus(duration).toString(FORMAT_YMD);
  }
  
  public static String minus(Long duration, String style) {
    return new DateTime().minus(duration).toString(style);
  }
  
  public static String minusMonth(int month) {
    return new DateTime().minusMonths(month).toString(FORMAT_YMD);
  }
  
  public static String minusWeek(int week) {
    return new DateTime().minusWeeks(week).toString(FORMAT_YMD);
  }
  
  public static Date getUpdtDttm(String updtDttm) {
    Assert.notNull(updtDttm);
    String temp = updtDttm.substring(0, updtDttm.length() - 2);
    DateTime upDt = DateTimeFormat.forPattern("y-M-d H:m:s").parseDateTime(temp);
    return new Date(upDt.getMillis());
  }
  
  public static String getLastDayOfMonth(String today) {
    return getLastDayOfMonth(today, FORMAT_DATETIME_YMD_WITH_DOT, FORMAT_YMD_WITH_DOT);
  }
  
  public static String getLastDayOfMonth(String today, String datetimeStyle, String dateStyle) {
    DateTime date = DateTimeFormat.forPattern(datetimeStyle).parseDateTime(today).dayOfMonth()
        .withMaximumValue();
    return date.toString(dateStyle);
  }
  
  public static boolean isAfter(String date1, String date2) {
    return isAfter(date1, date2, FORMAT_YMD);
  }
  
  public static boolean isAfter(String date1, String date2, String datetimeStyle) {
    DateTime from = DateTimeFormat.forPattern(datetimeStyle).parseDateTime(date1);
    DateTime to = DateTimeFormat.forPattern(datetimeStyle).parseDateTime(date2);
    return from.isAfter(to);
  }
  
  public static boolean isBefore(String date1, String date2) {
    return !isAfter(date1, date2);
  }
  
  public static boolean isBefore(String date1, String date2, String datetimeStyle) {
    return !isAfter(date1, date2, datetimeStyle);
  }
  
  //----------------------------------
  /** 예: 2013-03-16T21:15:44.688+09:00 */
  public static DateTime getSystemTime() {
    return new DateTime();
  }
  
  /** 예: 2013년 3월 16일 토요일 */
  public static String getFullDate() {
    return new DateTime().toString(DateTimeFormat.fullDate());
  }
  
  /** 예: 2013년 3월 16일 토요일 오후 9시 15분 44초 KST */
  public static String getFullDateTime() {
    return new DateTime().toString(DateTimeFormat.fullDateTime());
  }
  
  /** 예: 2013. 3. 16 오후 9:18:03 */
  public static String getMediumDateTime() {
    return new DateTime().toString(DateTimeFormat.mediumDateTime());
  }
  
  /** 예: 13. 3. 16 오후 9:19 */
  public static String getShortDateTime() {
    return new DateTime().toString(DateTimeFormat.shortDateTime());
  }
  
  /** 예: 20130316 */
  public static String getBasicDate() {
    return new DateTime().toString(ISODateTimeFormat.basicDate());
  }
  
  /** 예: 2013-03-16 */
  public static String getDate() {
    return new DateTime().toString(ISODateTimeFormat.date());
  }
  
  /** 포맷에 따른 날짜 반환 */
  public static String getDate(String format) {
    return new DateTime().toString(format);
  }
  
  /** 예: 2013-03-16 */
  public static String getTimeStamp() {
    return new DateTime().toString("YYYYMMDDHHmmSS");
  }
  
  public static DateTime getDate(int year, int monthOfYear, int dayOfMonth) {
    return new DateTime().withDate(year, monthOfYear, dayOfMonth);
  }
  
  /** 예: 2013 */
  public static int getYear() {
    return new DateTime().getYear();
  }
  
  /** 예: 3 */
  public static int getMonthOfYear() {
    return new DateTime().getMonthOfYear();
  }
  
  /** 예: 16 */
  public static int getDayOfMonth() {
    return new DateTime().getDayOfMonth();
  }
  
  /** 예: 6 (월=1, 화=2, 수=3, 목=4, 금=5, 토=6, 일=7) */
  public static int getDayOfWeek() {
    return new DateTime().getDayOfWeek();
  }
  
  /** 예: 월,화,수,목,금,토,일 */
  public static String getDayOfWeekKR() {
    String result = "";
    
    switch (getDayOfWeek()) {
    case 1:
      result = "월";
      break;
    case 2:
      result = "화";
      break;
    case 3:
      result = "수";
      break;
    case 4:
      result = "목";
      break;
    case 5:
      result = "금";
      break;
    case 6:
      result = "토";
      break;
    case 7:
      result = "일";
      break;
    }
    
    return result;
  }
  
  /** 예: 1363437349705 */
  public static long getMillis() {
    return new DateTime().getMillis();
  }
  
  /** 예: 1363437349705 */
  public static DateTime plusDays(int days) {
    return new DateTime().plusDays(days);
  }
  
  /** dt1값이 현재보다 미래인지 확인 */
  public static boolean isAfter(DateTime dt1) {
    return dt1.isAfterNow();
  }
  
  /** dt1값이 dt2보다 미래인지 확인 */
  public static boolean isAfter(DateTime dt1, DateTime dt2) {
    return dt1.isAfter(dt2);
  }
  
  /** dt1값이 현재보다 과거인지 확인 */
  public static boolean isBefore(DateTime dt1) {
    return dt1.isBeforeNow();
  }
  
  /** dt1값이 dt2보다 과거인지 확인 */
  public static boolean isBefore(DateTime dt1, DateTime dt2) {
    return dt1.isBefore(dt2);
  }
  
  /** dt1값과 dt2값이 동일한지 확인 */
  public static boolean isEqual(DateTime dt1, DateTime dt2) {
    return dt1.isEqual(dt2);
  }
  
}
