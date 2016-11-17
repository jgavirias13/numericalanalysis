package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.valoresIniciales;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.EstadoFunciones;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.IngresoFunciones;
import com.eafit.numericalanalysis.util.Comunicacion;
import com.eafit.numericalanalysis.util.Graficador;

public class ValoresIniciales extends AppCompatActivity implements View.OnClickListener{

    private Button btnGraficar;
    private Button btnBusquedas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valores_iniciales);

        btnGraficar = (Button) findViewById(R.id.btnGraficar);
        btnBusquedas = (Button) findViewById(R.id.btnBusquedaIncremental);
        btnBusquedas.setOnClickListener(this);
        btnGraficar.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnGraficar:
                if(!comprobarFunciones()) error();
                else{
                    Comunicacion.send(EstadoFunciones.solicitarEstado().getFuncion_f());
                    Intent nuevaActividad = new Intent(this, Graficador.class);
                    startActivity(nuevaActividad);
                }
                break;
            case R.id.btnBusquedaIncremental:
                if(!comprobarFunciones()) error();
                else{
                    Intent nuevaActividad = new Intent(this, BusquedaIncremental.class);
                    startActivity(nuevaActividad);
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
