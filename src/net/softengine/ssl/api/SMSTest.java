package net.softengine.ssl.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright &copy; Soft Engine
 * <p/>
 * Original author: Ayatullah Khomeni<br/>
 * Date: 24/01/2016
 * Last modification by: ayat $
 * Last modification on 24/01/2016: 12:06 PM
 * Current revision: : 1.1.1.1
 * <p/>
 * Revision History:
 * ------------------
 */
public class SMSTest {

    public static final String API_TOKEN = "QRFBRAND-41a6f43c-31f9-4965-a84a-17d77facff1d";
    public static final String SID = "QRFBRAND";

    public static void main(String[] args) {
        List<String> cells = new ArrayList<String>(
                //Arrays.asList("8801944005445", "8801945544306", "8801939109729")
                Arrays.asList("01717659287")
        );
        SMSTest.sendSMS(RequestedMethod.POST, cells, "This is a message NO 88");
    }

    public static void sendSMS(RequestedMethod method, List<String> cells, String text) {
        SMSClient client = new SMSClient(API_TOKEN, SID);
        ReplyResult result = client.sendSMS(method, cells, text);
        System.out.println(result.getLogin());
        System.out.println(result.getMessage());
        for (SMSInfo info : result.getSmsInfoList()) {
            System.out.print(info.getMsiSdn()+",\t");
            System.out.print(info.getSmsText() + ",\t");
            System.out.println(info.getMsiSdnStatus());
            System.out.println("=========================");
        }

    }

}
