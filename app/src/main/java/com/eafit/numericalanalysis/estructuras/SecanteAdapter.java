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

public class SecanteAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<ColumnaSecante> datos;

    public SecanteAdapter(Context context, ArrayList datos) {
        super(context, R.layout.lista_secante, datos);
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.lista_secante, null);

        TextView txtN = (TextView) item.findViewById(R.id.txtSecanteN);
        TextView txtX = (TextView) item.findViewById(R.id.txtSecanteX0);
        TextView txtFx = (TextView) item.findViewById(R.id.txtSecanteFX);
        TextView txtError = (TextView) item.findViewById(R.id.txtSecanteError);

        txtN.setText(String.format("%d",datos.get(position).getN()));
        txtX.setText(String.format("%.2g",datos.get(position).getX0()));
        txtFx.setText(String.format("%.2g",datos.get(position).getFx()));
        txtError.setText(String.format("%.2g", datos.get(position).getError()));

        return item;
    }
}
