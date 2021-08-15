package com.sqz;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpGetClient {

    public static HttpEntity get(String url) {
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httpclient!=null) {
                try {
                    httpclient.close();
                }catch (Exception e) {
                    httpclient = null;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //System.out.println(get("http://www.baidu.com"));
        System.out.println(get("http://127.0.0.1:8081"));
    }
}
