package com.eafit.numericalanalysis.actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.MenuSistemasEcuaciones;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.MenuUnaVariable;

import com.eafit.numericalanalysis.R;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.btnUnaVariable).setOnClickListener(this);
        findViewById(R.id.btnSistemasEcuaciones).setOnClickListener(this);
        findViewById(R.id.btnSalir).setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnUnaVariable:
                ecuacionesUnaVariable(view);
                break;
            case R.id.btnSistemasEcuaciones:
                sistemasEcuaciones(view);
                break;
            case R.id.btnSalir:
                salir(view);
                break;
        }
    }

    public void ecuacionesUnaVariable(View v){
        Intent nuevaActividad = new Intent(this, MenuUnaVariable.class);
        startActivity(nuevaActividad);
    }

    public void sistemasEcuaciones(View v){
        Intent nuevaActividad = new Intent(this, MenuSistemasEcuaciones.class);
        startActivity(nuevaActividad);
    }

    public void salir(View v){
        this.finish();
    }
}
