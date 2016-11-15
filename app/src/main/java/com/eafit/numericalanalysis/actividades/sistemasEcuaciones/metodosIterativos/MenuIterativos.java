package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosIterativos;

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
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosIterativos.gaussSeidel.IngresoGaussSeidel;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosIterativos.jacobi.IngresoJacobi;

public class MenuIterativos extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_iterativos);
        findViewById(R.id.btnJacobi).setOnClickListener(this);
        findViewById(R.id.btnGaussSeidel).setOnClickListener(this);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnGaussSeidel:
                if(EstadoEcuaciones.solicitarEstado().getA() == null){
                    Resources res = getResources();
                    String mensaje = res.getString(R.string.error_sistema_no_soluble);
                    error(mensaje);
                }else {
                    Intent actividad = new Intent(this, IngresoGaussSeidel.class);
                    startActivity(actividad);
                }
                break;
            case R.id.btnJacobi:
                if(EstadoEcuaciones.solicitarEstado().getA() == null){
                    Resources res = getResources();
                    String mensaje = res.getString(R.string.error_sistema_no_soluble);
                    error(mensaje);
                }else {
                    Intent jacobi = new Intent(this, IngresoJacobi.class);
                    startActivity(jacobi);
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
