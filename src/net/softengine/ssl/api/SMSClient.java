package net.softengine.ssl.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.softengine.ssl.util.IDGenerator;
import net.softengine.ssl.util.XmlUtil;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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

    public static void main(String[] args) throws Exception {

        SMSClient http = new SMSClient();

        System.out.println("Testing 1 - Send Http GET request");
        // http.sendGet();

        System.out.println("\nTesting 2 - Send Http POST request");
        //http.sendPost();

    }


    private static final String USER_AGENT = "Chrome/41.0.2228.0"; // "Mozilla/5.0";
//    private static final String URL = "http://sms.sslwireless.com/pushapi/dynamic/server.php";
    private static final String URL = "http://172.75.75.2/pushapi/dynamic/server.php";
    private static final int BUNDLE_LIMIT = 2;

    public static String username;
    public static String password;
    public static String sid;

    public SMSClient() {
    }

    public SMSClient(String username, String password, String sid) {
        SMSClient.username = username;
        SMSClient.password = password;
        SMSClient.sid = sid;
    }




    public ReplyResult sendSMS(RequestedMethod method, List<String> cells, String text) {
        ReplyResult result = new ReplyResult();
        List<SMSInfo> smsInfoList = new ArrayList<SMSInfo>();
        int subListCounter = 1;
        try {
            if (method == RequestedMethod.POST) {
                int start, end;
                int total = cells.size();
                for (int i = 0; i < total; i += BUNDLE_LIMIT) {
                    start = i;
                    end = i + BUNDLE_LIMIT;
                    List<String> subList = cells.subList(start, end > total ? total : end);
                    System.out.println("subList = " + subList);
                    ReplyResult replyResult = sendPost(subList, text);

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
            } else if (method == RequestedMethod.GET) {
                result.setSuccess(false);
                result.setMessage("GET Method not allowed here.");
                return result;
            } else {
                result.setSuccess(false);
                result.setMessage("No POST/GET method found !");
                return result;
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Unable to connect"+ e.getMessage()+"\nPlease check your internet connection.");
            return result;
        }
    }


    // HTTP GET request
    private static void sendGet() throws Exception {

        String url = "http://sms.sslwireless.com/pushapi/dynamic/server.php" +
                "?user=mak" +
                "&pass=mak123" +
                "&sms[0][0]=8801717659287" +
                "&sms[0][1]=HelloSSL" +
                "&sms[0][2]=12345678954" +
                "&sid=mak";

        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);

        // add request header
        request.addHeader("User-Agent", USER_AGENT);

        HttpResponse response = client.execute(request);

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        XmlUtil.readStringXML(result.toString());
        //System.out.println(result.toString());

    }

    // HTTP POST request
    private static ReplyResult sendPost(List<String> cells, String text) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);
        post.setHeader("User-Agent", USER_AGENT); // add header

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("user", username));
        urlParameters.add(new BasicNameValuePair("pass", password));
        IDGenerator csmSID = new IDGenerator();

        int index = 0;
        for (String cell : cells) {
            String sms = "sms[" + index + "]";
            urlParameters.add(new BasicNameValuePair(sms + "[0]", cell));
            urlParameters.add(new BasicNameValuePair(sms + "[1]", text));
            urlParameters.add(new BasicNameValuePair(sms + "[2]", csmSID.generate()));
            index++;
        }
        urlParameters.add(new BasicNameValuePair("sid", "mak"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("\nSending 'POST' request to URL : " + URL);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : " +
                response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer bufferResult = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            bufferResult.append(line);
        }
        //XmlUtil.readStringXML(bufferResult.toString());
        //System.out.println(result.toString());
        /*ReplyResult replyResult;
        if (subListCounter <= 1) {
            result = XmlUtil.generateActionResult(bufferResult.toString());
            smsInfoList.addAll(result.getSmsInfoList());
        } else {
            replyResult = XmlUtil.generateActionResult(bufferResult.toString());
            smsInfoList.addAll(replyResult.getSmsInfoList());
        }
        subListCounter++;*/

        return XmlUtil.generateActionResult(bufferResult.toString());
    }

}
