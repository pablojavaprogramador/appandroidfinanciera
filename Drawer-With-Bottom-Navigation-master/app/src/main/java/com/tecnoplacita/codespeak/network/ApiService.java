package com.tecnoplacita.codespeak.network;

import com.tecnoplacita.codespeak.data.model.RequestRegistro;
import com.tecnoplacita.codespeak.io.request.ClientesRequestAntiguo;
import com.tecnoplacita.codespeak.io.request.Domicilios;
import com.tecnoplacita.codespeak.io.request.Empleos;
import com.tecnoplacita.codespeak.io.request.LoginRequest;
import com.tecnoplacita.codespeak.io.request.PasswordResetConfirmationRequest;
import com.tecnoplacita.codespeak.io.request.PasswordResetRequest;
import com.tecnoplacita.codespeak.io.request.ReferenciaPersonal;
import com.tecnoplacita.codespeak.io.request.SearchRequest;
import com.tecnoplacita.codespeak.io.responses.ClienteRespuestaAntiguo;
import com.tecnoplacita.codespeak.io.responses.LoginResponse;
import com.tecnoplacita.codespeak.io.responses.PasswordResetConfirmationResponse;
import com.tecnoplacita.codespeak.io.responses.PasswordResetResponse;
import com.tecnoplacita.codespeak.io.request.TokenVerificationRequest;
import com.tecnoplacita.codespeak.io.responses.RespuestaOk;
import com.tecnoplacita.codespeak.io.responses.UsuariosReponse;
import com.tecnoplacita.codespeak.io.responses.VerbResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    Call<ArrayList<ClientesRequestAntiguo>>getClientes();
    @GET("clientes/{idCliente}")
    Call<ClienteRespuestaAntiguo>getCliente(@Path("idCliente") String idCliente);

    @GET("usuarios/{idUsuarios}")
    Call<UsuariosReponse>getUsuarios(@Path("idUsuarios") String usuarios);

    @POST("authenticate")
    Call<LoginResponse>autentificacion(@Body LoginRequest simpleResponse);

    @POST("referencia-personales")
    Call<Void>registroReferencia(@Body ReferenciaPersonal referencia);
    @POST("clientes")
    Call<Void>RegistroClientes(@Body ClientesRequestAntiguo clientes );
    @POST("empleos")
    Call<Void>RegistroEmpleos(@Body Empleos empleos );
    @POST("domicilios")
    Call<RespuestaOk>RegistroDomicilios(@Body Domicilios domicilios );


    @POST("/api/verbs/search")
    Call<List<VerbResponse>> searchVerbosWithAuth(@Header("Authorization") String token, @Body SearchRequest searchRequest);

}

