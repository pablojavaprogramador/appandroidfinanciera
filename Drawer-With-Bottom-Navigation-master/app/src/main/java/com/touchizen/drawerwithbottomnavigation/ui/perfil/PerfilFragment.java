package com.touchizen.drawerwithbottomnavigation.ui.gallery;

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
import com.touchizen.drawerwithbottomnavigation.io.RegistroClientesApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.responses.clientesResponse;
import com.touchizen.drawerwithbottomnavigation.model.Clientes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilFragment extends Fragment implements Callback<ArrayList<Clientes>> {

    private PerfilViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        galleryViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        Call<ArrayList<Clientes>> call= RegistroClientesApiAdapter.getApiService().getClientes();
        call.enqueue( this);


        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onResponse(Call<ArrayList<Clientes>> call, Response<ArrayList<Clientes>> response) {
        if(response.isSuccessful()){
          ArrayList<Clientes>clientes= response.body();
          Log.d("onResponse PerfilFragment","Tamaño de Clientes" +clientes.size());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Clientes>> call, Throwable t) {

    }
}