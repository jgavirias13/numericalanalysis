package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.ingresoEcuaciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.Ecuacion;
import com.eafit.numericalanalysis.estructuras.EcuacionesAdapter;

import java.util.ArrayList;

public class IngresoEcuaciones extends AppCompatActivity implements View.OnClickListener{

    private GridView gridView;
    private EditText editText;
    private int n=0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_ecuaciones);

        gridView = (GridView) findViewById(R.id.gridEcuaciones);
        editText = (EditText) findViewById(R.id.edtNumeroEcuaciones);
        findViewById(R.id.btnEcuacionesAceptar).setOnClickListener(this);
        findViewById(R.id.btnEcuacionesGuardar).setOnClickListener(this);

        ArrayList<Ecuacion> datos = new ArrayList();
        for(int i = 0; i<3; i++)
            for(int j = 0; j<3+1; j++)
                datos.add(new Ecuacion());

        EcuacionesAdapter adapter = new EcuacionesAdapter(this, datos);
        gridView.setAdapter(adapter);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnEcuacionesAceptar:
                try {
                    n = Integer.parseInt(editText.getText().toString());
                }catch(java.lang.NumberFormatException e){
                    n = 3;
                }
                ArrayList<Ecuacion> datos = new ArrayList();
                for(int i = 0; i<n; i++)
                    for(int j = 0; j<n+1; j++)
                        datos.add(new Ecuacion());
                EcuacionesAdapter ecuacionesAdapter = new EcuacionesAdapter(this, datos);
                gridView.setNumColumns(n+1);
                gridView.setAdapter(ecuacionesAdapter);
                break;
            case R.id.btnEcuacionesGuardar:
                gridView.requestFocus();
                EcuacionesAdapter adapter = (EcuacionesAdapter) gridView.getAdapter();
                if(n != 0){
                    double[][] a = new double[n][n];
                    double[] b = new double[n];
                    int posicionAdapter = 0;
                    Ecuacion actual;
                    for(int i=0; i<n; i++){
                        for(int j=0; j<n; j++){
                            actual = adapter.getEcuacion(posicionAdapter);
                            a[i][j] = actual.coeficiente;
                            posicionAdapter++;
                        }
                        actual = adapter.getEcuacion(posicionAdapter);
                        b[i] = actual.coeficiente;
                        posicionAdapter++;
                    }
                    EstadoEcuaciones.solicitarEstado().setEcuaciones(a,b);
                    finish();
                }
        }
    }
}
