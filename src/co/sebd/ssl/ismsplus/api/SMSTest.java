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

    public static void main(String[] args) {
        SMSClient client = new SMSClient("93f01bad-5f06-4b52-98be-d17b958458c1", "MY_SID");


        // Single SMS Example
        ReplyResult replyResult1 = client.sendSMS("01717659287", "Test aaa");
        List<SMSInfo> smsInfoList = replyResult1.getSmsInfoList();


        // Dynamic SMS Example
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("01717659287", "Dear Sir Your Due is 1298/- \nPlease Pay");
        messageMap.put("01945544306", "Dear Sir Your Due is 3200/- \nPlease Pay");
//        ReplyResult replyResult2 =  client.sendSMS(messageMap);
//        List<SMSInfo> smsInfoList2 = replyResult2.getSmsInfoList();

        // Dynamic SMS with Signature
//        replyResult2 =  client.sendSMS(messageMap,"Manager Accounts\nABC Company Ltd.");
//        smsInfoList2 = replyResult2.getSmsInfoList();



        // Bulk SMS Example
//        List<String> cellsBulk = Arrays.asList("01717659287", "01945544306");
//        ReplyResult replyResult3 = client.sendSMS(cellsBulk, "This is Common SMS for Everyone...");


    }


}
