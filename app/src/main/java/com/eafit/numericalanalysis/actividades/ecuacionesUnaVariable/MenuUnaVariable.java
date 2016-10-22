package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.MenuMetodosAbiertos;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosIntervalos.MenuMetodosIntervalos;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.valoresIniciales.ValoresIniciales;

public class MenuUnaVariable extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_una_variable);

        findViewById(R.id.btnIngresoFunciones).setOnClickListener(this);
        findViewById(R.id.btnMetodosAbiertos).setOnClickListener(this);
        findViewById(R.id.btnMetodosIntervalos).setOnClickListener(this);
        findViewById(R.id.btnValoresIniciales).setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnIngresoFunciones:
                ingresoFunciones(view);
                break;
            case R.id.btnMetodosIntervalos:
                metodosIntervalos(view);
                break;
            case R.id.btnValoresIniciales:
                valoresIniciales(view);
                break;
            case R.id.btnMetodosAbiertos:
                metodosAbiertos(view);
                break;
        }
    }

    public void ingresoFunciones(View v){
        Intent nuevaActividad = new Intent(this, IngresoFunciones.class);
        startActivity(nuevaActividad);
    }

    public void valoresIniciales(View v){
        Intent nuevaActividad = new Intent(this, ValoresIniciales.class);
        startActivity(nuevaActividad);
    }

    public void metodosAbiertos(View v){
        Intent nuevaActividad = new Intent(this, MenuMetodosAbiertos.class);
        startActivity(nuevaActividad);
    }

    public void metodosIntervalos(View v){
        Intent nuevaActividad = new Intent(this, MenuMetodosIntervalos.class);
        startActivity(nuevaActividad);
    }
}
