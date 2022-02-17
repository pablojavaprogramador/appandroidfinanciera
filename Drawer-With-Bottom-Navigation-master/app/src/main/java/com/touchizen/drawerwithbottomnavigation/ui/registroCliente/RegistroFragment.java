package com.touchizen.drawerwithbottomnavigation.ui.registroCliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.touchizen.drawerwithbottomnavigation.R;

public class RegistroFragment extends Fragment {


    TextView registro=null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

         View root = inflater.inflate(R.layout.fragment_registro, container, false);


        return root;
    }
}