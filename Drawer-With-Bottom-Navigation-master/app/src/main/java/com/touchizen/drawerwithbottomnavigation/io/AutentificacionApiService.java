package com.touchizen.drawerwithbottomnavigation.io;

import com.touchizen.drawerwithbottomnavigation.io.responses.autentificacionResponse;
import com.touchizen.drawerwithbottomnavigation.model.Clientes;
import com.touchizen.drawerwithbottomnavigation.model.autentificacion;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AutentificacionApiService {
    @GET("autentificacion")
    Call<autentificacionResponse> getAutentificacion();
}
