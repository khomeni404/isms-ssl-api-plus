package co.sebd.ssl.ismsplus.util;

import java.text.ParseException;
import java.util.Date;

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

public class IDGenerator {
    private static int counter = (short) 0;

    public IDGenerator() {
    }

    protected int getCount() {
        if (counter < 0) {
            counter = 0;
        }
        if (counter > 9999) {
            counter = 0;
        }
        return counter++;
    }

    public synchronized String generate() {
        String dateNow = DateUtil.getCurrentDate();
        Date curDate = null;
        try {
            curDate = DateUtil.toDate(dateNow);
        } catch (ParseException e) {
            return null;
        }
        String id = null;
        String randomTime = Long.toString(System.nanoTime());
        if (randomTime.length() >= 13) {
            int length = randomTime.length();
            randomTime = randomTime.substring((length % 8), length);
        }
        String counterId = Integer.toString(getCount());
        counterId = Utility.leftPad(counterId, 4, '0');
        randomTime = Utility.rightPad(randomTime, 8, '0');
        randomTime = randomTime + counterId;
        if (randomTime.length() < 12) {
            randomTime = Utility.rightPad(randomTime, 12, '0');
        }
        int year = DateUtil.getYear(curDate);
        String yearStr = Utility.leftPad(Integer.toString(year), 4, '0');
        int month = DateUtil.getMonth(curDate);
        String monthStr = Utility.leftPad(Integer.toString(month), 2, '0');
        int day = DateUtil.getDay(curDate);
        String dayStr = Utility.leftPad(Integer.toString(day), 2, '0');
        String curDateNow = yearStr + monthStr + dayStr;
        if (curDateNow.length() < 8) {
            curDateNow = Utility.leftPad(curDateNow, 8, '0');
        }
        id = curDateNow + randomTime;
        return id;
    }
}