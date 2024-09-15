package com.touchizen.drawerwithbottomnavigation.ui.pagoservicios;

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


public class PagoServiciosFragment extends Fragment {

   // ListViewAdapterPagoServicios adapter;
    String[] titulo = new String[]{
            "Plan Celular",
            "Luz",
            "Agua",
            "Gas",
            "Financiera"

    };

    String[] mensaje = new String[]{
            "Paga Tu Plan de Celular Aqui",
            "Paga tu Recibo de Luz Aqui",
            "Paga tu Agua Aqui",
            "Paga tu Gas Aqui",
            "Paga tu Credito Aqui"

    };

    int[] imagenes = {
            R.drawable.baseline_paid_24,
            R.drawable.baseline_paid_24,
            R.drawable.baseline_paid_24,
            R.drawable.baseline_paid_24,
            R.drawable.baseline_paid_24


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

      //  adapter = new ListViewAdapterPagoServicios(this, titulo, mensaje,imagenes,inflater);
      //  lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
             //   Fragment pagodeServicios = new FormulariopagodeserviciosFragment();
//                FragmentTransaction pagoServicios = getParentFragmentManager().beginTransaction();
//                pagoServicios.replace(R.id.nav_host_fragment, pagodeServicios);
//                pagoServicios.addToBackStack("tarjetasderegalo");
                // Commit a la transacción
                //pagoServicios.commit();
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