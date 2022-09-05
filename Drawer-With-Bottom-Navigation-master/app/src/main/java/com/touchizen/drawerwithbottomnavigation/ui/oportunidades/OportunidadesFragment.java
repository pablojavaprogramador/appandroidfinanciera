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
            "Pago de Servicios",
            "Recargas de Celular",
            "Tarjetas de Regalo",
            "Compra Bitcoins",
            "Cupones",
            "Mercado Digital",
    };

    String[] mensaje = new String[]{
            "disponible por tiempo Limitado",
            "luz,telefono ,internet,gobierno,financieros.",
            "recargas de celular telcel,movistar,unefon.",
            "amazon,microsoft,xbox,play station y muchos mas.",
            "la moneda digital al alcance de un click",
            "cupones de Descuento ",
            "vende y compra productos online "

    };

    int[] imagenes = {
            R.drawable.baseline_paid_24,
            R.drawable.baseline_location_city_24,
            R.drawable.baseline_phone_iphone_24,
            R.drawable.baseline_card_giftcard_24,
            R.drawable.baseline_currency_bitcoin_24,
            R.drawable.baseline_description_24,
            R.drawable.baseline_local_grocery_store_24


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