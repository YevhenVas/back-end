package com.brainacad;

import io.qameta.allure.Allure;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class HttpClientHelper {

    public static HttpResponse get(String endpointUrl, String parameters) throws IOException {
       //TODO: написать метод для GET запроса с хедерами по умолчанию
        Map<String, String> headers=new HashMap<>();
        headers.put("User-Agent", "My-Test-User-Agent");
        return get(endpointUrl,parameters, headers);
    }

    //REST GET запрос
    public static HttpResponse get(String endpointUrl, String parameters, Map<String, String> headers) throws IOException {
        Allure.addAttachment("GET input parameters", "endpointURL: "+endpointUrl+" Perameters:"+ parameters);
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP GET запрос из URL и параметров
        HttpGet request = new HttpGet(endpointUrl+"?"+parameters);

        //добавляем в запрос необходимые хедеры
        for(String headerKey:headers.keySet()) {
            request.addHeader(headerKey, headers.get(headerKey));
        }

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(request);
        Allure.addAttachment("GET response status code", "Status code: "+response.getStatusLine().getStatusCode());
        Allure.addAttachment("GET response body", "response body: "+getBodyFromResponse(response));
        //возвращаем response
        return response;
    }


    public static HttpResponse post(String endpointUrl, String parameters)throws IOException{
        //TODO: написать метод для POST запроса с хедерами по умолчанию
        Map<String, String> headers=new HashMap<>();
        headers.put("User-Agent", "My-Test-User-Agent");
        return post(endpointUrl,parameters,headers);
    }

    public static HttpResponse post(String endpointUrl, String body, Map<String, String> headers) throws IOException{
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP POST запрос из URL и параметров
        HttpPost post = new HttpPost(endpointUrl);

        //добавляем в запрос необходимые хедеры
        for(String headerKey:headers.keySet()) {
            post.addHeader(headerKey, headers.get(headerKey));
        }

        //добавляем к запросу тело запроса
        post.setEntity(new StringEntity(body));

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(post);

        //возвращаем response
        return response;
    }


    public static String getBodyFromResponse(HttpResponse response) throws IOException {
        //создаём ридер буффера и передаём в него входящий поток респонса
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line;

        //получаем в цикле построчно строки из входящего потока и собираем в одну строку
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
    //TODO: допишите методы для запросов PUT
    public static HttpResponse put(String endpointUrl, String parameters)throws IOException {
        //TODO: написать метод для Put запроса с хедерами по умолчанию
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "My-Test-User-Agent");
        return put(endpointUrl, parameters, headers);
    }

    public static HttpResponse put(String endpointUrl, String body, Map<String, String> headers) throws IOException{
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP PUT запрос из URL и параметров
        HttpPut put = new HttpPut(endpointUrl);

        //добавляем в запрос необходимые хедеры
        for(String headerKey:headers.keySet()) {
            put.addHeader(headerKey, headers.get(headerKey));
        }

        //добавляем к запросу тело запроса
        put.setEntity(new StringEntity(body));

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(put);

        //возвращаем response
        return response;
    }
    public static HttpResponse patch(String endpointUrl, String parameters)throws IOException {
        //TODO: написать метод для Patch запроса с хедерами по умолчанию
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "My-Test-User-Agent");
        return patch(endpointUrl, parameters, headers);
    }

    public static HttpResponse patch(String endpointUrl, String body, Map<String, String> headers) throws IOException{
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP PUT запрос из URL и параметров
        HttpPatch patch = new HttpPatch(endpointUrl);

        //добавляем в запрос необходимые хедеры
        for(String headerKey:headers.keySet()) {
            patch.addHeader(headerKey, headers.get(headerKey));
        }

        //добавляем к запросу тело запроса
        patch.setEntity(new StringEntity(body));

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(patch);

        //возвращаем response
        return response;
    }
/*
    //TODO: допишите методы для запросов DELETE
    public static HttpResponse delete(String endpointUrl, String parameters)throws IOException {
        //TODO: написать метод для Patch запроса с хедерами по умолчанию
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "My-Test-User-Agent");
        return delete(endpointUrl, parameters, headers);
    }

    public static HttpResponse delete(String endpointUrl, String body, Map<String, String> headers) throws IOException{
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP PUT запрос из URL и параметров
        HttpDelete delete = new HttpDelete(endpointUrl);

        //добавляем в запрос необходимые хедеры
        for(String headerKey:headers.keySet()) {
            delete.addHeader(headerKey, headers.get(headerKey));
        }

        //добавляем к запросу тело запроса
        delete.setEntity(new StringEntity(body));

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(delete);

        //возвращаем response
        return response;
    }*/

    //TODO: допишите методы для запросов PATCH и DELETE

}
