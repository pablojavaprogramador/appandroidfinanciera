package com.touchizen.drawerwithbottomnavigation.ui.referencias;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.touchizen.drawerwithbottomnavigation.R;

public class ReferenciasPersonalesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_referencias, container, false);
       // final TextView textView = root.findViewById(R.id.text_send);


           //return inflater.inflate(R.layout.fragment_chatbot, container, false);
         return root;
    }


}