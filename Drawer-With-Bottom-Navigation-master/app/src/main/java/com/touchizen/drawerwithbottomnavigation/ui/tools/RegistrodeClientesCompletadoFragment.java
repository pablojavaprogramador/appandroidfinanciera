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

import androidx.fragment.app.Fragment;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.request.Clientes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrodeClientesCompletadoFragment extends Fragment  {

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
        View root = inflater.inflate(R.layout.fragment_registrado, container, false);
        nombreClieten_Cliente= root.findViewById(R.id.registrado);;
        respuestaCliente = root.findViewById(R.id.respuestaRegistrado);

        return root;

    }



}