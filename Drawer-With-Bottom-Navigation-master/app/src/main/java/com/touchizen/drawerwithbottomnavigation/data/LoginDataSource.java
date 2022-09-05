package com.touchizen.drawerwithbottomnavigation.data;

import android.annotation.SuppressLint;
import android.util.Log;

import com.touchizen.drawerwithbottomnavigation.data.model.LoggedInUser;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.request.LoginRequest;
import com.touchizen.drawerwithbottomnavigation.io.responses.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource implements Callback<LoginResponse> {
    LoginResponse respuesta=null;
    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            LoginRequest enviodatos=new LoginRequest();
            enviodatos.setUsername(username);
            enviodatos.setPassword(password);
           LoginUsuarios(enviodatos);

            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {

    }

    @SuppressLint("LongLogTag")
    private void LoginUsuarios(LoginRequest enviodatos) {

        Call<LoginResponse> call = NetworkApiAdapter.getApiService().autentificacion(enviodatos);
        call.enqueue(this);
        Log.i("entro en Registrar Cliente","ewqe");
     //   return call;

    }


    private void poblarLogin(Response<LoginResponse> body) {

    }

    @Override
    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
        if(response.isSuccessful()){
           // LoginResponse respuestaServicioLogin=response.body();
            poblarLogin(response);
        }else{

            Log.i("error", String.valueOf(response.message()));


        }
    }



    @Override
    public void onFailure(Call<LoginResponse> call, Throwable t) {
        System.out.println("Network Error :: " + t.getLocalizedMessage());
        Log.i("errordos", t.getLocalizedMessage());
    }






}