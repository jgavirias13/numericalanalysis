package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosEliminacionGaussiana;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosEliminacionGaussiana.gaussSimple.IngresoGaussSimple;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosEliminacionGaussiana.pivoteoEscalonado.IngresoPivoteoEscalonado;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosEliminacionGaussiana.pivoteoParcial.IngresoPivoteoParcial;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosEliminacionGaussiana.pivoteoTotal.IngresoPivoteoTotal;

public class MenuEliminacionGussiana extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_eliminacion_gussiana);

        findViewById(R.id.btnGaussSimple).setOnClickListener(this);
        findViewById(R.id.btnPivoteoParcial).setOnClickListener(this);
        findViewById(R.id.btnPivoteoTotal).setOnClickListener(this);
        findViewById(R.id.btnPivoteoEscalonado).setOnClickListener(this);
    }

    public void onClick(View view){
        Intent nuevaActivity;
        switch(view.getId()){
            case R.id.btnGaussSimple:
                nuevaActivity = new Intent(this, IngresoGaussSimple.class);
                startActivity(nuevaActivity);
                break;
            case R.id.btnPivoteoParcial:
                nuevaActivity = new Intent(this, IngresoPivoteoParcial.class);
                startActivity(nuevaActivity);
                break;
            case R.id.btnPivoteoTotal:
                nuevaActivity = new Intent(this, IngresoPivoteoTotal.class);
                startActivity(nuevaActivity);
                break;
            case R.id.btnPivoteoEscalonado:
                nuevaActivity = new Intent(this, IngresoPivoteoEscalonado.class);
                startActivity(nuevaActivity);
                break;
        }
    }
}
