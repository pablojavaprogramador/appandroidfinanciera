package com.touchizen.drawerwithbottomnavigation.network;

import com.touchizen.drawerwithbottomnavigation.data.model.RequestRegistro;
import com.touchizen.drawerwithbottomnavigation.io.request.Clientes;
import com.touchizen.drawerwithbottomnavigation.io.request.Domicilios;
import com.touchizen.drawerwithbottomnavigation.io.request.Empleos;
import com.touchizen.drawerwithbottomnavigation.io.request.LoginRequest;
import com.touchizen.drawerwithbottomnavigation.io.request.PasswordResetConfirmationRequest;
import com.touchizen.drawerwithbottomnavigation.io.request.PasswordResetRequest;
import com.touchizen.drawerwithbottomnavigation.io.request.ReferenciaPersonal;
import com.touchizen.drawerwithbottomnavigation.io.responses.ClienteResponse;
import com.touchizen.drawerwithbottomnavigation.io.responses.LoginResponse;
import com.touchizen.drawerwithbottomnavigation.io.responses.PasswordResetConfirmationResponse;
import com.touchizen.drawerwithbottomnavigation.io.responses.PasswordResetResponse;
import com.touchizen.drawerwithbottomnavigation.io.request.TokenVerificationRequest;
import com.touchizen.drawerwithbottomnavigation.io.responses.RespuestaOk;
import com.touchizen.drawerwithbottomnavigation.io.responses.UsuariosReponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("/auth/signup")
    Call<Void> registerUser(@Body RequestRegistro request);
    @POST("/verificar")
    Call<Void> verifyToken(@Body TokenVerificationRequest request);

    @POST("/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);


    @POST("/auth/reset-password")
    Call<PasswordResetResponse> resetPassword(@Body PasswordResetRequest request);

    @POST("/auth/confirm-reset")
    Call<PasswordResetConfirmationResponse> confirmReset(@Body PasswordResetConfirmationRequest request);

//Revisar Despues

    @GET("clientes")
    Call<ArrayList<Clientes>>getClientes();
    @GET("clientes/{idCliente}")
    Call<ClienteResponse>getCliente(@Path("idCliente") String idCliente);

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

