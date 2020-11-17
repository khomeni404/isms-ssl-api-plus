package co.sebd.ssl.ismsplus.util;

import java.security.InvalidParameterException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Copyright &copy; Soft Engine
 * <p/>
 * Original author: Ayatullah Khomeni<br/>
 * Date: 26/01/2016
 * Last modification by: ayat $
 * Last modification on 26/01/2016: 12:44 PM
 * Current revision: : 1.1.1.1
 * <p/>
 * Revision History:
 * ------------------
 */

public class DateUtil {
    public static final char SEPARATOR_SLASH = '/';
    public static final char SEPARATOR_DASH = '-';
    public static final char SEPARATOR_DOT = '.';
    public static final char SEPARATOR_COMMA = ',';
    public static final char SEPARATOR_NONE = '\u0000';
    /**
     * @deprecated
     */
    public static final String DATE_FORMAT_DD_MM_YYYY_BACKSLASH = "dd/MM/yyyy";
    /**
     * @deprecated
     */
    public static final String DATE_FORMAT_YYYY_MM_DD_BACKSLASH = "yyyy/MM/dd";
    public static final String DATE_FORMAT_DD_MM_YYYY_SLASH = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DD_MM_YYYY_DOT = "dd.MM.yyyy";
    public static final String DATE_FORMAT_DD_MM_YYYY_COMMA = "dd,MM,yyyy";
    public static final String DATE_FORMAT_DD_MM_YYYY_DASH = "dd-MM-yyyy";
    public static final String DATE_FORMAT_YYYY_MM_DD_SLASH = "yyyy/MM/dd";
    public static final String DATE_FORMAT_YYYY_MM_DD_DOT = "yyyy.MM.dd";
    public static final String DATE_FORMAT_YYYY_MM_DD_DASH = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YY_MM_DD_NO_SEPARATOR = "yyMMdd";
    public static final String DATE_FORMAT_YYYY_MM_DD_NO_SEPARATOR = "yyyyMMdd";
    public static final String DATE_FORMAT_YY_MM_DD_HH_MI_NO_SEPARATOR = "yyMMddHHmm";
    public static final String DATE_FORMAT_YYYY_MM_DD_HH_MI_NO_SEPARATOR = "yyyyMMddHHmm";
    public static final String DATE_FORMAT_DD_MON_YYYY_DASH = "dd-MMM-yyyy";
    public static final String DATE_FORMAT_DD_MM_YYYY_HH_MI_AMPM_SLASH = "dd/MM/yyyy hh:mm a";
    public static final String DATE_FORMAT_MM_DD_YY_NO_SEPARATOR = "MMddyy";
    public static final String DATE_FORMAT_HH_MI_NO_SEPARATOR = "HHmm";
    public static final String DATE_FORMAT_HH_MI_SS_MS_NO_SEPARATOR = "HHmmssSSS";

    public DateUtil() {
    }

    public static String getCurrentDate() {
        Date date = getSystemDate();
        return toString(date);
    }

    public static String getCurrentDate(String seperator) {
        Date date = getSystemDate();
        return toString(date, seperator);
    }

    public static Date getSystemDate() {
        return getSystemDate(true);
    }

    public static Date getSystemDate(boolean withTimeStamp) {
        Date myDate = new Date(System.currentTimeMillis());
        if (!withTimeStamp) {
            Calendar cal = Calendar.getInstance();
            cal.set(10, 0);
            cal.set(12, 0);
            cal.set(13, 0);
            cal.set(14, 0);
            cal.set(9, 0);
            myDate = cal.getTime();
        }

        return myDate;
    }

    public static int getDay(Date date) {
        byte day = -1;
        Calendar calendar = null;
        if (date == null) {
            return day;
        } else {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            int day1 = calendar.get(5);
            return day1;
        }
    }

    public static String getDayName(int day) {
        String dayName = "";
        switch (day) {
            case 1:
                dayName = "SUN";
                break;
            case 2:
                dayName = "MON";
                break;
            case 3:
                dayName = "TUE";
                break;
            case 4:
                dayName = "WED";
                break;
            case 5:
                dayName = "THU";
                break;
            case 6:
                dayName = "FRI";
                break;
            case 7:
                dayName = "SAT";
                break;
            default:
                dayName = "unknown";
        }

        return dayName;
    }

    public static String getMonthName(Date date) {
        int month = getMonth(date);
        return getMonthName(month);
    }

    public static String getMonthName(int month) {
        String monthName = "";
        switch (month) {
            case 0:
                monthName = "JAN";
                break;
            case 1:
                monthName = "FEB";
                break;
            case 2:
                monthName = "MAR";
                break;
            case 3:
                monthName = "APR";
                break;
            case 4:
                monthName = "MAY";
                break;
            case 5:
                monthName = "JUN";
                break;
            case 6:
                monthName = "JUL";
                break;
            case 7:
                monthName = "AUG";
                break;
            case 8:
                monthName = "SEP";
                break;
            case 9:
                monthName = "OCT";
                break;
            case 10:
                monthName = "NOV";
                break;
            case 11:
                monthName = "DEC";
                break;
            default:
                monthName = "unknown";
        }

        return monthName;
    }

    public static int getYear(Date date) {
        byte year = -1;
        Calendar calendar = null;
        if (date == null) {
            return year;
        } else {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            int year1 = calendar.get(1);
            return year1;
        }
    }

    public static String toString(Date date) {
        return toString(date, "/");
    }

    public static String toString(Date date, String seperator) {
        String myDate = null;
        Calendar calendar = null;
        if (date == null) {
            return myDate;
        } else {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            int dd = calendar.get(5);
            int mm = calendar.get(2) + 1;
            int yyyy = calendar.get(1);
            if (dd != 0 && mm != 0 && yyyy != 0) {
                String day = Integer.toString(dd);
                String month = Integer.toString(mm);
                String year = Integer.toString(yyyy);
                if (day.length() == 1) {
                    day = "0" + day;
                }

                if (month.length() == 1) {
                    month = "0" + month;
                }

                myDate = day + seperator + month + seperator + year;
            }

            return myDate;
        }
    }

    public static String toStringWithFormat(Date date, String destinationFormat) {
        String fomattedDate = null;
        SimpleDateFormat sdfDestination = new SimpleDateFormat(destinationFormat);
        fomattedDate = sdfDestination.format(date);
        return fomattedDate;
    }

    public static Date toDateWithFormat(String dateString, String dateFormat) throws ParseException {
        Date fomattedDate = null;
        SimpleDateFormat sdfDestination = new SimpleDateFormat(dateFormat);
        fomattedDate = sdfDestination.parse(dateString);
        return fomattedDate;
    }

    public static Date toDate(String dateString) throws ParseException {
        Date fomattedDate = null;
        String dateFormat;
        byte separator;
        if (dateString.indexOf(44) != -1) {
            dateFormat = "dd,MM,yyyy";
            separator = 44;
        } else if (dateString.indexOf(45) != -1) {
            dateFormat = "dd-MM-yyyy";
            separator = 45;
        } else if (dateString.indexOf(46) != -1) {
            dateFormat = "dd.MM.yyyy";
            separator = 46;
        } else {
            if (dateString.indexOf(47) == -1) {
                return fomattedDate;
            }

            dateFormat = "dd/MM/yyyy";
            separator = 47;
        }

        if (dateString.length() == 8) {
            Calendar sdfDestination = Calendar.getInstance();
            int yy = sdfDestination.get(1);
            int y = dateString.lastIndexOf(separator);
            dateString = dateString.substring(0, y + 1) + String.valueOf(yy).substring(0, 2) + dateString.substring(y + 1);
        }

        SimpleDateFormat sdfDestination1 = new SimpleDateFormat(dateFormat);
        fomattedDate = sdfDestination1.parse(dateString);
        return fomattedDate;
    }

    public static boolean isLeapYear(int year) {
        boolean rs = false;
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            rs = true;
        }

        return rs;
    }

    public static boolean isLeapYear(Date date) {
        boolean rs = false;
        boolean year = false;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year1 = c.get(1);
        if (year1 % 4 == 0 && (year1 % 100 != 0 || year1 % 400 == 0)) {
            rs = true;
        }

        return rs;
    }

    public static int getMonth(Date date) {
        if (date == null) {
            return -1;
        } else {
            Calendar calendar = null;
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(2);
        }
    }

    public static boolean isBefore(Date mainDate, Date compareDate) {
        boolean result = false;
        Calendar gc1 = null;
        Calendar gc2 = null;
        if (mainDate != null && compareDate != null) {
            gc1 = Calendar.getInstance();
            gc1.setTime(mainDate);
            gc2 = Calendar.getInstance();
            gc2.setTime(compareDate);
            return gc1.before(gc2);
        } else {
            return result;
        }
    }

    public static boolean isBeforeWithoutTime(Date mainDate, Date compareDate) {
        boolean result = false;
        Calendar gc1 = null;
        Calendar gc2 = null;
        if (mainDate != null && compareDate != null) {
            gc1 = Calendar.getInstance();
            gc1.setTime(mainDate);
            gc2 = Calendar.getInstance();
            gc2.setTime(compareDate);
            if (gc1.get(1) < gc2.get(1)) {
                result = true;
            } else if (gc1.get(1) == gc2.get(1) && gc1.get(6) < gc2.get(6)) {
                result = true;
            }

            return result;
        } else {
            return result;
        }
    }

    public static boolean isEqualDate(Date date, Date anotherDate) {
        boolean result = false;
        Calendar gc1 = null;
        Calendar gc2 = null;
        if (date != null && anotherDate != null) {
            gc1 = Calendar.getInstance();
            gc1.setTime(date);
            gc2 = Calendar.getInstance();
            gc2.setTime(anotherDate);
            result = gc1.get(1) == gc2.get(1) && gc1.get(2) == gc2.get(2) && gc1.get(5) == gc2.get(5);
            return result;
        } else {
            return result;
        }
    }

    public static long dateDiffInDay(Date firstDate, Date secondDate) {
        long diff = 0L;
        Calendar c1 = Calendar.getInstance();
        c1.setTime(firstDate);
        c1.set(10, 0);
        c1.set(12, 0);
        c1.set(13, 0);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(secondDate);
        c2.set(10, 0);
        c2.set(12, 0);
        c2.set(13, 0);
        long f1 = c1.getTimeInMillis();
        long f2 = c2.getTimeInMillis();
        long div = 86400000L;
        if (f1 > f2) {
            diff = (f1 - f2) / div;
        } else {
            diff = (f2 - f1) / div;
        }

        return diff;
    }

    public static String toString(Time time, int length, String seperator) {
        String myTime = null;
        Calendar calendar = null;
        if (time == null) {
            return myTime;
        } else {
            calendar = Calendar.getInstance();
            calendar.setTime(time);
            int hh = calendar.get(11);
            int mi = calendar.get(12);
            int ss = calendar.get(13);
            int mss = calendar.get(14);
            if (hh >= 0 && mi >= 0 && ss >= 0 && mss > 0) {
                String hour = Integer.toString(hh);
                String mnt = Integer.toString(mi);
                String second = Integer.toString(ss);
                String miliSecond = Integer.toString(mss);
                if (hour.length() == 1) {
                    hour = "0" + hour;
                }

                if (mnt.length() == 1) {
                    mnt = "0" + mnt;
                }

                if (second.length() == 1) {
                    second = "0" + second;
                }

                if (second.length() == 1) {
                    miliSecond = "0" + miliSecond;
                }

                if (length == 9) {
                    myTime = hour + seperator + mnt + seperator + second + seperator + miliSecond;
                } else if (length == 6) {
                    myTime = hour + seperator + mnt + seperator + second;
                } else {
                    if (length != 4) {
                        throw new InvalidParameterException("Incorrect Length");
                    }

                    myTime = hour + seperator + mnt;
                }
            }

            return myTime;
        }
    }

    public static Date getDate(int day, int month, int year) {
        Date date = null;
        GregorianCalendar c = new GregorianCalendar(year, month - 1, day);
        date = c.getTime();
        return date;
    }

    public static Date getDate(int day, int month, int year, int hr, int min) {
        Date date = null;
        GregorianCalendar c = new GregorianCalendar(year, month - 1, day, hr, min);
        date = c.getTime();
        return date;
    }

    public static String toOracleDateString(Date date) {
        return toStringWithFormat(date, "dd-MMM-yyyy");
    }

    public static String convertDateFormat(String dateString, String sourceFormat, String destinationFormat) {
        String fomattedDate = null;

        try {
            SimpleDateFormat e = new SimpleDateFormat(sourceFormat);
            Date date = e.parse(dateString);
            SimpleDateFormat sdfDestination = new SimpleDateFormat(destinationFormat);
            fomattedDate = sdfDestination.format(date);
            return fomattedDate;
        } catch (Exception var7) {
            var7.printStackTrace();
            return fomattedDate;
        }
    }
}

