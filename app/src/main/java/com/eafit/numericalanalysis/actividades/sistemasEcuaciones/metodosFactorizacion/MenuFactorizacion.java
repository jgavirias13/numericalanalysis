package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosFactorizacion;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.ingresoEcuaciones.EstadoEcuaciones;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosFactorizacion.factorizacionCholesky.IngresoFactorizacionCholesky;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosFactorizacion.factorizacionCrout.IngresoFactorizacionCrout;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosFactorizacion.factorizacionDoolittle.IngresoFactorizacionDoolittle;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosFactorizacion.factorizacionPivoteo.IngresoFactorizacionPivoteo;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosFactorizacion.factorizacionSimple.IngresoFactorizacionSimple;

public class MenuFactorizacion extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_factorizacion);

        findViewById(R.id.btnFactorizacionCholesky).setOnClickListener(this);
        findViewById(R.id.btnFactorizacionCrout).setOnClickListener(this);
        findViewById(R.id.btnFactorizacionDoolittle).setOnClickListener(this);
        findViewById(R.id.btnFactorizacionSimple).setOnClickListener(this);
        findViewById(R.id.btnFactorizacionPivoteo).setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnFactorizacionCholesky:
                if(EstadoEcuaciones.solicitarEstado().getA() == null){
                    Resources res = getResources();
                    String mensaje = res.getString(R.string.error_matriz);
                    error(mensaje);
                }else {
                    Intent actividad = new Intent(this, IngresoFactorizacionCholesky.class);
                    startActivity(actividad);
                }
                break;
            case R.id.btnFactorizacionCrout:
                if(EstadoEcuaciones.solicitarEstado().getA() == null){
                    Resources res = getResources();
                    String mensaje = res.getString(R.string.error_matriz);
                    error(mensaje);
                }else {
                    Intent actividad = new Intent(this, IngresoFactorizacionCrout.class);
                    startActivity(actividad);
                }
                break;
            case R.id.btnFactorizacionDoolittle:
                if(EstadoEcuaciones.solicitarEstado().getA() == null){
                    Resources res = getResources();
                    String mensaje = res.getString(R.string.error_matriz);
                    error(mensaje);
                }else {
                    Intent actividad = new Intent(this, IngresoFactorizacionDoolittle.class);
                    startActivity(actividad);
                }
                break;
            case R.id.btnFactorizacionSimple:
                if(EstadoEcuaciones.solicitarEstado().getA() == null){
                    Resources res = getResources();
                    String mensaje = res.getString(R.string.error_matriz);
                    error(mensaje);
                }else {
                    Intent actividad = new Intent(this, IngresoFactorizacionSimple.class);
                    startActivity(actividad);
                }
                break;
            case R.id.btnFactorizacionPivoteo:
                if(EstadoEcuaciones.solicitarEstado().getA() == null){
                    Resources res = getResources();
                    String mensaje = res.getString(R.string.error_matriz);
                    error(mensaje);
                }else {
                    Intent actividad = new Intent(this, IngresoFactorizacionPivoteo.class);
                    startActivity(actividad);
                }
                break;
        }
    }

    private void error(String mensaje){
        final Dialog dialog = new Dialog(this, R.style.Theme_Dialog_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.error_info);

        Button cancelar = (Button) dialog.findViewById(R.id.btnCancelarInfo);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        TextView texto = (TextView) dialog.findViewById(R.id.texto_error);
        texto.setText(mensaje);

        dialog.show();
    }
}
