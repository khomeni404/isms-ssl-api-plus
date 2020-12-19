# isms-ssl-api-plus


This API is to send SMS by SSL Wireless of Bangladesh
https://ismsplus.sslwireless.com/



        SMSClient client = new SMSClient("API_Hash_from_ssl_wireless", "YOUR_SID");


        // Single SMS Example
        ReplyResult replyResult1 = client.sendSMS("01717659287", "Test aaa");
        List<SMSInfo> smsInfoList = replyResult1.getSmsInfoList();


        // Dynamic SMS Example
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("01717659287", "Dear Sir Your Due is 1298/- \nPlease Pay");
        messageMap.put("01945544306", "Dear Sir Your Due is 3200/- \nPlease Pay");
        ReplyResult replyResult2 =  client.sendSMS(messageMap);
        List<SMSInfo> smsInfoList2 = replyResult2.getSmsInfoList();

        // Dynamic SMS with Signature
        replyResult2 =  client.sendSMS(messageMap,"Manager Accounts\nABC Company Ltd.");
        smsInfoList2 = replyResult2.getSmsInfoList();



        // Bulk SMS Example
        List<String> cellsBulk = Arrays.asList("01717659287", "01945544306");
        ReplyResult replyResult3 = client.sendSMS(cellsBulk, "This is Common SMS for Everyone...");
