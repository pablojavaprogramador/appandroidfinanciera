package com.touchizen.drawerwithbottomnavigation.io;

import com.touchizen.drawerwithbottomnavigation.io.responses.clientesResponse;
import com.touchizen.drawerwithbottomnavigation.model.Clientes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegistroClientesApiService {
    @GET("clientes")
    Call<ArrayList<Clientes>>getClientes();


}
