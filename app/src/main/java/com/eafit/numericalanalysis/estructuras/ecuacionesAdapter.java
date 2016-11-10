package com.eafit.numericalanalysis.estructuras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.eafit.numericalanalysis.R;

import java.util.ArrayList;

/**
 * Created by jgavi on 8/11/2016.
 */

public class EcuacionesAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<Ecuacion> datos;

    public EcuacionesAdapter(Context context, ArrayList datos) {
        super(context, R.layout.contenido_grid, datos);
        this.context = context;
        this.datos = datos;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.contenido_grid, null);
        EditText editText = (EditText) item.findViewById(R.id.edtEcuacion);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try {
                    datos.get(position).coeficiente = Double.parseDouble(((EditText) v).getText().toString());
                }catch (java.lang.NumberFormatException e){

                }
            }
        });
        return item;
    }

    public Ecuacion getEcuacion(int position){
        return datos.get(position);
    }

    public int getSize(){
        return datos.size();
    }
}
