package com.touchizen.drawerwithbottomnavigation.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.responses.ClienteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements Callback<ClienteResponse> {

    private HomeViewModel homeViewModel;
    TextView textcredito=null;
    TextView txtsaldo=null;
    TextView textCliente=null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        obtenerDatosClientes();
        View root = inflater.inflate(R.layout.fragment_home, container, false);


         textCliente=root.findViewById(R.id.text_nombre);
         textcredito=root.findViewById(R.id.text_credito_disponible);
         txtsaldo=root.findViewById(R.id.text_Saldo);



        return root;
    }

    private void obtenerDatosClientes() {
        String idCliente = "9";
        Call<ClienteResponse> call= NetworkApiAdapter.getApiService().getCliente(idCliente);
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
        textCliente.setText(body.getNombre()+body.getApellidoPaterno()+" "+body.getApellidoMaterno());
        textcredito.setText("10000");
        txtsaldo.setText("5200");
//        textSaldoView.setText("30000");
//        textCreditoDisponibleView.setText("90000");
    }


    @Override
    public void onFailure(Call<ClienteResponse> call, Throwable t) {

    }
}