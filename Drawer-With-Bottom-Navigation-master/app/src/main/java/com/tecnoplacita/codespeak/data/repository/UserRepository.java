package com.tecnoplacita.codespeak.data.repository;


import com.tecnoplacita.codespeak.data.model.RequestRegistro;
import com.tecnoplacita.codespeak.io.request.TokenVerificationRequest;
import com.tecnoplacita.codespeak.network.ApiService;

import retrofit2.Call;
import retrofit2.Response;

public class UserRepository {

    private final ApiService apiService;

    public UserRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<Void> registerUser(RequestRegistro request) {
        return apiService.registerUser(request);
    }


    public Call<Void> verifyToken(String token) {
        TokenVerificationRequest request = new TokenVerificationRequest(token);
        return apiService.verifyToken(request);
    }

    public void handleResponse(Response<Void> response) {
        if (response.code() == 200) {
            // Manejar éxito
        } else {
            // Manejar error
        }
    }

    public void handleError(Throwable t) {
        // Manejar error de red
    }
}
