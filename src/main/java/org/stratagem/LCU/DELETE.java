package org.stratagem.LCU;

import java.net.HttpURLConnection;
import java.net.URL;

public class DELETE {
    public static void delete(String protocol, String url, String port, String endpoint, String auth) {
        try {
            String fullUrl = protocol + "://" + url + ":" + port + endpoint;
            URL obj = java.net.URI.create(fullUrl).toURL();
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Authorization", auth);
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            if ("https".equalsIgnoreCase(protocol) && con instanceof javax.net.ssl.HttpsURLConnection) {
                ((javax.net.ssl.HttpsURLConnection) con).setSSLSocketFactory(
                        (javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory.getDefault()
                );
            }

            con.getResponseCode();
        } catch (Exception ignored) {
        }
    }
}