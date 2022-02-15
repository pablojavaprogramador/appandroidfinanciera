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

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.model.Clientes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment implements Callback<ArrayList<Clientes>> {




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_perfil, container, false);


        Call<ArrayList<Clientes>> call= NetworkApiAdapter.getApiService().getClientes();
         call.enqueue(this);


        final TextView textView = root.findViewById(R.id.text_gallery);
        final TextView textNombreView=root.findViewById(R.id.text_nombre_perfil);
        textNombreView.setText("Rene Cortes Trejo");

        return root;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onResponse(Call<ArrayList<Clientes>> call, Response<ArrayList<Clientes>> response) {
        if(response.isSuccessful()){

          ArrayList<Clientes>clientes= response.body();

          Log.d("onResponse PerfilFragment","Tamaño de Clientes" +clientes.size()+clientes.get(0).getNombre());

        }
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onFailure(Call<ArrayList<Clientes>> call, Throwable t) {

        Log.d("onResponse PerfilFragment","Error" +t.toString());

    }
}