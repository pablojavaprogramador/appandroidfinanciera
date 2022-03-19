package com.touchizen.drawerwithbottomnavigation.ui.dashboard;

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

public class EstadosFinnancierosFragment extends Fragment {

    ListViewAdapter adapter;

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
        adapter = new ListViewAdapter(this, titulo, imagenes,inflater);
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