package com.eafit.numericalanalysis.actividades.derivacion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.derivacion.cincoPuntos.IngresoCincoPuntos;
import com.eafit.numericalanalysis.actividades.derivacion.dosPuntos.IngresoDosPuntos;
import com.eafit.numericalanalysis.actividades.derivacion.tresPuntos.IngresoTresPuntos;

public class MenuDerivacion extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_derivacion);
        findViewById(R.id.btnDosPuntos).setOnClickListener(this);
        findViewById(R.id.btnTresPuntos).setOnClickListener(this);
        findViewById(R.id.btnCincoPuntos).setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnDosPuntos:
                Intent dosPuntos = new Intent(this, IngresoDosPuntos.class);
                startActivity(dosPuntos);
                break;
            case R.id.btnTresPuntos:
                Intent tresPuntos = new Intent(this, IngresoTresPuntos.class);
                startActivity(tresPuntos);
                break;
            case R.id.btnCincoPuntos:
                Intent cincoPuntos = new Intent(this, IngresoCincoPuntos.class);
                startActivity(cincoPuntos);
                break;
        }
    }
}
