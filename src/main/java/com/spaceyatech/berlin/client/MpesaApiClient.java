package com.spaceyatech.berlin.client;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceyatech.berlin.requests.MpesaC2bRequest;
import com.spaceyatech.berlin.requests.MpesaExpressRequest;
import com.spaceyatech.berlin.requests.MpesaTransactionStatusRequestBody;
import com.spaceyatech.berlin.response.MpesaResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
@Slf4j
@Component
public class MpesaApiClient {

    String consumerKey = "O49ERw35rAq9p1DjaaizpPGtAJa6S3ks";
    String consumerSecret = "yHEGXwcouHBfKlbc";


    private final String MPESA_BASE_URL = "https://sandbox.safaricom.co.ke/";

    private final OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))//logging the call
            .build();

   // private final OkHttpClient httpClient = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    //mpesa authentication
    public String authenticate() throws IOException {


        log.info("======mpesa authentication started===");

        String appKeySecret = consumerKey + ":" + consumerSecret;
        byte[] bytes = appKeySecret.getBytes(StandardCharsets.ISO_8859_1);
        String encoded = Base64.getEncoder().encodeToString(bytes);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(MPESA_BASE_URL + "oauth/v1/generate?grant_type=client_credentials")
                .get()
                .addHeader("authorization", "Basic "+encoded)
                .addHeader("cache-control", "no-cache")
                .build();

        JSONObject jsonObject = null;
        try (Response response = client.newCall(request).execute()) {
            log.info("auth response ------>{}", response);
            if (response.isSuccessful() && response.body() != null) {
                jsonObject = new JSONObject(response.body().string());
                log.info("access token -----> {}", jsonObject.getString("access_token"));
                return jsonObject.getString("access_token");
            } else {
                log.error("Failed to authenticate. Response code: {}", response.code());
                throw new IOException("Failed to authenticate ( Response code : "+response.code()+" )");
            }
        } catch (IOException | JSONException e) {
            log.error("Exception occurred during authentication", e);
            throw e;
        }
    }


    //MpesaTransactionStatus
    public MpesaResponse getTransactionStatus(MpesaTransactionStatusRequestBody requestBody) throws IOException {

        RequestBody body = RequestBody.create( objectMapper.writeValueAsString(requestBody),MediaType.parse("application/json; charset=utf-8"));
        log.info("MPESA_AUTH_TOKEN :{}",authenticate());
        log.info("request body:{}",requestBody);


        Request request = new Request.Builder()
                .url(MPESA_BASE_URL + "mpesa/transactionstatus/v1/query")
                .addHeader("Authorization", "Bearer " + authenticate())
                .post(body)
                .build();
        log.info("request to safaricom {} : ",request);


        String responseBody;
        try (Response response = httpClient.newCall(request).execute()) {
            assert response.body() != null;
            responseBody = response.body().string();
        }
        log.info("response from safaricom {} : ",responseBody);

        return objectMapper.readValue(responseBody, MpesaResponse.class);
    }
    //mpesa c2b
    public MpesaResponse getC2bTransaction(MpesaC2bRequest requestBody) throws IOException {
        RequestBody body = RequestBody.create(objectMapper.writeValueAsString(requestBody),MediaType.parse("application/json; charset=utf-8"));
        log.info("MPESA_AUTH_TOKEN :{}",authenticate());
        log.info("request body:{}",requestBody);


        Request request = new Request.Builder()
                .url(MPESA_BASE_URL + "mpesa/c2b/v1/simulate")
                .addHeader("Authorization", "Bearer " + authenticate())
                .post(body)
                .build();



        String responseBody;
        try (Response response = httpClient.newCall(request).execute()) {
            assert response.body() != null;
            responseBody = response.body().string();
        }


        return objectMapper.readValue(responseBody, MpesaResponse.class);

    }
    //STKPUsh
    public MpesaResponse sendmpesaExpressStkPush(MpesaExpressRequest requestBody) throws IOException {
        log.info("====STK PUSH ACTIVITIES STARTED====");

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String requestBodyJson = objectMapper.writeValueAsString(requestBody);

        log.info("json -----------> {}",requestBodyJson);

        RequestBody body = RequestBody.create( requestBodyJson,mediaType);


        //RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), objectMapper.writeValueAsString(requestBody));

        log.info("MPESA_AUTH_TOKEN :{}",authenticate());
        log.info("request body:{}",requestBody);


        Request request = new Request.Builder()
                .url(MPESA_BASE_URL + "mpesa/stkpush/v1/processrequest")
                .addHeader("Content-Type", "application/json;charset=UTF-8")
                .addHeader("Authorization", "Bearer " + authenticate())
                .post(body)
                .build();



        String responseBody;
        try (Response response = httpClient.newCall(request).execute()) {
            assert response.body() != null;
            responseBody = response.body().string();
        }


        return objectMapper.readValue(responseBody, MpesaResponse.class);

    }
}

