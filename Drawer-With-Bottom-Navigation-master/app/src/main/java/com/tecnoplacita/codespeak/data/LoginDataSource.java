package com.tecnoplacita.codespeak.data;

import android.util.Log;

import com.tecnoplacita.codespeak.data.model.LoggedInUser;
import com.tecnoplacita.codespeak.io.request.LoginRequest;
import com.tecnoplacita.codespeak.io.responses.LoginResponse;
import com.tecnoplacita.codespeak.network.NetworkApiAdapter;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication with login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        try {
            LoginRequest loginRequest = new LoginRequest(username, password);

            // Realiza la solicitud de autenticación en un hilo separado
            Call<LoginResponse> call = NetworkApiAdapter.getApiService().autentificacion(loginRequest);
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                        LoginResponse loginResponse = response.body();

                        // Si el login es exitoso, crea un LoggedInUser y actualiza el resultado
                        LoggedInUser user = new LoggedInUser(
                                java.util.UUID.randomUUID().toString(), // Genera un ID único para el usuario
                                username // Nombre del usuario obtenido del servidor
                        );
                        // Aquí debes informar a la capa que maneja los resultados del login
                        // Ejemplo: loginResult.postValue(new Result.Success<>(user));
                    } else {
                        Log.i("LoginDataSource", "Server returned error: " + response.message());
                        // Ejemplo: loginResult.postValue(new Result.Error(new IOException("Server error")));
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.i("LoginDataSource", "Network Error: " + t.getLocalizedMessage());
                    // Ejemplo: loginResult.postValue(new Result.Error(new IOException("Network error")));
                }
            });

            // Retorna un estado provisional mientras se procesa la solicitud
         //   return new Result.Loading(); // Deberías manejar el estado de carga en la capa de ViewModel

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
        return null;
    }

    public void logout() {
        // Implementa la lógica de cierre de sesión si es necesario
    }
}
