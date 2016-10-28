package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosIntervalos;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.EstadoFunciones;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.IngresoFunciones;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosIntervalos.biseccion.BiseccionIngreso;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosIntervalos.reglaFalsa.ReglaFalsaIngreso;

public class MenuMetodosIntervalos extends AppCompatActivity implements View.OnClickListener{

    private Button btnBiseccion;
    private Button btnReglaFalsa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_intervalos);

        btnBiseccion = (Button) findViewById(R.id.btnBiseccion);
        btnReglaFalsa = (Button) findViewById(R.id.btnReglaFalsa);

        btnBiseccion.setOnClickListener(this);
        btnReglaFalsa.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnBiseccion:
                if(!comprobarFunciones()) error();
                else{
                    Intent nuevaActivity = new Intent(this, BiseccionIngreso.class);
                    startActivity(nuevaActivity);
                }
                break;
            case R.id.btnReglaFalsa:
                if(!comprobarFunciones()) error();
                else{
                    Intent nuevaActivity = new Intent(this, ReglaFalsaIngreso.class);
                    startActivity(nuevaActivity);
                }
                break;
        }
    }

    private boolean comprobarFunciones(){
        return (!EstadoFunciones.solicitarEstado().getFuncion_f().isEmpty());
    }

    private void error(){
        final Dialog dialog = new Dialog(this, R.style.Theme_Dialog_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.error_funcion);

        Button cancelar = (Button) dialog.findViewById(R.id.btnCancelarError);
        Button ingresar = (Button) dialog.findViewById(R.id.btnIngresarFuncion);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevaActividad = new Intent(getApplicationContext(), IngresoFunciones.class);
                startActivity(nuevaActividad);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
