package com.example.datvl.testcn.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static final String BASE_URL = "https://onlyd.000webhostapp.com/Server/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
//                                .readTimeout(10000, TimeUnit.MICROSECONDS)
////                                .writeTimeout(10000,TimeUnit.MICROSECONDS)
//                                .connectTimeout(2000,TimeUnit.MICROSECONDS)
//                                .retryOnConnectionFailure(true)
                                //.protocols(Arrays.asList(Protocol.HTTP_1_1))
                                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}