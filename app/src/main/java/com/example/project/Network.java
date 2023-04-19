package com.example.project;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static String BASE_URL = "https://api.prop2p.ru/";
    private static Network Instance;
    private final Retrofit retrofit;
    private static String token;
    public static void setToken(String token) {
        Network.token = token;
    }

    public static String getToken() {
        return token;
    }

    public Network() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static Network getInstance() {
        if(Instance == null){
            Instance = new Network();
        }
        return Instance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }
}
