package com.tecnoplacita.codespeak.ui.recargas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnoplacita.codespeak.R;


public class ListViewAdapterRecargas extends BaseAdapter {
    // Declare Variables
    RecargasFragment context;
    String[] titulos;
    String[] mensajes;
    int[] imagenes;
    LayoutInflater inflater;

    public ListViewAdapterRecargas(RecargasFragment context, String[] titulo, String[] mensaje, int[] imagenes, LayoutInflater inflater) {
        this.context = context;
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
        TextView txtTitle;
        ImageView imgImg;
        TextView txtproductos;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
      // inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_row_recargas, parent, false);

        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.list_row_title_recargas);
        txtproductos = (TextView) itemView.findViewById(R.id.list_row_producto_recargas);
        imgImg = (ImageView) itemView.findViewById(R.id.list_row_image_recargas);


        // Capture position and set to the TextViews
        txtTitle.setText(titulos[position]);
        txtproductos.setText(mensajes[position]);
        imgImg.setImageResource(imagenes[position]);



        return itemView;
    }
}