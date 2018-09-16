package com.example.safi.testretrofit;

import com.example.safi.testretrofit.user_api.UserName;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndUser {
    @GET("/")
    Call<UserName> getUser();
}
