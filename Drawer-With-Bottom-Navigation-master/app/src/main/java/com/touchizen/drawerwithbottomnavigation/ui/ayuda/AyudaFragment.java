package com.touchizen.drawerwithbottomnavigation.ui.ayuda;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.touchizen.drawerwithbottomnavigation.R;
//Cambio de Fragments

//
public class AyudaFragment extends Fragment   {


    public TextView nombreCliente=null;
    public  TextView correElectronico=null;
    public  TextView  password=null;
    public  TextView  confirmarPassword=null;
    public TextView respuestaRegistro=null;

    TextView registro=null;

    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        
         View root = inflater.inflate(R.layout.fragment_ayuda, container, false);
        nombreCliente = root.findViewById(R.id.id_Persona);
        correElectronico = root.findViewById(R.id.editTextEmailAddress);
        password = root.findViewById(R.id.editTextPassword);
        confirmarPassword = root.findViewById(R.id.editTextConfirmarPassword);
        respuestaRegistro = root.findViewById(R.id.respuestaRegistro);

                // Crear fragmento de tu clase
              //  Fragment fragment = new ReferenciasPersonalesFragment();
// Obtener el administrador de fragmentos a través de la actividad
             //   FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
// Definir una transacción
             //    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
// Remplazar el contenido principal por el fragmento R.id.drawer_layout
          //      fragmentTransaction.replace(R.id.drawer_layout, fragment);
            //    fragmentTransaction.addToBackStack(null);
// Cambiar
           //     fragmentTransaction.commit();

         return root;
    }





}