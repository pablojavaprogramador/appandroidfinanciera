package com.tecnoplacita.codespeak.ui.estados;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.tecnoplacita.codespeak.R;
import com.tecnoplacita.codespeak.ui.estados.EstadosFinnancierosFragment;
public class EstadosFinnancierosFragment extends Fragment {

    EstadosListViewAdapter adapter;

    String[] titulo = new String[]{
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Agosto",
            "Octubre"
    };

    int[] imagenes = {
            R.drawable.ic_home_black_24dp,
            R.drawable.baseline_account_balance_24,
            R.drawable.baseline_location_city_24,
            R.drawable.baseline_satellite_alt_20,
            R.drawable.baseline_account_balance_24,
            R.drawable.baseline_location_city_24,
            R.drawable.baseline_account_balance_24,
            R.drawable.baseline_location_city_24
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



          View root = inflater.inflate(R.layout.fragment_estadosfinancieros, container, false);
        final ListView lista = (ListView) root.findViewById(R.id.listView1);
        adapter = new EstadosListViewAdapter(this, titulo, imagenes,inflater);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Aun no hay Registros " , Toast.LENGTH_SHORT).show();
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(), "Aun no hay Registros " , Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return root;
    }


}