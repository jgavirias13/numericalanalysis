package com.eafit.numericalanalysis.actividades.interpolacion.newton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.interpolacion.ingresoPuntos.EstadoPuntos;
import com.eafit.numericalanalysis.estructuras.SalidaNewtonInterpolacion;
import com.eafit.numericalanalysis.metodos.interpolacion.Newton;

public class IngresoNewton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_newton);
        TextView txtRespuesta = (TextView) findViewById(R.id.txtRespuesta);
        TextView txtProceso = (TextView) findViewById(R.id.txtProceso);
        EstadoPuntos estado = EstadoPuntos.solicitarEstado();
        double x[] = new double[estado.getnPuntos()];
        double y[] = new double[estado.getnPuntos()];
        for(int i=0; i<estado.getnPuntos(); i++){
            x[i] = estado.getPuntos()[i].getX();
            y[i] = estado.getPuntos()[i].getY();
        }
        SalidaNewtonInterpolacion salida = Newton.evaluar(x,y);
        String polinomio = "";
        for(int i=0; i<estado.getnPuntos(); i++){
            polinomio += String.format("%.14f",salida.getResultado()[i]);
            for(int j=0; j<i; j++){
                polinomio += String.format("*(x-(%.14f))",x[j]);
            }
            if(i<estado.getnPuntos()-1)
                polinomio+= " + ";
        }
        txtRespuesta.setText(polinomio);
        String proceso = "";
        for(int i=0; i<estado.getnPuntos(); i++){
            for(int j=0; j<estado.getnPuntos(); j++){
                proceso += String.format("%.14f ",salida.getProceso()[i][j]);
            }
            proceso +="\n\n";
        }
        txtProceso.setText(proceso);
    }
}
