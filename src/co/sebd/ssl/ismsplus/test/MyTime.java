package co.sebd.ssl.ismsplus.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Copyright &copy; Soft Engine
 * <p>
 * Original author: Ayatullah Khomeni<br/>
 * Date: 24/01/2016
 * Last modification by: ayat $
 * Last modification on 24/01/2016: 3:50 PM
 * Current revision: : 1.1.1.1
 * <p/>
 * Revision History:
 * ------------------
 */
public class MyTime {
   static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
    SimpleDateFormat stf2 = new SimpleDateFormat("hh.mm.ss aa");

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
       //Date yourUtcDate = sdf.parse("");
        System.out.println(sdf2.format(new Date()));

        System.out.println(sdf.format(new Date()));
        //DateTime dt = DateTime.Now;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss aa");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(f.format(new Date()));
    }
}
