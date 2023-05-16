package service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClient {

    public String GetRequest(String url) throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);

            request.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36\n");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("istek hata aldı : " + url);
    }

    public void PostRequest(String url, int rdveriKaynagi, int ddlIller, int ddlIlceler, int ddlSandiklar) throws IOException {

        final HttpPost httpPost = new HttpPost(url);
        final List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("rdveriKaynagi", String.valueOf(rdveriKaynagi)));
        params.add(new BasicNameValuePair("ddlIller", String.valueOf(ddlIller)));
        params.add(new BasicNameValuePair("ddlIlceler", String.valueOf(ddlIlceler)));
        params.add(new BasicNameValuePair("ddlSandiklar", String.valueOf(ddlSandiklar)));
        params.add(new BasicNameValuePair("btnSorgula", "SORGULA"));
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(EntityUtils.toString(response.getEntity()));
        System.out.println("status code : " + statusCode);
    }

}
