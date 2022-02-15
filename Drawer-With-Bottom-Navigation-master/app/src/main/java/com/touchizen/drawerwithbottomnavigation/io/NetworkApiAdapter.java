package com.touchizen.drawerwithbottomnavigation.io;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroClientesApiAdapter {
    private static RegistroClientesApiService API_SERVICE;

    public static RegistroClientesApiService getApiService() {

        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
      //  OkHttpClient.Builder httpClient = new OkHttpClient.Builder();// sin el header bearer token
     //   httpClient.addInterceptor(logging);




        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY0NzQ4MDI3Mn0.66kpkpePGHLqt2qhE17L9hq9kQ5Q6e_V9H8zEEiiVR9w55Zargi5arWCsqoBfW9PrA4KgNxAPewvWN0ijWuqzQ")
                        .build();

                return chain.proceed(newRequest);
            }
        }).build();




        String baseUrl = "http://192.168.100.6:8080/api/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient) // <-- usamos el log level
                  //  .client(httpClient.build()) // <-- se utiliza cuando ya no se incorpora el token

                    .build();
            API_SERVICE = retrofit.create(RegistroClientesApiService.class);
        }

        return API_SERVICE;
    }
}
