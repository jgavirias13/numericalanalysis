package com.eafit.numericalanalysis.estructuras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.util.Tupla;

import java.util.ArrayList;

/**
 * Created by jgavi on 13/11/2016.
 */

public class PuntosAdapter extends ArrayAdapter{
    private Context context;
    private ArrayList<Tupla> datos;

    public PuntosAdapter(Context context, ArrayList datos) {
        super(context, R.layout.lista_interpolacion, datos);
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.lista_interpolacion, null);
        final EditText edtX = (EditText) item.findViewById(R.id.edtX);
        final EditText edtY = (EditText) item.findViewById(R.id.edtY);

        edtX.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    String strX = edtX.getText().toString();
                    double x;
                    if(strX.equals("")){
                        x = 0;
                    }else{
                        x = Double.parseDouble(strX);
                    }
                    datos.get(position).setX(x);
                }catch (java.lang.NumberFormatException e){

                }
            }
        });
        edtY.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    String strY = edtY.getText().toString();
                    double y;
                    if(strY.equals("")){
                        y = 0;
                    }else{
                        y = Double.parseDouble(strY);
                    }
                    datos.get(position).setY(y);
                }catch (java.lang.NumberFormatException e){

                }
            }
        });
        return item;
    }

    public Tupla getPunto(int position){
        return datos.get(position);
    }

    public int getSize(){
        return datos.size();
    }
}
