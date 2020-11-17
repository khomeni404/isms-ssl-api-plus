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

    public static final String API_TOKEN = "35498629-2dc2-4ef8-920a-8aecace2a35f";
    public static final String SID = "ISCOOLBELLBRAND";
    public static final String SID_SE = "SOFTENGINEBRAND";


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
