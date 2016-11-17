package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosFactorizacion.factorizacionCrout;

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
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosFactorizacion.factorizacionSimple.ProcesoFactorizacionSimple;
import com.eafit.numericalanalysis.estructuras.SalidaFactorizacionCrout;
import com.eafit.numericalanalysis.estructuras.SalidaFactorizacionGaussina;
import com.eafit.numericalanalysis.excepciones.ExcepcionNoSoluble;
import com.eafit.numericalanalysis.metodos.sistemasEcuaciones.Crout;
import com.eafit.numericalanalysis.metodos.sistemasEcuaciones.FactorizacionGaussiana;
import com.eafit.numericalanalysis.util.Comunicacion;
import com.eafit.numericalanalysis.util.Help;

public class IngresoFactorizacionCrout extends AppCompatActivity implements View.OnClickListener{

    private TextView txtMatriz;
    private TextView txtTitulo;
    private Button btnCalcular;
    private double[][] matrizAumentada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_factorizacion_crout);
        txtMatriz = (TextView) findViewById(R.id.txtMatriz);
        txtTitulo = (TextView) findViewById(R.id.txtGaussTitulo);
        btnCalcular = (Button) findViewById(R.id.btnCalcularGauss);
        btnCalcular.setOnClickListener(this);
        findViewById(R.id.btnHelp).setOnClickListener(this);

        EstadoEcuaciones estado = EstadoEcuaciones.solicitarEstado();
        double[][] a = estado.getA();
        double[] b = estado.getB();
        matrizAumentada = new double[a.length][a.length+1];
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a.length; j++){
                matrizAumentada[i][j] = a[i][j];
            }
            matrizAumentada[i][a.length] = b[i];
        }

        String matriz = "";
        for(int i=0; i<matrizAumentada.length; i++){
            for(int j=0; j<matrizAumentada[0].length; j++){
                matriz += matrizAumentada[i][j] + "  ";
            }
            matriz += "\n\n";
        }

        txtMatriz.setText(matriz);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnCalcularGauss:
                try {
                    EstadoEcuaciones estado = EstadoEcuaciones.solicitarEstado();
                    final SalidaFactorizacionCrout resultado = Crout.evaluar(estado.getA(),estado.getB());
                    String salida = "";
                    double[] x = resultado.getX();
                    for(int i=0; i<x.length; i++){
                        salida += "x"+i+" = "+x[i]+"\n";
                    }
                    Resources res = getResources();
                    txtTitulo.setText(res.getString(R.string.resultado));
                    txtMatriz.setText(salida);
                    btnCalcular.setText(res.getText(R.string.proceso));
                    btnCalcular.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent proceso = new Intent(v.getContext(), ProcesoFactorizacionCrout.class);
                            Comunicacion.send(resultado);
                            startActivity(proceso);
                        }
                    });
                } catch (ExcepcionNoSoluble excepcionNoSoluble) {
                    Resources res = getResources();
                    error(res.getString(R.string.error_sistema_no_soluble));
                }
                break;
            case R.id.btnHelp:
                Intent help = new Intent(this, Help.class);
                help.putExtra("id",R.string.help_crout);
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
