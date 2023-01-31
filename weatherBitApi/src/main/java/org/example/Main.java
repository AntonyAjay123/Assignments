package org.example;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter city");
        String city = obj.next();
        String apiEndPoint = "https://api.weatherbit.io/v2.0/current";
        String apiKey = "837766fca5a6477b81ca6235cc1ea21e";
        URIBuilder builder = new URIBuilder(apiEndPoint);
        builder.setParameter("key",apiKey)
                .setParameter("city",city);
        HttpGet data = new HttpGet(builder.build());
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = client.execute(data);

        if(response.getStatusLine().getStatusCode()==200){
            HttpEntity responseEntity = response.getEntity();
            String result = EntityUtils.toString(responseEntity);
            JSONObject json = new JSONObject(result);
            System.out.println(json);
        }
        else System.out.println("Something went wrong");

    }
}