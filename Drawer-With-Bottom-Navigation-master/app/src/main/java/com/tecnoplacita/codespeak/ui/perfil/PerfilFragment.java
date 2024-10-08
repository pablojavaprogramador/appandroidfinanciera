package com.tecnoplacita.codespeak.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.tecnoplacita.codespeak.R;
import com.tecnoplacita.codespeak.io.responses.ClienteRespuestaAntiguo;
import com.tecnoplacita.codespeak.network.NetworkApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment implements Callback<ClienteRespuestaAntiguo> {
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
        Call<ClienteRespuestaAntiguo>  call= NetworkApiAdapter.getApiService().getCliente(idCliente);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<ClienteRespuestaAntiguo> call, Response<ClienteRespuestaAntiguo> response) {
    if(response.isSuccessful()){
    ClienteRespuestaAntiguo respuestaServicioCliente=response.body();
    poblarPerfil(response.body());
    }
    }

    private void poblarPerfil(ClienteRespuestaAntiguo body) {
        textNombreView.setText(body.getNombre()+" "+body.getApellidoPaterno()+" "+body.getApellidoMaterno());
        textNumeroContratoView.setText("Contrato Definido");
        textNumeroTelefonoView.setText(body.getTelefono());
        textEmailView.setText(body.getCorreoElectronico());
//        textSaldoView.setText("30000");
//        textCreditoDisponibleView.setText("90000");
   }


    @Override
    public void onFailure(Call<ClienteRespuestaAntiguo> call, Throwable t) {

    }
}