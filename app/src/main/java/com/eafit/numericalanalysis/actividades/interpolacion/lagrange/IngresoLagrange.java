package com.eafit.numericalanalysis.actividades.interpolacion.lagrange;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.interpolacion.ingresoPuntos.EstadoPuntos;
import com.eafit.numericalanalysis.estructuras.SalidaNewtonInterpolacion;
import com.eafit.numericalanalysis.metodos.interpolacion.Lagrange;
import com.eafit.numericalanalysis.metodos.interpolacion.LagrangeCalculo;
import com.eafit.numericalanalysis.metodos.interpolacion.Newton;
import com.eafit.numericalanalysis.util.Help;

public class IngresoLagrange extends AppCompatActivity implements View.OnClickListener{

    private EditText edtPunto;
    private TextView txtCalculo;
    private double[] x;
    private double[] y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_lagrange);
        TextView txtRespuesta = (TextView) findViewById(R.id.txtRespuesta);
        edtPunto = (EditText) findViewById(R.id.edtPunto);
        txtCalculo = (TextView) findViewById(R.id.txtEvaluar);
        EstadoPuntos estado = EstadoPuntos.solicitarEstado();
        findViewById(R.id.btnEvaluar).setOnClickListener(this);
        findViewById(R.id.btnHelp).setOnClickListener(this);


        x = new double[estado.getnPuntos()];
        y = new double[estado.getnPuntos()];
        for(int i=0; i<estado.getnPuntos(); i++){
            x[i] = estado.getPuntos()[i].getX();
            y[i] = estado.getPuntos()[i].getY();
        }
        String salida[] = Lagrange.evaluar(x);
        String polinomio = "";
        for(int i=0; i<salida.length; i++){
            polinomio += String.format("(%s)*%.14f",salida[i],y[i]);
            if(i<salida.length-1)
                System.out.print(" + ");
        }
        txtRespuesta.setText(polinomio);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnEvaluar:
                String valor = edtPunto.getText().toString();
                if(valor.isEmpty()){
                    Resources res = getResources();
                    error(res.getString(R.string.error_punto));
                }else{
                    double xValor = Double.parseDouble(valor);
                    double respuesta = LagrangeCalculo.evaluar(x,y,xValor);
                    txtCalculo.setText(String.format("y = %.14f",respuesta));
                }
                break;
            case R.id.btnHelp:
                Intent help = new Intent(this, Help.class);
                help.putExtra("id",R.string.help_lagrange);
                startActivity(help);
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
