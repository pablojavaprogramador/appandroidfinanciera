package com.touchizen.drawerwithbottomnavigation.ui.pago;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.touchizen.drawerwithbottomnavigation.R;


public class PagoCompraBitcoinsFragment extends Fragment {
TextView textCliente=null;
EditText editTarjeta=null;
Button buttonPago=null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pagocomprabitcoins, container, false);


        return root;
    }
}