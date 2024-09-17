package com.touchizen.drawerwithbottomnavigation.network;

import com.touchizen.drawerwithbottomnavigation.data.model.RequestRegistro;
import com.touchizen.drawerwithbottomnavigation.io.request.LoginRequest;
import com.touchizen.drawerwithbottomnavigation.io.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/auth/signup")
    Call<Void> registerUser(@Body RequestRegistro request);
    @POST("/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}

