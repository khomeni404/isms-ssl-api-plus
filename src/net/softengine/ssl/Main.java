package net.softengine.ssl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

public class Main {

    public static void main(String[] args) {
        String q = "random";
        try {
            URI uri = new URI(q);
            System.out.println("uri.toASCIIString() = " + uri.toASCIIString());
            String url = "http://example.com/query?q=" + URLEncoder.encode(q, "UTF-8");
            System.out.println(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
