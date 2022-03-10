package com.touchizen.drawerwithbottomnavigation.ui.referencias;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.request.ReferenciaPersonal;
import com.touchizen.drawerwithbottomnavigation.io.responses.error.ApiResponseError;
import com.touchizen.drawerwithbottomnavigation.io.responses.error.FieldError;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReferenciasPersonalesFragment extends Fragment implements Callback<Void> {

    private TextView nombreReferencia =null;
    private TextView apellidoPaternoReferencia=null;
    private TextView apellidoMaternoReferencia=null;
    private TextView fechadeNacimientoReferencia=null;
    private TextView celularReferencia=null;
    private TextView telefonoCasaReferencia=null;
  //  private TextView comentariosReferencia=null;
    private TextView respuestaReferencia=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_referencias, container, false);
        nombreReferencia= root.findViewById(R.id.edit_Text_Nombre);
        apellidoPaternoReferencia = root.findViewById(R.id.edit_Text_Apellido_Paterno);
        apellidoMaternoReferencia = root.findViewById(R.id.edit_Text_ApellidoMaterno);
        fechadeNacimientoReferencia = root.findViewById(R.id.edit_Text_fechadeNacimiento);
        celularReferencia = root.findViewById(R.id.edit_Text_Celular);
        telefonoCasaReferencia=root.findViewById(R.id.edit_Text_Telefono);
       // comentariosReferencia=root.findViewById(R.id.edit_Text_Comentarios);
     respuestaReferencia = root.findViewById(R.id.id_respuestaReferencia_label);
        Button boton = root.findViewById(R.id.buttonRegistrarReferencia);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton","se presiono");
                ReferenciaPersonal enviodatos=new ReferenciaPersonal();
                enviodatos.setNombre(nombreReferencia.getText().toString());
                enviodatos.setApellidoPaterno(apellidoPaternoReferencia.getText().toString());
                enviodatos.setApellidoMaterno(apellidoMaternoReferencia.getText().toString());
                enviodatos.setFechaNacimiento(fechadeNacimientoReferencia.getText().toString());
                enviodatos.setCelular(celularReferencia.getText().toString());
                enviodatos.setTelefono(telefonoCasaReferencia.getText().toString());
//                enviodatos.setComentarios(comentariosReferencia.getText().toString());
                RegistrarReferencias(enviodatos);

            }
        });
         return root;
    }


    @SuppressLint("LongLogTag")
    private void RegistrarReferencias(ReferenciaPersonal enviodatos) {

        Call<Void> call = NetworkApiAdapter.getApiService().registroReferencia(enviodatos);
        call.enqueue(this);

        Log.i("entro en Registrar Cliente","ewqe");
    }


    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.code()==201){
            Void respuestaServicioReferencia=response.body();
            // Toast.makeText(getActivity(),"Ingresa el Codigo que enviamos a:" +"tecnoplacita@gmail.com",Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(),"Se Registro/Actualizo La Refrencia",Toast.LENGTH_SHORT).show();
            respuestaReferencia.setHighlightColor(5);
            respuestaReferencia.setText("Se Registro/Actualizo La Refrencia");


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
            respuestaReferencia.setText(campo+" "+mensaje);
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