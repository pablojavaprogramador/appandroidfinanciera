package com.touchizen.drawerwithbottomnavigation.ui.ayuda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.touchizen.drawerwithbottomnavigation.R;
import com.touchizen.drawerwithbottomnavigation.ui.notificaciones.NotificacionesFragment;

public class ListViewAdapterAyuda extends BaseAdapter {
    // Declare Variables
    AyudaFragment context;
    String[]fechaHoras;
    String[] titulos;
    String[] mensajes;
    int[] imagenes;
    LayoutInflater inflater;

    public ListViewAdapterAyuda(AyudaFragment context, String[] fechaHora, String[] titulo, String[] mensaje, int[] imagenes, LayoutInflater inflater) {
        this.context = context;
        this.fechaHoras = fechaHora;
        this.titulos = titulo;
        this.mensajes = mensaje;
        this.imagenes = imagenes;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return titulos.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        // Declare Variables
        TextView fechaHora;
        TextView txtTitle;
        ImageView imgImg;
        TextView txtproductos;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
      // inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row_ayuda, parent, false);

        // Locate the TextViews in listview_item.xml
        fechaHora= (TextView) itemView.findViewById(R.id.list_row_fechaHora_oportunidades);
        txtTitle = (TextView) itemView.findViewById(R.id.list_row_title_oportunidades);
        txtproductos = (TextView) itemView.findViewById(R.id.list_row_producto_oportunidades);
//        imgImg = (ImageView) itemView.findViewById(R.id.list_row_image_oportunidades);


        // Capture position and set to the TextViews
        fechaHora.setText(fechaHoras[position]);
        txtTitle.setText(titulos[position]);
        txtproductos.setText(mensajes[position]);
//        imgImg.setImageResource(imagenes[position]);



        return itemView;
    }
}