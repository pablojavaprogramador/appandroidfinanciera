package com.touchizen.drawerwithbottomnavigation.ui.tarjetaderegalo;


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
import com.touchizen.drawerwithbottomnavigation.ui.pago.PagoTarjetasdeRegaloFragment;
import com.touchizen.drawerwithbottomnavigation.ui.recargas.ListViewAdapterRecargas;
import com.touchizen.drawerwithbottomnavigation.ui.recargas.RecargasFragment;

public class TarjetasRegaloFragment extends Fragment  {

    ListViewAdapterTarjetasRegalo adapter;
    String[] titulo = new String[]{
            "Amazon",
            "Cinepolis",
            "Microsoft",
            "Nintendo"
    };

    String[] mensaje = new String[]{
            "Compra tu Tarjeta Amazon",
            "Compra tu Tarjeta  Cinepolis",
            "Compra tu Tarjeta Microsoft",
            "Compra tu Tarjeta Nintendo"


    };

    int[] imagenes = {
            R.drawable.baseline_card_giftcard_24,
            R.drawable.baseline_card_giftcard_24,
            R.drawable.baseline_card_giftcard_24,
            R.drawable.baseline_card_giftcard_24,


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
        adapter = new ListViewAdapterTarjetasRegalo(this, titulo, mensaje,imagenes,inflater);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Fragment tarjetasRegalo = new PagoTarjetasdeRegaloFragment();
                FragmentTransaction regalo = getParentFragmentManager().beginTransaction();
                regalo.replace(R.id.nav_host_fragment, tarjetasRegalo);
                regalo.addToBackStack("tarjetasderegalo");
                // Commit a la transacción
                regalo.commit();
                Toast.makeText(getActivity(), "Tarjetas de regalo" + i, Toast.LENGTH_SHORT).show();


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