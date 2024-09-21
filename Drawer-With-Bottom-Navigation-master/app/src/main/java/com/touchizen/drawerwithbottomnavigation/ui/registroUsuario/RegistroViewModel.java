package com.touchizen.drawerwithbottomnavigation.ui.registroUsuario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.touchizen.drawerwithbottomnavigation.io.responses.ErrorResponse;
import com.touchizen.drawerwithbottomnavigation.data.model.RequestRegistro;
import com.touchizen.drawerwithbottomnavigation.data.repository.UserRepository;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.google.gson.Gson; // Asegúrate de importar Gson

public class RegistroViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<String> _status = new MutableLiveData<>();
    public LiveData<String> status = _status;

    public RegistroViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String usuario, String email, String password, boolean avisoPrivacidad) {
        RequestRegistro request = new RequestRegistro();
        request.setUsuario(usuario);
        request.setEmail(email);
        request.setPassword(password);
        request.setAceptoAvisoPrivacidad(avisoPrivacidad);

        userRepository.registerUser(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    _status.setValue("Registro exitoso, Validacion Pendiente");
                } else {
                    try {
                        // Intentar obtener el cuerpo de la respuesta de error
                        ErrorResponse errorResponse = new Gson().fromJson(response.errorBody().string(), ErrorResponse.class);
                        _status.setValue("Error: " + errorResponse.getDescription());
                    } catch (Exception e) {
                        _status.setValue("Error en el registro: " + e.getMessage());
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
