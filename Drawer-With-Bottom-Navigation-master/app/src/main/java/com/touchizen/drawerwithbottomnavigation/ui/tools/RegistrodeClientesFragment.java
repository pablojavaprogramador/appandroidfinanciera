package com.touchizen.drawerwithbottomnavigation.ui.tools;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.request.Clientes;
import com.touchizen.drawerwithbottomnavigation.network.NetworkApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrodeClientesFragment extends Fragment implements Callback<Void> {

    private TextView nombreClieten_Cliente =null;
    private TextView apellidoPaternoReferencia_Cliente=null;
    private TextView apellidoMaternoReferencia_Cliente=null;
    private TextView fechadeNacimientoReferencia_Cliente=null;
    private TextView celularReferencia_Cliente=null;
    private TextView telefonoCasaReferencia_Cliente=null;
//    private TextView comentariosReferencia_Cliente=null;
    private TextView respuestaCliente=null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_clientes, container, false);
        nombreClieten_Cliente= root.findViewById(R.id.edit_Text_Nombre_Cliente);
        apellidoPaternoReferencia_Cliente = root.findViewById(R.id.edit_Text_Apellido_Paterno_Cliente);
        apellidoMaternoReferencia_Cliente = root.findViewById(R.id.edit_Text_ApellidoMaterno_Cliente);
        fechadeNacimientoReferencia_Cliente = root.findViewById(R.id.edit_Text_fechadeNacimiento_Cliente);
        celularReferencia_Cliente = root.findViewById(R.id.edit_Text_Celular_Cliente);
        telefonoCasaReferencia_Cliente=root.findViewById(R.id.edit_Text_Telefono_Cliente);
     //   comentariosReferencia_Cliente=root.findViewById(R.id.edit_Text_Comentarios_Cliente);
        respuestaCliente = root.findViewById(R.id.id_respuestaReferencia_label_Cliente);
        Button boton = root.findViewById(R.id.buttonRegistrar_Cliente);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton","se presiono");
                Clientes enviodatos=new Clientes();

                enviodatos.setNombre(nombreClieten_Cliente.getText().toString());
                enviodatos.setApellidoPaterno(apellidoPaternoReferencia_Cliente.getText().toString());
                enviodatos.setApellidoMaterno(apellidoMaternoReferencia_Cliente.getText().toString());
                enviodatos.setFechaNacimiento(fechadeNacimientoReferencia_Cliente.getText().toString());
                enviodatos.setClaveElector("Clave de Elector");
                enviodatos.setIdentificacionOCR("Indentificador OCR");
                enviodatos.setCorreoElectronico("tecnoplacita@gmail.com");

                enviodatos.setCelular(celularReferencia_Cliente.getText().toString());
                enviodatos.setTelefono(telefonoCasaReferencia_Cliente.getText().toString());

                RegistrarClientes(enviodatos);

            }
        });
        return root;

    }



    @SuppressLint("LongLogTag")
    private void RegistrarClientes(Clientes enviodatos) {

        Call<Void> call = NetworkApiAdapter.getApiService().RegistroClientes(enviodatos);
        call.enqueue(this);

        Log.i("entro en Registrar Cliente","ewqe");
    }


    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.code()==201){
            Void respuestaServicioReferencia=response.body();
            // Toast.makeText(getActivity(),"Ingresa el Codigo que enviamos a:" +"tecnoplacita@gmail.com",Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(),"Se Registro/Actualizo La Refrencia",Toast.LENGTH_SHORT).show();
            respuestaCliente.setHighlightColor(5);
            respuestaCliente.setText("Se Registro/Actualizo El Cliente");


            Log.i("traza:", String.valueOf(response.code()));

        }else{

            Log.i("error", String.valueOf(response.message()));
//            ApiResponseError message = new Gson().fromJson(response.errorBody().charStream(), ApiResponseError.class);
       //     List<FieldError> mensajes = message.getFieldErrors();
      //      String mensaje=null;
     //       String campo=null;
       //     for(int i=0;i<mensajes.size();i++){
        //        campo=mensajes.get(i).getField().toString();
        //        mensaje=mensajes.get(i).getMessage().toString();
        //    }
            // respuestaRegistro.setText(response.message());
        //   respuestaCliente.setText(campo+" "+mensaje);
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