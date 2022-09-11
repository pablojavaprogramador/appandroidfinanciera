package com.touchizen.drawerwithbottomnavigation.io;

import com.touchizen.drawerwithbottomnavigation.io.request.Domicilios;
import com.touchizen.drawerwithbottomnavigation.io.request.Empleos;
import com.touchizen.drawerwithbottomnavigation.io.request.LoginRequest;

import com.touchizen.drawerwithbottomnavigation.io.responses.ClienteResponse;
import com.touchizen.drawerwithbottomnavigation.io.request.RequestRegistro;
import com.touchizen.drawerwithbottomnavigation.io.request.Clientes;
import com.touchizen.drawerwithbottomnavigation.io.request.ReferenciaPersonal;
import com.touchizen.drawerwithbottomnavigation.io.responses.LoginResponse;
import com.touchizen.drawerwithbottomnavigation.io.responses.RespuestaOk;
import com.touchizen.drawerwithbottomnavigation.io.responses.UsuariosReponse;

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
    @POST("usuarios")
    Call<Void> registroUsuarios(@Body RequestRegistro simpleResponse);

    @GET("usuarios/{idUsuarios}")
    Call<UsuariosReponse>getUsuarios(@Path("idUsuarios") String usuarios);

    @POST("authenticate")
    Call<LoginResponse>autentificacion(@Body LoginRequest simpleResponse);

    @POST("referencia-personales")
    Call<Void>registroReferencia(@Body ReferenciaPersonal referencia);
    @POST("clientes")
    Call<Void>RegistroClientes(@Body Clientes clientes );
    @POST("empleos")
    Call<Void>RegistroEmpleos(@Body Empleos empleos );
    @POST("domicilios")
    Call<RespuestaOk>RegistroDomicilios(@Body Domicilios domicilios );
}
