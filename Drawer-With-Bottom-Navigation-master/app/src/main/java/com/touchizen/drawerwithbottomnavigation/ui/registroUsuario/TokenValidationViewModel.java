package com.touchizen.drawerwithbottomnavigation.ui.registroUsuario;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.touchizen.drawerwithbottomnavigation.data.repository.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.touchizen.drawerwithbottomnavigation.io.responses.ErrorResponse;

public class TokenValidationViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<String> _status = new MutableLiveData<>();
    public LiveData<String> getStatus() {
        return _status;
    }

    public TokenValidationViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateToken(String token) {
        userRepository.verifyToken(token).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    _status.setValue("Validación exitosa");
                } else {
                    // Manejo del error
                    try {
                        // Intentar obtener el cuerpo de la respuesta de error
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        _status.setValue("Error: " + errorResponse.getDetail());
                    } catch (Exception e) {
                        _status.setValue("Error en la validación: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                _status.setValue("Error de red: " + t.getMessage());
            }
        });
    }
}
