package org.stratagem.LCU;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PUT {
    public static void put(String protocol, String url, String port, String endpoint, String auth, String data) {
        try {
            String fullUrl = protocol + "://" + url + ":" + port + endpoint;
            URL obj = java.net.URI.create(fullUrl).toURL();
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Authorization", auth);
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setDoOutput(true);

            if ("https".equalsIgnoreCase(protocol) && con instanceof javax.net.ssl.HttpsURLConnection) {
                ((javax.net.ssl.HttpsURLConnection) con).setSSLSocketFactory(
                        (javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory.getDefault()
                );
            }

            if (data != null) {
                try (OutputStream os = con.getOutputStream()) {
                    os.write(data.getBytes(java.nio.charset.StandardCharsets.UTF_8));
                }
            }

            con.getResponseCode();
        } catch (Exception ignored) {
        }
    }
}