package com.touchizen.drawerwithbottomnavigation.ui.registroUsuario;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;

import com.touchizen.drawerwithbottomnavigation.io.request.Authority;
import com.touchizen.drawerwithbottomnavigation.io.request.RequestRegistro;
import com.touchizen.drawerwithbottomnavigation.io.responses.error.ApiResponseError;
import com.touchizen.drawerwithbottomnavigation.io.responses.error.FieldError;
import com.touchizen.drawerwithbottomnavigation.ui.login.LoginActivity;

import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends Activity implements Callback<Void> {

    public TextView nombreCliente=null;
    public  TextView correElectronico=null;
    public  TextView  password=null;
    public  TextView  confirmarPassword=null;
    public TextView respuestaRegistro=null;

    TextView registro=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_registro);

        nombreCliente = findViewById(R.id.id_Persona);
        correElectronico = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.editTextPassword);
        confirmarPassword =findViewById(R.id.editTextConfirmarPassword);
        respuestaRegistro = findViewById(R.id.respuestaRegistro);
        Button boton =findViewById(R.id.buttonRegistrarUsuario);
        TextView inicioSesionLabel=findViewById(R.id.link_to_login);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton","se presiono");
                RequestRegistro enviodatos=new RequestRegistro();
                enviodatos.setLogin(nombreCliente.getText().toString());
                enviodatos.setEmail(correElectronico.getText().toString());
                enviodatos.setPassword(password.getText().toString());
                Set<Authority> autorizaciones = new HashSet<>();
                Authority autorizacion =new Authority();
                autorizacion.setName("name");
                autorizaciones.add(autorizacion);
                enviodatos.setAuthorities(autorizaciones);
                RegistrarClientes(enviodatos);

                // Crear fragmento de tu clase
                //  Fragment fragment = new ReferenciasPersonalesFragment();
// Obtener el administrador de fragmentos a través de la actividad
                //   FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
// Definir una transacción
                //    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
// Remplazar el contenido principal por el fragmento R.id.drawer_layout
                //      fragmentTransaction.replace(R.id.drawer_layout, fragment);
                //    fragmentTransaction.addToBackStack(null);
// Cambiar
                //     fragmentTransaction.commit();
            }
        });


        inicioSesionLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton inicio de sesion", "se presiono");
                Intent intent=new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressLint("LongLogTag")
    private void RegistrarClientes(RequestRegistro enviodatos) {

        Call<Void> call = NetworkApiAdapter.getApiService().registroUsuarios(enviodatos);
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
            Toast.makeText(this,"Se envio un Correo de Activacion al Correo " +"tecnoplacita@gmail.com",Toast.LENGTH_SHORT).show();
            respuestaRegistro.setHighlightColor(5);
            respuestaRegistro.setText("Se envio un Correo de Activacion al Correo " +"tecnoplacita@gmail.com");


            Log.i("traza:", String.valueOf(response.code()));

        }else if (response.code()==400){

            Log.i("error", String.valueOf(response.errorBody().contentType().toString()));

             Log.i("error", String.valueOf(response.errorBody().source()));
            Log.i("error", String.valueOf(response.errorBody().source().buffer().toString()));
            Log.i("error", String.valueOf(response.errorBody().contentLength()));
            Log.i("error", String.valueOf(response.raw().toString()));
            Log.i("error", String.valueOf(response.code()));
            Log.i("error", String.valueOf(response.raw()));
            Log.i("error", String.valueOf(response.errorBody().charStream().toString()));
            ApiResponseError message = new Gson().fromJson(response.errorBody().charStream(), ApiResponseError.class);

            List<FieldError> mensajes = message.getFieldErrors();
            String mensaje=null;
            String campo=null;
            for(int i=0;i<mensajes.size();i++){
                campo=mensajes.get(i).getField().toString();
                mensaje=mensajes.get(i).getMessage().toString();
            }
            // respuestaRegistro.setText(response.message());
//            respuestaRegistro.setText(campo+" "+mensaje);
//            respuestaRegistro.setText(mensajes.get(0).toString());
//             Toast.makeText(null, "" + mensaje.toString(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        System.out.println("Network Error :: " + t.getLocalizedMessage());
        Log.i("errordos", t.getLocalizedMessage());
    }

}
