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
 * Created by jgavi on 28/10/2016.
 */

public class BiseccionAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<ColumnaBiseccion> datos;

    public BiseccionAdapter(Context context, ArrayList datos) {
        super(context, R.layout.lista_biseccion, datos);
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.lista_biseccion, null);

        TextView txtN = (TextView) item.findViewById(R.id.txtBiseccionN);
        TextView txtXi = (TextView) item.findViewById(R.id.txtBiseccionXi);
        TextView txtXs = (TextView) item.findViewById(R.id.txtBiseccionXs);
        TextView txtXm = (TextView) item.findViewById(R.id.txtBiseccionXm);
        TextView txtFxm = (TextView) item.findViewById(R.id.txtBiseccionFxm);
        TextView txtError = (TextView) item.findViewById(R.id.txtBiseccionError);

        txtN.setText(String.format("%d",datos.get(position).getN()));
        txtXi.setText(String.format("%.2g",datos.get(position).getXi()));
        txtXs.setText(String.format("%.2g",datos.get(position).getXs()));
        txtXm.setText(String.format("%.2g",datos.get(position).getXm()));
        txtFxm.setText(String.format("%.2g",datos.get(position).getFxm()));
        txtError.setText(String.format("%.2g", datos.get(position).getError()));

        return item;
    }

}
