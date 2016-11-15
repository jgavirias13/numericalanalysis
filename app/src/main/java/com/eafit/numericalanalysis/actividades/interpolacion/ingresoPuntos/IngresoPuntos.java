package com.eafit.numericalanalysis.actividades.interpolacion.ingresoPuntos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.PuntosAdapter;
import com.eafit.numericalanalysis.util.Tupla;

import java.util.ArrayList;

public class IngresoPuntos extends AppCompatActivity implements View.OnClickListener{

    private ListView listaPuntos;
    private EditText edtNPuntos;
    private int n = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_puntos);

        listaPuntos = (ListView) findViewById(R.id.listaPuntos);
        edtNPuntos = (EditText) findViewById(R.id.edtNumeroPuntos);

        findViewById(R.id.btnAceptar).setOnClickListener(this);
        findViewById(R.id.btnGuardar).setOnClickListener(this);

        ArrayList<Tupla> datos = new ArrayList<>();
        for(int i=0; i<3; i++){
            datos.add(new Tupla(0,0));
        }
        PuntosAdapter puntosAdapter = new PuntosAdapter(this, datos);
        listaPuntos.setAdapter(puntosAdapter);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnAceptar:
                try{
                    n = Integer.parseInt(edtNPuntos.getText().toString());
                }catch (java.lang.NumberFormatException e){
                    n = 3;
                }
                ArrayList<Tupla> datos = new ArrayList<>();
                for(int i=0; i<n; i++){
                    datos.add(new Tupla(0,0));
                }
                PuntosAdapter puntosAdapter = new PuntosAdapter(this, datos);
                listaPuntos.setAdapter(puntosAdapter);
                break;
            case R.id.btnGuardar:
                edtNPuntos.requestFocus();
                PuntosAdapter adapter = (PuntosAdapter) listaPuntos.getAdapter();
                if(n != 0){
                    Tupla[] tuplasPuntos = new Tupla[n];
                    for(int i=0; i<n; i++){
                        tuplasPuntos[i] = adapter.getPunto(i);
                    }
                    EstadoPuntos estado = EstadoPuntos.solicitarEstado();
                    estado.setPuntos(tuplasPuntos);
                    finish();
                }
                break;
        }
    }
}
