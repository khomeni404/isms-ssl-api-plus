package co.sebd.ssl.ismsplus.api;

import java.util.*;

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

    public static final String API_TOKEN = "QRFBRAND-31f9-4965-a84a-17d77facff1d-41a6f43c";
    public static final String SID = "QRFBRAND";


    public static void main(String[] args) {
        System.out.println(RequestedMethod.GET.name());
    }





    public static void sendSMS(RequestedMethod method, List<String> cells, String text) {
        SMSClient client = new SMSClient(API_TOKEN, SID);
        ReplyResult result = client.sendSMS(cells, text);
        System.out.println(result.getLogin());
        System.out.println(result.getMessage());
        for (SMSInfo info : result.getSmsInfoList()) {
            System.out.print(info.getMsiSdn() + ",\t");
            System.out.print(info.getSmsText() + ",\t");
            System.out.println(info.getMsiSdnStatus());
            System.out.println("=========================");
        }

    }

}
