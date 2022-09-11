package com.touchizen.drawerwithbottomnavigation.ui.cupones;


import android.annotation.SuppressLint;
import android.os.Bundle;
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
import androidx.fragment.app.FragmentTransaction;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.ui.recargas.ListViewAdapterRecargas;

public class CuponesFragment extends Fragment  {

    ListViewAdapterCupones adapter;
    String[] titulo = new String[]{
            "Burger King",
            "Pizza Hut",
            "Bodega Ahorrera"
    };

    String[] mensaje = new String[]{
            "10 %  de Descuenta en 2 x 1 Codigo ABCXPOUOP",
            "Llevate una pizza familiar con 30% Descuento Codigo ZBCXPOUOP",
            "10% en Electrodosmesticos  Codigo PBCXPOUOP"

    };

    int[] imagenes = {
            R.drawable.baseline_description_24,
            R.drawable.baseline_description_24,
            R.drawable.baseline_description_24,
            R.drawable.baseline_description_24


    };


    private TextView recargaLabel=null;
    private TextView companiaLabel=null;
    private Button bottonRecargas=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_recargas, container, false);
      //  recargaLabel= root.findViewById(R.id.id_recarga_Label);
        companiaLabel=root.findViewById(R.id.id_compania_Label);



        final ListView lista = (ListView) root.findViewById(R.id.listViewRecarga1);
        adapter = new ListViewAdapterCupones(this, titulo, mensaje,imagenes,inflater);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {


                Toast.makeText(getActivity(), "El Cupon estara Disponible Por Tiempo Limitado " + i, Toast.LENGTH_SHORT).show();
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