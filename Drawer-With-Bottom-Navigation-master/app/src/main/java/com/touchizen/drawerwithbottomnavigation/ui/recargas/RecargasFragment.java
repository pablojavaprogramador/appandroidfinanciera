package com.touchizen.drawerwithbottomnavigation.ui.recargas;


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
import androidx.fragment.app.FragmentTransaction;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.io.NetworkApiAdapter;
import com.touchizen.drawerwithbottomnavigation.io.request.ReferenciaPersonal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecargasFragment extends Fragment  {

    ListViewAdapterRecargas adapter;
    String[] titulo = new String[]{
            "Telcel",
            "Movistar",
            "Unefon",
            "AT&T"
    };

    String[] mensaje = new String[]{
            "Recarga de Saldo Celular",
            "Recarga de Saldo Celular",
            "Recarga de Saldo Celular",
            "Recarga de Saldo Celular"


    };

    int[] imagenes = {
            R.drawable.baseline_phone_iphone_24,
            R.drawable.baseline_phone_iphone_24,
            R.drawable.baseline_phone_iphone_24,
            R.drawable.baseline_phone_iphone_24


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
        adapter = new ListViewAdapterRecargas(this, titulo, mensaje,imagenes,inflater);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                if(i==2){

                    //  FragmentManager fragmentManager = null;
                    // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    // RecargasFragment fragment = new RecargasFragment();
                    //fragmentTransaction.add(R.layout.fragment_home, fragment);
                    //fragmentTransaction.commit();
                    // Crea el nuevo fragmento y la transacción.
                    Fragment nuevoFragmento = new RecargasFragment();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, nuevoFragmento);
                    transaction.addToBackStack(null);

                    // Commit a la transacción
                    transaction.commit();
                    Toast.makeText(getActivity(), "Recargas" + i, Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(getActivity(), "presiono " + i, Toast.LENGTH_SHORT).show();
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