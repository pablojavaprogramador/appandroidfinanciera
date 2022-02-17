package com.touchizen.drawerwithbottomnavigation.io;

import com.touchizen.drawerwithbottomnavigation.io.responses.ClienteResponse;
import com.touchizen.drawerwithbottomnavigation.io.responses.SimpleResponse;
import com.touchizen.drawerwithbottomnavigation.model.Clientes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NeworkApiService {

    @GET("clientes")
    Call<ArrayList<Clientes>>getClientes();
    @GET("clientes/{idCliente}")
    Call<ClienteResponse>getCliente(@Path("idCliente") String idCliente);

    @POST("api/register")
    Call<SimpleResponse> postPhoto(
            @Field("login") String login,
            @Field("password") String password,
            @Field("email") String email
    );

}
