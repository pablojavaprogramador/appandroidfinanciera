package com.touchizen.drawerwithbottomnavigation.ui.registroCliente;

import static androidx.core.content.ContextCompat.getSystemService;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.responses.ApiResponseError;
import com.touchizen.drawerwithbottomnavigation.io.responses.ClienteResponse;
import com.touchizen.drawerwithbottomnavigation.io.responses.FieldError;
import com.touchizen.drawerwithbottomnavigation.io.responses.SimpleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroFragment extends Fragment implements Callback<Void>  {


    public TextView nombreCliente=null;
    public  TextView correElectronico=null;
    public  TextView  password=null;
    public  TextView  confirmarPassword=null;
    public TextView respuestaRegistro=null;

    TextView registro=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

         View root = inflater.inflate(R.layout.fragment_registro, container, false);
        nombreCliente = root.findViewById(R.id.editTextCliente);
        correElectronico = root.findViewById(R.id.editTextEmailAddress);
        password = root.findViewById(R.id.editTextPassword);
        confirmarPassword = root.findViewById(R.id.editTextConfirmarPassword);
        respuestaRegistro = root.findViewById(R.id.respuestaRegistro);
        Button boton = root.findViewById(R.id.buttonRegistrarCliente);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton","se presiono");
                SimpleResponse enviodatos=new SimpleResponse();
                enviodatos.setLogin(nombreCliente.getText().toString());
                enviodatos.setEmail(correElectronico.getText().toString());
                enviodatos.setPassword(password.getText().toString());
                RegistrarClientes(enviodatos);


            }
        });
         return root;
    }




    @SuppressLint("LongLogTag")
    private void RegistrarClientes(SimpleResponse enviodatos) {

        Call<Void> call = NetworkApiAdapter.getApiService().registroCliente(enviodatos);
        call.enqueue(this);

        Log.i("entro en Registrar Cliente","ewqe");
    }


    private void poblarPerfil(Void body) {
    //    textNombreView.setText(body.getNombre()+" "+body.getApellidoPaterno()+" "+body.getApellidoMaterno());
      //  textNumeroContratoView.setText("Contrato Definido");
       // textNumeroTelefonoView.setText(body.getTelefono());
        //textEmailView.setText(body.getCorreoElectronico());
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.code()==201){
            Void respuestaServicioRegistroCliente=response.body();
           // Toast.makeText(getActivity(),"Ingresa el Codigo que enviamos a:" +"tecnoplacita@gmail.com",Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(),"Se envio un Correo de Activacion al Correo " +"tecnoplacita@gmail.com",Toast.LENGTH_SHORT).show();
            respuestaRegistro.setHighlightColor(5);
            respuestaRegistro.setText("Se envio un Correo de Activacion al Correo " +"tecnoplacita@gmail.com");


            Log.i("traza:", String.valueOf(response.code()));

        }else{

            Log.i("error", String.valueOf(response.message()));
            ApiResponseError message = new Gson().fromJson(response.errorBody().charStream(), ApiResponseError.class);
            List<FieldError> mensajes = message.getFieldErrors();
            String mensaje=null;
            String campo=null;
            for(int i=0;i<mensajes.size();i++){
                campo=mensajes.get(i).getField().toString();
                mensaje=mensajes.get(i).getMessage().toString();
            }
           // respuestaRegistro.setText(response.message());
            respuestaRegistro.setText(campo+" "+mensaje);
           // Toast.makeText(null, "" + mensaje.toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(),"Hola",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        System.out.println("Network Error :: " + t.getLocalizedMessage());
        Log.i("errordos", t.getLocalizedMessage());
    }




}