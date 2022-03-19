package com.touchizen.drawerwithbottomnavigation.ui.oportunidades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.touchizen.drawerwithbottomnavigation.R;


public class OportunidadesFragment extends Fragment {
    ListViewAdapterOportunidades adapter;
    String[] titulo = new String[]{
            "¿Necesitas dinero ? obten un adelanto de quincena",
            "Compra Bitcoins",
            "Crea una Tanda",
            "¿Comprar Acciones"
    };

    String[] mensaje = new String[]{
            "Disponible por tiempo Limitado",
            "La moneda digital al alcance de un click",
            "Abre tu propio credito Online",
            "Comprar acciones de Mi bolsillo",
    };

    int[] imagenes = {
            R.drawable.baseline_paid_24,
            R.drawable.baseline_currency_bitcoin_24,
            R.drawable.baseline_location_city_24,
            R.drawable.baseline_satellite_alt_20

    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_oportunidades, container, false);
        final ListView lista = (ListView) root.findViewById(R.id.listView1);
        adapter = new ListViewAdapterOportunidades(this, titulo, mensaje,imagenes,inflater);
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