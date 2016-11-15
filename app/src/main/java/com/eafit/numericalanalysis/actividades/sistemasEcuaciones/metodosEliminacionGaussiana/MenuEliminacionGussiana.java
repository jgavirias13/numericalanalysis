package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosEliminacionGaussiana;

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
                if(EstadoEcuaciones.solicitarEstado().getA() == null){
                    Resources res = getResources();
                    String mensaje = res.getString(R.string.error_matriz);
                    error(mensaje);
                }else {
                    nuevaActivity = new Intent(this, IngresoGaussSimple.class);
                    startActivity(nuevaActivity);
                }
                break;
            case R.id.btnPivoteoParcial:
                if(EstadoEcuaciones.solicitarEstado().getA() == null){
                    Resources res = getResources();
                    String mensaje = res.getString(R.string.error_sistema_no_soluble);
                    error(mensaje);
                }else {
                    nuevaActivity = new Intent(this, IngresoPivoteoParcial.class);
                    startActivity(nuevaActivity);
                    break;
                }
            case R.id.btnPivoteoTotal:
                if(EstadoEcuaciones.solicitarEstado().getA() == null){
                    Resources res = getResources();
                    String mensaje = res.getString(R.string.error_sistema_no_soluble);
                    error(mensaje);
                }else {
                    nuevaActivity = new Intent(this, IngresoPivoteoTotal.class);
                    startActivity(nuevaActivity);
                    break;
                }
            case R.id.btnPivoteoEscalonado:
                if(EstadoEcuaciones.solicitarEstado().getA() == null){
                    Resources res = getResources();
                    String mensaje = res.getString(R.string.error_sistema_no_soluble);
                    error(mensaje);
                }else {
                    nuevaActivity = new Intent(this, IngresoPivoteoEscalonado.class);
                    startActivity(nuevaActivity);
                    break;
                }
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
