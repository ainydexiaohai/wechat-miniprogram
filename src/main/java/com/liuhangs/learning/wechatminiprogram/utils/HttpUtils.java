package com.liuhangs.learning.wechatminiprogram.utils;

import com.google.gson.Gson;
import com.sun.javafx.fxml.builder.URLBuilder;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {

    private static final Gson gson = new Gson();

    public static <T> T doGet(String url, Map<String, String> params, Class<T> targetClass) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        List<NameValuePair> queryParams = new ArrayList<>();
        if(params!=null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                queryParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        try {
            URI requestUrl = new URIBuilder(url).addParameters(queryParams).build();
            HttpGet httpGet = new HttpGet(requestUrl);
            httpGet.addHeader("Content-Type","application/json;charset=UTF-8");
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //返回json格式
                String res = EntityUtils.toString(response.getEntity(), "utf-8");
                return gson.fromJson(res, targetClass);
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
