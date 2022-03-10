package com.touchizen.drawerwithbottomnavigation.io;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkApiAdapterOauth {
    private static NeworkApiService API_SERVICE;

    public static NeworkApiService getApiService() {

        // Creamos un interceptor y le indicamos el log level a usar
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Asociamos el interceptor a las peticiones
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();// sin el header bearer token
       httpClient.addInterceptor(logging);


        final String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY0NzQ4NjYzM30.wLCf4vpioOLKOidB_e9eQjClORlxrDokxv35RNb1qS9K0fEvldqCoNKvY19Ov_T507McaRKQJ-1JijbKHi4S-g";


  //      OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
    //        @Override
      //      public Response intercept(Chain chain) throws IOException {
        //        Request newRequest  = chain.request().newBuilder()
          //              .addHeader("Authorization", "Bearer " + token)
            //            .build();

              //  return chain.proceed(newRequest);
            //}
        //}).build();




        String baseUrl = "http://192.168.100.6:8080/api/";

        if (API_SERVICE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                 //   .client(httpClient) // <-- usamos el log level
                    .client(httpClient.build()) // <-- se utiliza cuando ya no se incorpora el token

                    .build();
            API_SERVICE = retrofit.create(NeworkApiService.class);
        }

        return API_SERVICE;
    }
}
