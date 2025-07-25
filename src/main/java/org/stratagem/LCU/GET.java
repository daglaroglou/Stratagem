package org.stratagem.LCU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GET {
    public static String get(String protocol, String url, String port, String endpoint, String auth) {
        try {
            String fullUrl = protocol + "://" + url + ":" + port + endpoint;
            URL obj = java.net.URI.create(fullUrl).toURL();
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", auth);
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            if ("https".equalsIgnoreCase(protocol) && con instanceof javax.net.ssl.HttpsURLConnection) {
                ((javax.net.ssl.HttpsURLConnection) con).setSSLSocketFactory((javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory.getDefault());
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            return null;
        }
    }
}