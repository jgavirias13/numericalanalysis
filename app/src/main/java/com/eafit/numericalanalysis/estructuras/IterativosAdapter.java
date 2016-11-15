package com.eafit.numericalanalysis.estructuras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;

import java.util.ArrayList;

/**
 * Created by jgavi on 13/11/2016.
 */

public class IterativosAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Double> datos;

    public IterativosAdapter(Context context, ArrayList datos) {
        super(context, R.layout.lista_iterativos, datos);
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.lista_iterativos, null);

        TextView txtIteracion = (TextView) item.findViewById(R.id.txtIteracion);

        txtIteracion.setText("x"+position+":");
        final EditText edtValor = (EditText) item.findViewById(R.id.edtValor);
        edtValor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String valor = edtValor.getText().toString();
                if(valor.equals(""))
                    datos.set(position,0d);
                else
                    datos.set(position, Double.parseDouble(valor));
            }
        });

        return item;
    }

    public double getValor(int posicion){
        return datos.get(posicion);
    }
}
