package co.sebd.ssl.ismsplus.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import co.sebd.ssl.ismsplus.util.IDGenerator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Copyright &copy; Soft Engine
 * <p/>
 * Original author: Ayatullah Khomeni<br/>
 * Date: 24/01/2016
 * Last modification by: ayat $
 * Last modification on 24/01/2016: 2:34 PM
 * Current revision: : 1.1.1.1
 * <p/>
 * Revision History:
 * ------------------
 */

public class SMSClient {

    private static final String ROOT_URL = "https://smsplus.sslwireless.com/api/v3/send-sms";
    private static final int BUNDLE_LIMIT = 100;
    private static final String API_STATUS_SUCCESS = "SUCCESS";
    private static final String API_STATUS_FAILED = "FAILED";
    private String apiToken;
    private String sid;

    public SMSClient(String apiToken, String sid) {
        this.apiToken = apiToken;
        this.sid = sid;
    }


    // Single SMS
    public synchronized ReplyResult sendSMS(String cell, String text) {
        return sendSMS(Arrays.asList(cell), text);
    }

    // Bulk SMS
    public synchronized ReplyResult sendSMS(List<String> cells, String text) {
        ReplyResult result = new ReplyResult();
        try {
            URL bulkURL = new URL(SMSClient.ROOT_URL + "/bulk");
            URL singleURL = new URL(SMSClient.ROOT_URL);
            IDGenerator csmSID = new IDGenerator();

            List<SMSInfo> smsInfoList = new ArrayList<SMSInfo>();
            int subListCounter = 1;
            int start, end;
            int total = cells.size();
            for (int i = 0; i < total; i += BUNDLE_LIMIT) {
                start = i;
                end = i + BUNDLE_LIMIT;
                List<String> subList = cells.subList(start, end > total ? total : end);
                System.out.println("subList = " + subList);

                if (subList.get(0) != null) {
                    ReplyResult replyResult;
                    JSONObject parent = new JSONObject();
                    parent.put("api_token", apiToken);
                    parent.put("sid", sid);
                    parent.put("sms", text);

                    if (subList.size() > 1) { // Bulk SMS
                        Set<String> cellSet = new HashSet<>(subList);
                        JSONArray cellArray = new JSONArray(cellSet);
                        parent.put("msisdn", cellArray);
                        parent.put("batch_csms_id", csmSID.generate());
                        replyResult = sendPost(bulkURL, parent);
                    } else { // Single SMS
                        parent.put("msisdn", subList.get(0));
                        parent.put("csms_id", csmSID.generate());
                        replyResult = sendPost(singleURL, parent);
                    }

                    if (subListCounter <= 1) {
                        result = replyResult;
                        smsInfoList.addAll(result.getSmsInfoList());
                        result.setMessage(result.getMessage());
                    } else {
                        smsInfoList.addAll(replyResult.getSmsInfoList());
                        result.setMessage(replyResult.getMessage());
                    }
                    subListCounter++;
                }
            }
            result.setSuccess(true);
            result.setSmsInfoList(smsInfoList);

        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Unable to connect" + e.getMessage() + "\nPlease check your internet connection.");
        }
        return result;
    }

    // Dynamic SMS : Without Signature
    public synchronized ReplyResult sendSMS(Map<String, String> messageMap) {
        return sendSMS(messageMap, null);
    }

    // Dynamic SMS : With Signature
    public synchronized ReplyResult sendSMS(Map<String, String> messageMap, String signature) {
        ReplyResult result = new ReplyResult();
        try {
            URL url = new URL(SMSClient.ROOT_URL + "/dynamic");
            JSONObject parent = new JSONObject();
            IDGenerator csmSID = new IDGenerator();
            List<JSONObject> cells = new ArrayList<>();
            for (Map.Entry<String, String> entry : messageMap.entrySet()) {
                JSONObject msg = new JSONObject();
                msg.put("msisdn", entry.getKey());
                msg.put("text", entry.getValue() + (signature == null ? "" : ("\n"+signature)));
                msg.put("csms_id", csmSID.generate());
                cells.add(msg);
            }


            List<SMSInfo> smsInfoList = new ArrayList<>();
            int subListCounter = 1;

            int start, end;
            int total = cells.size();
            for (int i = 0; i < total; i += BUNDLE_LIMIT) {
                start = i;
                end = i + BUNDLE_LIMIT;
                List<JSONObject> subList = cells.subList(start, end > total ? total : end);
                System.out.println("subList = " + subList);

                JSONArray msisdn = new JSONArray(subList);
                parent.put("api_token", apiToken);
                parent.put("sid", sid);
                parent.put("sms", msisdn);

                ReplyResult replyResult = sendPost(url, parent);

                if (subListCounter <= 1) {
                    result = replyResult;
                    smsInfoList.addAll(result.getSmsInfoList());
                } else {
                    smsInfoList.addAll(replyResult.getSmsInfoList());
                }
                subListCounter++;
            }
            result.setSuccess(true);
            result.setMessage("Request sent successfully");
            result.setSmsInfoList(smsInfoList);
            return result;
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Unable to connect" + e.getMessage() + "\nPlease check your internet connection.");
            return result;
        }
    }



    // Posting Request
    private synchronized static ReplyResult sendPost(URL url, JSONObject parent) throws Exception {

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod(RequestedMethod.POST.name());

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), StandardCharsets.UTF_8);
        wr.write(parent.toString());
        wr.flush();
        wr.close();

        //display what returns the POST request
        ReplyResult replyResult = new ReplyResult();
        StringBuilder sb = new StringBuilder();
        int HttpResult = con.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8)
            );
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            br.close();

            JSONObject json = new JSONObject(sb.toString());
            String status = (String) json.get("status");
            Integer status_code = (Integer) json.get("status_code");

            List<SMSInfo> smsInfoList = new ArrayList<>();
            try {
                JSONArray jsonArray = (JSONArray) json.get("smsinfo");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jo = (JSONObject) jsonArray.get(i);
                    smsInfoList.add(SMSInfo.toSMSInfo(jo));
                }
                replyResult.setSmsInfoList(smsInfoList);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            replyResult.setSuccess(API_STATUS_SUCCESS.equals(status));
            replyResult.setMessage("Status Code: " + StatusCode.API_STATUS_CODE_MAP.get(status_code));

            System.out.println("" + sb.toString());
        } else {
            replyResult.setSuccess(false);
            replyResult.setMessage("Response Code: " + HttpResult);
        }

        return replyResult;
    }


}
