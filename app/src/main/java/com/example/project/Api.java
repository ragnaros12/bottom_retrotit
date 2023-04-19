package com.example.project;

import com.example.project.Models.LoginRequest;
import com.example.project.Models.Token;
import com.example.project.Models.UserInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @POST("/auth/login")
    Call<Token> login(@Body() LoginRequest loginRequest);
    @GET("/users/myInfo")
    Call<UserInfo> info(@Header("Authorization") String token);

    @GET("/users/userById/{id}")
    Call<UserInfo> userById(@Header("Authorization") String token, @Path("id") int id);

    @GET("test")
    Call<Void> test(@Query("data") String data);
}
