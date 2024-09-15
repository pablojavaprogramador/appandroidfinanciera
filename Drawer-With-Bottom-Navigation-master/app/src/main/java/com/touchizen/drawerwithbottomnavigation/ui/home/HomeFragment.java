package com.touchizen.drawerwithbottomnavigation.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.responses.ClienteResponse;
import com.touchizen.drawerwithbottomnavigation.io.responses.UsuariosReponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements Callback<UsuariosReponse> {
    ListViewAdapterHome adapter;
    String[] titulo = new String[]{
            "Adelanto de quincena",
            "Bitcoin  Comprados"
    };

    String[] mensaje = new String[]{
            "Actualmente estas al Corriente",
            "Actualmente tienes 1 Bitcoin"
    };

    int[] imagenes = {
            R.drawable.baseline_paid_24,
            R.drawable.baseline_paid_24


    };

    private HomeViewModel homeViewModel;
    TextView textcredito=null;
    TextView txtsaldo=null;
    TextView textCliente=null;
    TextView textEmail=null;
    TextView textActivate=null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        obtenerDatosClientes();
        View root = inflater.inflate(R.layout.fragment_home, container, false);

         textCliente=root.findViewById(R.id.text_nombre);
         textcredito=root.findViewById(R.id.text_credito_disponible);
         txtsaldo=root.findViewById(R.id.text_Saldo);
        textEmail=root.findViewById(R.id.text_Email);
        textActivate=root.findViewById(R.id.text_Activate);

        final ListView lista = (ListView) root.findViewById(R.id.listViewHome);
        adapter = new ListViewAdapterHome(this, titulo, mensaje,imagenes,inflater);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "presiono " + i, Toast.LENGTH_SHORT).show();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "presiono LARGO " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return root;
    }

    private void obtenerDatosClientes() {
        String idCliente = "1";
        Call<UsuariosReponse> call= NetworkApiAdapter.getApiService().getUsuarios(idCliente);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<UsuariosReponse> call, Response<UsuariosReponse> response) {
        if(response.isSuccessful()){
            UsuariosReponse respuestaServicioCliente=response.body();
            poblarPerfil(response.body());
        }
    }

    private void poblarPerfil(UsuariosReponse body) {
        textCliente.setText("Bienvenida a mi Bolsillo: " +body.getFirstName() +" " +body.getLastName());
        textcredito.setText("Credito con el que cuentas $10000");
        textEmail.setText("Correo Registrado: "+body.getEmail());
        if(body.isActivated()==true){
        textActivate.setText("Estatus: Activo");
        }else{
            textActivate.setText("Estatus: Inactivo");
        }
        txtsaldo.setText("Productos con el que se cuenta ");

//        textSaldoView.setText("30000");
//        textCreditoDisponibleView.setText("90000");
    }


    @Override
    public void onFailure(Call<UsuariosReponse> call, Throwable t) {

    }
}