package com.touchizen.drawerwithbottomnavigation.io;

import com.touchizen.drawerwithbottomnavigation.io.request.Empleos;
import com.touchizen.drawerwithbottomnavigation.io.responses.ClienteResponse;
import com.touchizen.drawerwithbottomnavigation.io.request.RequestRegistro;
import com.touchizen.drawerwithbottomnavigation.io.request.Clientes;
import com.touchizen.drawerwithbottomnavigation.io.request.ReferenciaPersonal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NeworkApiService {

    @GET("clientes")
    Call<ArrayList<Clientes>>getClientes();

    @GET("clientes/{idCliente}")
    Call<ClienteResponse>getCliente(@Path("idCliente") String idCliente);


    @POST("register")
    Call<Void> registroUsuarios(@Body RequestRegistro simpleResponse);
    @POST("referencia-personales")
    Call<Void>registroReferencia(@Body ReferenciaPersonal referencia);

    @POST("clientes")
    Call<Void>RegistroClientes(@Body Clientes clientes );

    @POST("empleos")
    Call<Void>RegistroEmpleos(@Body Empleos empleos );
}
