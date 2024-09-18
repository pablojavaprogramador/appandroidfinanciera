package com.touchizen.drawerwithbottomnavigation.ui.registroUsuario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.touchizen.drawerwithbottomnavigation.data.repository.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                    _status.setValue("Error en la validación: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                _status.setValue("Error de red: " + t.getMessage());
            }
        });
    }
}
