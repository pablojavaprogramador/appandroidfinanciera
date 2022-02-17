package com.touchizen.drawerwithbottomnavigation.ui.perfil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.NeworkApiService;
import com.touchizen.drawerwithbottomnavigation.io.responses.ClienteResponse;
import com.touchizen.drawerwithbottomnavigation.model.Cliente;
import com.touchizen.drawerwithbottomnavigation.model.Clientes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment implements Callback<ClienteResponse> {
    public TextView textView=null;
    public  TextView textNombreView=null;
    public  TextView  textEmailView=null;
    public  TextView   textNumeroContratoView=null;
    public  TextView textNumeroTelefonoView=null;
    public  TextView  textSaldoView=null;
    public  TextView  textCreditoDisponibleView=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        obtenerDatosClientes();



        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

      //  Call<ArrayList<Clientes>> call= NetworkApiAdapter.getApiService().getClientes();
        // call.enqueue(this);
         textView = root.findViewById(R.id.text_gallery);
        textNombreView=root.findViewById(R.id.text_nombre_perfil);
        textEmailView=root.findViewById(R.id.text_email);
        textNumeroContratoView=root.findViewById(R.id.text_numero_contrato);
        textNumeroTelefonoView=root.findViewById(R.id.text_telefono);


        return root;
    }

    private void obtenerDatosClientes() {
        String idCliente = "9";
        Call<ClienteResponse>  call= NetworkApiAdapter.getApiService().getCliente(idCliente);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<ClienteResponse> call, Response<ClienteResponse> response) {
if(response.isSuccessful()){
    ClienteResponse respuestaServicioCliente=response.body();
    poblarPerfil(response.body());
}
    }

    private void poblarPerfil(ClienteResponse body) {
        textNombreView.setText(body.getNombre()+" "+body.getApellidoPaterno()+" "+body.getApellidoMaterno());
        textNumeroContratoView.setText("Contrato Definido");
        textNumeroTelefonoView.setText(body.getTelefono());
        textEmailView.setText(body.getCorreoElectronico());
//        textSaldoView.setText("30000");
//        textCreditoDisponibleView.setText("90000");
   }


    @Override
    public void onFailure(Call<ClienteResponse> call, Throwable t) {

    }
}