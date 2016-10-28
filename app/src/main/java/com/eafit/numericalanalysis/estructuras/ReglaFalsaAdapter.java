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

public class ReglaFalsaAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<ColumnaReglaFalsa> datos;

    public ReglaFalsaAdapter(Context context, ArrayList datos) {
        super(context, R.layout.lista_regla_falsa, datos);
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.lista_regla_falsa, null);

        TextView txtN = (TextView) item.findViewById(R.id.txtReglaFalsaN);
        TextView txtXi = (TextView) item.findViewById(R.id.txtReglaFalsaXi);
        TextView txtXs = (TextView) item.findViewById(R.id.txtReglaFalsaXs);
        TextView txtXm = (TextView) item.findViewById(R.id.txtReglaFalsaXm);
        TextView txtFxm = (TextView) item.findViewById(R.id.txtReglaFalsaFxm);
        TextView txtError = (TextView) item.findViewById(R.id.txtReglaFalsaError);

        txtN.setText(String.format("%d",datos.get(position).getN()));
        txtXi.setText(String.format("%.2g",datos.get(position).getXi()));
        txtXs.setText(String.format("%.2g",datos.get(position).getXs()));
        txtXm.setText(String.format("%.2g",datos.get(position).getXm()));
        txtFxm.setText(String.format("%.2g",datos.get(position).getFxm()));
        txtError.setText(String.format("%.2g", datos.get(position).getError()));

        return item;
    }
}
