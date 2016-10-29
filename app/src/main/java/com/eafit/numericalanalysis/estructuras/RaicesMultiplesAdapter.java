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

public class RaicesMultiplesAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<ColumnaRaicesMultiples> datos;

    public RaicesMultiplesAdapter(Context context, ArrayList datos) {
        super(context, R.layout.lista_raices_multiples, datos);
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.lista_raices_multiples, null);

        TextView txtN = (TextView) item.findViewById(R.id.txtRaicesMultiplesN);
        TextView txtX = (TextView) item.findViewById(R.id.txtRaicesMultiplesX);
        TextView txtFx = (TextView) item.findViewById(R.id.txtRaicesMultiplesFX);
        TextView txtFDx = (TextView) item.findViewById(R.id.txtRaicesMultiplesDX);
        TextView txtF2Dx = (TextView) item.findViewById(R.id.txtRaicesMultiples2DX);
        TextView txtError = (TextView) item.findViewById(R.id.txtNewtonError);

        txtN.setText(String.format("%d",datos.get(position).getN()));
        txtX.setText(String.format("%.2g",datos.get(position).getX()));
        txtFx.setText(String.format("%.2g",datos.get(position).getFx()));
        txtFDx.setText(String.format("%.2g",datos.get(position).getFdx()));
        txtF2Dx.setText(String.format("%.2g",datos.get(position).getF2dx()));
        txtError.setText(String.format("%.2g", datos.get(position).getError()));

        return item;
    }
}
