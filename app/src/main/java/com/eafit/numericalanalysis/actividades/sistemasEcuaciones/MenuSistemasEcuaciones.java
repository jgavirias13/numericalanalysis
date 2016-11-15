package com.eafit.numericalanalysis.actividades.sistemasEcuaciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.ingresoEcuaciones.IngresoEcuaciones;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosEliminacionGaussiana.MenuEliminacionGussiana;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosFactorizacion.MenuFactorizacion;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosIterativos.MenuIterativos;

public class MenuSistemasEcuaciones extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_sistemas_ecuaciones);

        findViewById(R.id.btnIngresoEcuaciones).setOnClickListener(this);
        findViewById(R.id.btnGaussiana).setOnClickListener(this);
        findViewById(R.id.btnFactorizacion).setOnClickListener(this);
        findViewById(R.id.btnIterativos).setOnClickListener(this);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnIngresoEcuaciones:
                ingresoEcuaciones();
                break;
            case R.id.btnGaussiana:
                gaussiana();
                break;
            case R.id.btnFactorizacion:
                factorizacion();
                break;
            case R.id.btnIterativos:
                iterativos();
                break;
        }
    }

    private void ingresoEcuaciones(){
        Intent actividad = new Intent(this, IngresoEcuaciones.class);
        startActivity(actividad);
    }

    private void gaussiana(){
        Intent actividad = new Intent(this, MenuEliminacionGussiana.class);
        startActivity(actividad);
    }

    private void factorizacion(){
        Intent actividad = new Intent(this, MenuFactorizacion.class);
        startActivity(actividad);
    }

    private void iterativos(){
        Intent actividad = new Intent(this, MenuIterativos.class);
        startActivity(actividad);
    }
}
