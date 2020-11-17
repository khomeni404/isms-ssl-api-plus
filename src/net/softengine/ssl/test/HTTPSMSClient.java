package net.softengine.ssl.test;

import net.softengine.ssl.util.XmlUtil;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
public class HTTPSMSClient {
    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        //String url = "https://selfsolve.apple.com/wcResults.do";
        String url = "http://114.134.95.236/rmsapiToken";
        String url2 = "http://114.134.95.236/rmsapiToken?grant_type=password&username=bracsaajan@msn.com&Password=Admin@123";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("grant_type", "password"));
        urlParameters.add(new BasicNameValuePair("username", "bracsaajan@msn.com"));
        urlParameters.add(new BasicNameValuePair("password", "Admin@123"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("Sending 'POST' request..... "+post.getURI());
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());
        //System.out.println(result.toString());


    }

    // HTTP GET request
    private void sendGet() throws Exception {

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

        System.out.println("\nSending 'GET' request to ROOT_URL : " + url);
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
    private void sendPost() throws Exception {

        //String url = "https://selfsolve.apple.com/wcResults.do";
        String url = "http://sms.sslwireless.com/pushapi/dynamic/server.php";

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("user", "mak"));
        urlParameters.add(new BasicNameValuePair("pass", "mak123"));
        urlParameters.add(new BasicNameValuePair("sms[0][0]", "8801717659287"));
        urlParameters.add(new BasicNameValuePair("sms[0][1]", "HelloSSL"));
        urlParameters.add(new BasicNameValuePair("sms[0][2]", "12345678954"));
        urlParameters.add(new BasicNameValuePair("sid", "mak"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("\nSending 'POST' request to ROOT_URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
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

}
