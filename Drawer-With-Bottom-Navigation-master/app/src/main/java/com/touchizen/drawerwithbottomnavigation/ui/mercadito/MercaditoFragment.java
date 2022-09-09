package com.touchizen.drawerwithbottomnavigation.ui.mercadito;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.ui.cupones.ListViewAdapterCupones;

public class MercaditoFragment extends Fragment  {

    private TextView recargaLabel=null;
    private TextView companiaLabel=null;
    private Button bottonRecargas=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mercadito, container, false);
      //  recargaLabel= root.findViewById(R.id.id_recarga_Label);
     //   companiaLabel=root.findViewById(R.id.id_compania_Label);



    //    final ListView lista = (ListView) root.findViewById(R.id.listViewRecarga1);



         return root;
    }



}