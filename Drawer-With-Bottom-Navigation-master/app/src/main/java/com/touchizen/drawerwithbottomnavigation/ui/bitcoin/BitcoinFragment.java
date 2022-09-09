package com.touchizen.drawerwithbottomnavigation.ui.bitcoin;


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

public class BitcoinFragment extends Fragment  {



    private TextView recargaLabel=null;
    private TextView companiaLabel=null;
    private Button bottonRecargas=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_bitcoin, container, false);

         return root;
    }



}