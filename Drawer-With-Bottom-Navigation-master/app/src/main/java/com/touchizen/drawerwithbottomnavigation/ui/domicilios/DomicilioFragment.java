package com.touchizen.drawerwithbottomnavigation.ui.domicilios;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.request.Clientes;
import com.touchizen.drawerwithbottomnavigation.io.request.Domicilios;
import com.touchizen.drawerwithbottomnavigation.io.responses.RespuestaOk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DomicilioFragment extends Fragment implements Callback<RespuestaOk> {
    private TextView andador_Domicilio =null;
    private TextView calle_Domicilio=null;
    private TextView codigoPostal_Domicilio=null;
    private TextView domicilioActual_Domicilio=null;
    private TextView edificio_Domicilio=null;
    private TextView fechaAntiguedad_Domicilio=null;
    private TextView manzana_Domicilio=null;
    private TextView numeroExterior_Domicilio=null;
    private TextView numeroInterior_Domicilio=null;
    private TextView respuestaDomicilio=null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_domicilios, container, false);


        andador_Domicilio =root.findViewById(R.id.edit_Text_domicilioActual_Domicilio);
        calle_Domicilio=root.findViewById(R.id.edit_Text_Calle_Domiclio);
        codigoPostal_Domicilio=root.findViewById(R.id.edit_Text_CodigoPostal_Domicilio);
        domicilioActual_Domicilio=root.findViewById(R.id.edit_Text_domicilioActual_Domicilio);
        edificio_Domicilio=root.findViewById(R.id.edit_Text_edificio_Domicilio);
        fechaAntiguedad_Domicilio=root.findViewById(R.id.edit_Text_fechaAntiguedad_Domicilio);
        manzana_Domicilio=root.findViewById(R.id.edit_Text_Manzana_Domicilio);
        numeroExterior_Domicilio=root.findViewById(R.id.edit_Text_numeroExterior_Domicilio);
        numeroInterior_Domicilio=root.findViewById(R.id.edit_Text_numeroExterior_Domicilio);
        respuestaDomicilio=root.findViewById(R.id.id_respuestaReferencia_label_Domicilio);
        Button boton = root.findViewById(R.id.buttonRegistrarDomicilio);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton","se presiono");
                Domicilios enviodatos=new Domicilios();
                enviodatos.setAndador(andador_Domicilio.getText().toString());
                enviodatos.setCalle(calle_Domicilio.getText().toString());
                enviodatos.setCodigoPostal(codigoPostal_Domicilio.getText().toString());
                enviodatos.setDomicilioActual(domicilioActual_Domicilio.getText().toString());
                enviodatos.setFechaAntiguedad(fechaAntiguedad_Domicilio.getText().toString());
                enviodatos.setManzana(manzana_Domicilio.getText().toString());
                enviodatos.setNumeroExterior(numeroExterior_Domicilio.getText().toString());
                enviodatos.setNumeroInterior(numeroInterior_Domicilio.getText().toString());

                RegistrarDomicilios(enviodatos);

            }
        });
        return root;
    }

    @SuppressLint("LongLogTag")
    private void RegistrarDomicilios(Domicilios enviodatos) {

        Call<RespuestaOk> call = NetworkApiAdapter.getApiService().RegistroDomicilios(enviodatos);
        call.enqueue(this);

        Log.i("entro en Registrar Domicilio","ewqe");
    }

    @Override
    public void onResponse(Call<RespuestaOk> call, Response<RespuestaOk> response) {
        if(response.code()==201){
            RespuestaOk respuestaServicioReferencia=response.body();
            // Toast.makeText(getActivity(),"Ingresa el Codigo que enviamos a:" +"tecnoplacita@gmail.com",Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(),"Se Registro/Actualizo El Domicilio",Toast.LENGTH_SHORT).show();
            respuestaDomicilio.setHighlightColor(5);
            respuestaDomicilio.setText("Se Registro/Actualizo El Domicilio");
            Log.i("traza:", String.valueOf(response.code()));
            Log.i("traza:", String.valueOf(respuestaServicioReferencia.getMensaje()));

        }else{
            Log.i("error", String.valueOf(response.message()));
          Toast.makeText(getActivity(),"Hola",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<RespuestaOk> call, Throwable t) {
        System.out.println("Network Error :: " + t.getLocalizedMessage());
        Log.i("errordos", t.getLocalizedMessage());
    }
}