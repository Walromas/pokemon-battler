package com.PokemonBattler.GUI;

import javax.net.ssl.*;
import javax.print.DocFlavor;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;

public class DisableSSLVerification {
    public static HttpURLConnection openConnectionWithCustomSSL(URL url) {
        try {
            if (url.getHost().contains("githubusercontent.com")) {
                // Disable SSL verification for specific website
                SSLContext sc = SSLContext.getInstance("TLS");
                TrustManager[] trustAllCertificates = new TrustManager[]{
                        new X509TrustManager() {
                            public X509Certificate[] getAcceptedIssuers() {
                                return null;
                            }

                            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            }

                            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                            }
                        }
                };
                sc.init(null, trustAllCertificates, new java.security.SecureRandom());

                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

                // Disable hostname verification for the specific website
                HostnameVerifier allHostsValid = (hostname, session) -> true;
                HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            }

            // Open connection
            return (HttpURLConnection) url.openConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}