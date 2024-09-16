package com.touchizen.drawerwithbottomnavigation.ui.registroUsuario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.touchizen.drawerwithbottomnavigation.data.model.RequestRegistro;
import com.touchizen.drawerwithbottomnavigation.data.repository.UserRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroViewModel extends ViewModel {

    private final UserRepository userRepository;
    private final MutableLiveData<String> _status = new MutableLiveData<>();
    public LiveData<String> status = _status;

    public RegistroViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String usuario, String email, String password) {
        RequestRegistro request = new RequestRegistro();
        request.setLogin(usuario);
        request.setEmail(email);
        request.setPassword(password);

        userRepository.registerUser(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    _status.setValue("Registro exitoso");
                } else {

                    _status.setValue("Error en el registro "+response.message().toString() );
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                _status.setValue("Error de red: " + t.getMessage());
            }
        });
    }
}
