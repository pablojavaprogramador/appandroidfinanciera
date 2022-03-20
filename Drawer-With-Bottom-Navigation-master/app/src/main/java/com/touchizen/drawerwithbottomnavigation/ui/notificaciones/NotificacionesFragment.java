package com.touchizen.drawerwithbottomnavigation.ui.notificaciones;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.request.RequestRegistro;
import com.touchizen.drawerwithbottomnavigation.io.responses.error.ApiResponseError;
import com.touchizen.drawerwithbottomnavigation.io.responses.error.FieldError;
import com.touchizen.drawerwithbottomnavigation.ui.oportunidades.ListViewAdapterOportunidades;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//Cambio de Fragments

//
public class NotificacionesFragment extends Fragment   {

    ListViewAdapterNotificaciones adapter;
    String[] fechaHora = new String[]{
            "12/05/1987  3:30",
            "12/05/1987  3:30",
            "12/05/1987  3:30",
            "12/05/1987  3:30"
    };

    String[] titulo = new String[]{
            "Paga  Realizado      $1000",
            "Cargo Realizado     $1000",
            "Quieres ir de Vacaciones",
            "Realiza tu pago antes del dia 27"
    };

    String[] mensaje = new String[]{
            "Movimientos",
            "Movimientos",
            "Promociones",
            "Alertas",
    };

    int[] imagenes = {
            R.drawable.baseline_attach_money_24,
            R.drawable.baseline_attach_money_24,
            R.drawable.baseline_local_offer_24,
            R.drawable.baseline_notifications_active_24

    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notificaciones, container, false);
        final ListView lista = (ListView) root.findViewById(R.id.listView1);
        adapter = new ListViewAdapterNotificaciones(this, fechaHora,titulo, mensaje,imagenes,inflater);
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



}