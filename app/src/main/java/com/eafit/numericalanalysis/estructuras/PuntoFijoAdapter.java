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
 * Created by jgavi on 29/10/2016.
 */

public class PuntoFijoAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<ColumnaPuntoFijo> datos;

    public PuntoFijoAdapter(Context context, ArrayList datos) {
        super(context, R.layout.lista_punto_fijo, datos);
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.lista_punto_fijo, null);

        TextView txtN = (TextView) item.findViewById(R.id.txtPuntoFijoN);
        TextView txtX = (TextView) item.findViewById(R.id.txtPuntoFijoX);
        TextView txtFx = (TextView) item.findViewById(R.id.txtPuntoFijoFX);
        TextView txtError = (TextView) item.findViewById(R.id.txtPuntoFijoError);

        txtN.setText(String.format("%d",datos.get(position).getN()));
        txtX.setText(String.format("%.2g",datos.get(position).getX()));
        txtFx.setText(String.format("%.2g",datos.get(position).getFx()));
        txtError.setText(String.format("%.2g", datos.get(position).getError()));

        return item;
    }
}
