package com.touchizen.drawerwithbottomnavigation.ui.tools;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.request.Clientes;
import com.touchizen.drawerwithbottomnavigation.io.request.Empleos;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToolsFragment extends Fragment implements Callback<Void> {
private TextView calle_Empleo=null;
private TextView cargoPublico_Empleo=null;
private TextView codigoPostal_Empleo=null;
private TextView familiarCargoPublico_Empleo=null;
private TextView ingresosComprobables_Empleo=null;
private TextView numeroExterior_Empleo=null;
private TextView respuestaEmpleo=null;

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        calle_Empleo= root.findViewById(R.id.edit_Text_Calle_Empleo);
     cargoPublico_Empleo= root.findViewById(R.id.edit_Text_CargoPublico_Empleo);
       codigoPostal_Empleo= root.findViewById(R.id.edit_Text_CodigoPostal_Empleo);
         familiarCargoPublico_Empleo= root.findViewById(R.id.edit_Text_FamiliarCargoPublico_Empleo);
       ingresosComprobables_Empleo= root.findViewById(R.id.edit_Text_IngresosComprobables_Empleo);
        numeroExterior_Empleo= root.findViewById(R.id.edit_Text_NumeroExterior_Empleo);
        respuestaEmpleo= root.findViewById(R.id.id_banderas_label_empleo);
Button empleoBoton=root.findViewById(R.id.buttonRegistrar_Empleo);
        empleoBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("boton","se presiono el boton Empleo");
                Empleos enviodatos=new Empleos();
                enviodatos.setCalle(calle_Empleo.getText().toString());
                enviodatos.setCargoPublico(cargoPublico_Empleo.getText().toString());
                enviodatos.setCodigoPostal(codigoPostal_Empleo.getText().toString());
                enviodatos.setFamiliarCargoPublico(familiarCargoPublico_Empleo.getText().toString());
                enviodatos.setIngresosComprobables(ingresosComprobables_Empleo.getText().toString());
                enviodatos.setNumeroExterior(numeroExterior_Empleo.getText().toString());


                RegistrarEmpleos(enviodatos);

            }
        });


        return root;
    }

    @SuppressLint("LongLogTag")
    private void RegistrarEmpleos(Empleos enviodatos) {
        Call<Void> call = NetworkApiAdapter.getApiService().RegistroEmpleos(enviodatos);
        call.enqueue(this);

        Log.i("entro en Registrar Cliente","ewqe");
    }

    @Override
    public void onResponse(Call<Void> call, Response<Void> response) {
        if(response.code()==201){
            Void respuestaServicioReferencia=response.body();
            // Toast.makeText(getActivity(),"Ingresa el Codigo que enviamos a:" +"tecnoplacita@gmail.com",Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(),"Se Registro/Actualizo Empleo",Toast.LENGTH_SHORT).show();
            respuestaEmpleo.setHighlightColor(5);
            respuestaEmpleo.setText("Se Registro/Actualizo El Cliente");


            Log.i("traza:", String.valueOf(response.code()));

        }else{
            Log.i("error", String.valueOf(response.message()));
            Toast.makeText(getActivity(),"Hola",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        System.out.println("Network Error :: " + t.getLocalizedMessage());
        Log.i("errordos", t.getLocalizedMessage());
    }
}