package com.tecnoplacita.codespeak.ui.oportunidades;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.tecnoplacita.codespeak.R;
import com.tecnoplacita.codespeak.ui.bitcoin.BitcoinFragment;
import com.tecnoplacita.codespeak.ui.credito.CreditoFragment;
import com.tecnoplacita.codespeak.ui.cupones.CuponesFragment;
import com.tecnoplacita.codespeak.ui.mercadito.MercaditoFragment;
import com.tecnoplacita.codespeak.ui.pagoservicios.PagoServiciosFragment;
import com.tecnoplacita.codespeak.ui.recargas.RecargasFragment;



public class OportunidadesFragment extends Fragment {



  //  ListViewAdapterOportunidades adapter;
    String[] titulo = new String[]{
            "¿Necesitas dinero ? obten un adelanto de quincena",
            "Pago de Servicios",
            "Recargas de Celular",
            "Tarjetas de Regalo",
            "Compra Bitcoins",
            "Cupones",
            "Mercado Digital",
    };

    String[] mensaje = new String[]{
            "disponible por tiempo Limitado",
            "luz,telefono ,internet,gobierno,financieros.",
            "recargas de celular telcel,movistar,unefon.",
            "amazon,microsoft,xbox,play station y muchos mas.",
            "la moneda digital al alcance de un click",
            "cupones de Descuento ",
            "vende y compra productos online "

    };

    int[] imagenes = {
            R.drawable.baseline_paid_24,
            R.drawable.baseline_location_city_24,
            R.drawable.baseline_phone_iphone_24,
            R.drawable.baseline_card_giftcard_24,
            R.drawable.baseline_currency_bitcoin_24,
            R.drawable.baseline_description_24,
            R.drawable.baseline_local_grocery_store_24


    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_oportunidades, container, false);
        final ListView lista = (ListView) root.findViewById(R.id.listView1);
//        adapter = new ListViewAdapterOportunidades(this, titulo, mensaje,imagenes,inflater);
//        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {



                switch(i){
                    case 0:

                        Fragment Credito = new CreditoFragment();
                        FragmentTransaction creditoRegalo = getParentFragmentManager().beginTransaction();
                        creditoRegalo.replace(R.id.nav_host_fragment, Credito);
                        creditoRegalo.addToBackStack("regalo");
                        creditoRegalo.commit();
                        Toast.makeText(getActivity(), "Credito Regalo" , Toast.LENGTH_SHORT).show();

                        break;

                    case 1:
                        Fragment PagoServiciosFragment = new PagoServiciosFragment();
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, PagoServiciosFragment);
                        transaction.addToBackStack("pago");
                        transaction.commit();
                        Toast.makeText(getActivity(), "Pagos de Servicios" + i, Toast.LENGTH_SHORT).show();

                        break;

                    case 2:
                        Fragment RecargasFragment = new RecargasFragment();
                        FragmentTransaction transicion = getParentFragmentManager().beginTransaction();
                        transicion.replace(R.id.nav_host_fragment, RecargasFragment);
                        transicion.addToBackStack("recarga");
                        // Commit a la transacción
                        transicion.commit();
                        Toast.makeText(getActivity(), "Recargas" + i, Toast.LENGTH_SHORT).show();

                        break;

                    case 3:
//                        Fragment TarjetasdeRegalo = new TarjetasRegaloFragment();
//                        FragmentTransaction transaccionregalo = getParentFragmentManager().beginTransaction();
//                        transaccionregalo.replace(R.id.nav_host_fragment, TarjetasdeRegalo);
//                        transaccionregalo.addToBackStack("regalo");
//                        transaccionregalo.commit();
//                        Toast.makeText(getActivity(), "Tarjetas de Regalo" , Toast.LENGTH_SHORT).show();

                        break;
                    case 4:
                        Fragment Bitcoin = new BitcoinFragment();
                        FragmentTransaction bitcoinFragment = getParentFragmentManager().beginTransaction();
                        bitcoinFragment.replace(R.id.nav_host_fragment, Bitcoin);
                        bitcoinFragment.addToBackStack("regalo");
                        bitcoinFragment.commit();
                        Toast.makeText(getActivity(), "Credito Regalo" , Toast.LENGTH_SHORT).show();

                        break;
                    case 5:
                        Fragment nuevoFragmento = new CuponesFragment();
                        FragmentTransaction prueba = getParentFragmentManager().beginTransaction();
                        prueba.replace(R.id.nav_host_fragment, nuevoFragmento);
                        prueba.addToBackStack("cupon");
                        prueba.commit();
                        Toast.makeText(getActivity(), "Cupones de Descuento" + i, Toast.LENGTH_SHORT).show();

                        break;
                    case 6:
                        Fragment mercaditoFragmento = new MercaditoFragment();
                        FragmentTransaction mercadito = getParentFragmentManager().beginTransaction();
                        mercadito.replace(R.id.nav_host_fragment, mercaditoFragmento);
                        mercadito.addToBackStack("cupon");
                        mercadito.commit();
                        Toast.makeText(getActivity(), "Cupones de Descuento" + i, Toast.LENGTH_SHORT).show();

                        break;
                }
                Toast.makeText(getActivity(), "presiono " + i +l, Toast.LENGTH_SHORT).show();
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