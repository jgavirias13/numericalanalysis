package com.eafit.numericalanalysis.estructuras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;

import java.util.ArrayList;

/**
 * Created by jgavi on 23/10/2016.
 */

public class BusquedasAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<ColumnaBusquedas> datos;

    public BusquedasAdapter(Context context, ArrayList datos) {
        super(context, R.layout.lista_busquedas, datos);
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.lista_busquedas, null);

        TextView txtN = (TextView) item.findViewById(R.id.txtBusquedasN);
        TextView txtX0 = (TextView) item.findViewById(R.id.txtBusquedasX0);
        TextView txtX1 = (TextView) item.findViewById(R.id.txtBusquedasX1);

        txtN.setText(String.format("%d",datos.get(position).getN()));
        txtX0.setText(String.format("%.2f",datos.get(position).getX0()));
        txtX1.setText(String.format("%.1g",datos.get(position).getX1()));

        return item;
    }

}
