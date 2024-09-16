package com.touchizen.drawerwithbottomnavigation.network;

import com.touchizen.drawerwithbottomnavigation.data.model.RequestRegistro;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/auth/signup")
    Call<Void> registerUser(@Body RequestRegistro request);
}

