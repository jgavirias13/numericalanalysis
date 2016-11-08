package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos;

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
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.EstadoFunciones;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.IngresoFunciones;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.newton.NewtonIngreso;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.puntoFijo.PuntoFijoIngreso;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.raicesMultiples.RaicesMultiplesIngreso;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.secante.SecanteIngreso;
import com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable.PuntoFijo;

public class MenuMetodosAbiertos extends AppCompatActivity implements View.OnClickListener{

    private Button btnPuntoFijo;
    private Button btnNewton;
    private Button btnSecante;
    private Button btnRaicesMultiples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodos_abiertos);

        this.btnPuntoFijo = (Button) findViewById(R.id.btnPuntoFijo);
        this.btnNewton = (Button) findViewById(R.id.btnNewton);
        this.btnSecante = (Button) findViewById(R.id.btnSecante);
        this.btnRaicesMultiples = (Button) findViewById(R.id.btnRaicesMultiples);

        btnPuntoFijo.setOnClickListener(this);
        btnNewton.setOnClickListener(this);
        btnSecante.setOnClickListener(this);
        btnRaicesMultiples.setOnClickListener(this);
    }

    public void onClick(View view){
        Resources res = getResources();
        switch(view.getId()){
            case R.id.btnPuntoFijo:
                if(EstadoFunciones.solicitarEstado().getFuncion_f().isEmpty() ||
                        EstadoFunciones.solicitarEstado().getFuncion_g().isEmpty()){
                    error(res.getString(R.string.error_punto_fijo));
                }else{
                    Intent nuevaActividad = new Intent(this, PuntoFijoIngreso.class);
                    startActivity(nuevaActividad);
                }
                break;
            case R.id.btnNewton:
                if(EstadoFunciones.solicitarEstado().getFuncion_f().isEmpty() ||
                        EstadoFunciones.solicitarEstado().getDerivada_f().isEmpty()){
                    error(res.getString(R.string.error_newton));
                }else{
                    Intent nuevaActivity = new Intent(this, NewtonIngreso.class);
                    startActivity(nuevaActivity);
                }
                break;
            case R.id.btnSecante:
                if(EstadoFunciones.solicitarEstado().getFuncion_f().isEmpty()){
                    error(res.getString(R.string.error_secante));
                }else{
                    Intent nuevaActivity = new Intent(this, SecanteIngreso.class);
                    startActivity(nuevaActivity);
                }
                break;
            case R.id.btnRaicesMultiples:
                if(EstadoFunciones.solicitarEstado().getFuncion_f().isEmpty() ||
                        EstadoFunciones.solicitarEstado().getDerivada_f().isEmpty() ||
                        EstadoFunciones.solicitarEstado().getDerivada2_f().isEmpty()){
                    error(res.getString(R.string.error_raices_multiples));
                }else{
                    Intent nuevaActivity = new Intent(this, RaicesMultiplesIngreso.class);
                    startActivity(nuevaActivity);
                }
                break;
        }
    }

    private void error(String mensaje){
        final Dialog dialog = new Dialog(this, R.style.Theme_Dialog_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.error_funcion);

        TextView txtMensaje = (TextView) dialog.findViewById(R.id.txtErrorFuncion);
        txtMensaje.setText(mensaje);
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
