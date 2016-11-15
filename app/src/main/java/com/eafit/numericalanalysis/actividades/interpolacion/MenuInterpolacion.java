package com.eafit.numericalanalysis.actividades.interpolacion;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.interpolacion.ingresoPuntos.EstadoPuntos;
import com.eafit.numericalanalysis.actividades.interpolacion.ingresoPuntos.IngresoPuntos;
import com.eafit.numericalanalysis.actividades.interpolacion.lagrange.IngresoLagrange;
import com.eafit.numericalanalysis.actividades.interpolacion.newton.IngresoNewton;

public class MenuInterpolacion extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_interpolacion);

        findViewById(R.id.btnNewton).setOnClickListener(this);
        findViewById(R.id.btnIngresoPuntos).setOnClickListener(this);
        findViewById(R.id.btnLagrange).setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnNewton:
                EstadoPuntos estadoPuntos = EstadoPuntos.solicitarEstado();
                if(estadoPuntos.getnPuntos() == 0){
                    error("Error, debe ingresar los puntos a interpolar");
                }else{
                    Intent newton = new Intent(this, IngresoNewton.class);
                    startActivity(newton);
                }
                break;
            case R.id.btnLagrange:
                EstadoPuntos estadoPuntosL = EstadoPuntos.solicitarEstado();
                if(estadoPuntosL.getnPuntos() == 0){
                    error("Error, debe ingresar los puntos a interpolar");
                }else{
                    Intent lagrange = new Intent(this, IngresoLagrange.class);
                    startActivity(lagrange);
                }
                break;
            case R.id.btnIngresoPuntos:
                Intent ingresoPuntos = new Intent(this, IngresoPuntos.class);
                startActivity(ingresoPuntos);
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
