package com.touchizen.drawerwithbottomnavigation.network;

import com.touchizen.drawerwithbottomnavigation.io.request.LoginRequest;
import com.touchizen.drawerwithbottomnavigation.io.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
