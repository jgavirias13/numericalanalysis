package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.raicesMultiples;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.EstadoFunciones;
import com.eafit.numericalanalysis.estructuras.SalidaRaicesMultiples;
import com.eafit.numericalanalysis.excepciones.ExcepcionEvaluacion;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionParser;
import com.eafit.numericalanalysis.excepciones.ExcepcionRaiz;
import com.eafit.numericalanalysis.excepciones.ExcepcionTolerancia;
import com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable.RaicesMultiples;
import com.eafit.numericalanalysis.util.Comunicacion;
import com.eafit.numericalanalysis.util.Help;
import com.eafit.numericalanalysis.util.Intervalo;
import com.eafit.numericalanalysis.util.Parser;

public class RaicesMultiplesIngreso extends AppCompatActivity implements View.OnClickListener{

    private RadioButton rbtnErrorAbsoluto;
    private RadioButton rbtnErrorRelativo;
    private EditText edtX0;
    private EditText edtIteraciones;
    private EditText edtTolerancia;
    private Button btnCalcular;
    private LinearLayout layout;
    private TextView txtSolucion;
    private Button btnProceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raices_multiples_ingreso);
        findViewById(R.id.btnHelp).setOnClickListener(this);
        rbtnErrorAbsoluto = (RadioButton) findViewById(R.id.rbtnRaicesMultiplesAbsoluto);
        rbtnErrorRelativo = (RadioButton) findViewById(R.id.rbtnRaicesMultiplesRelativo);
        edtX0 = (EditText) findViewById(R.id.edtRaicesMultiplesX0);
        edtIteraciones = (EditText) findViewById(R.id.edtRaicesMultiplesIteraciones);
        edtTolerancia = (EditText) findViewById(R.id.edtRaicesMultiplesTolerancia);
        btnCalcular = (Button) findViewById(R.id.btnRaicesMultiplesCalcular);
        layout = (LinearLayout) findViewById(R.id.layoutRaicesMultiples);
        txtSolucion = (TextView) findViewById(R.id.txtRespuestaRaicesMultiples);
        btnProceso = (Button) findViewById(R.id.btnRaicesMultiplesProceso);

        btnCalcular.setOnClickListener(this);
        btnProceso.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnRaicesMultiplesCalcular:
                realizarRaicesMultiples();
                break;
            case R.id.btnRaicesMultiplesProceso:
                realizarProceso();
                break;
            case R.id.btnHelp:
                Intent help = new Intent(this, Help.class);
                help.putExtra("id",R.string.help_raices_multiples);
                startActivity(help);
                break;
        }
    }

    private void realizarRaicesMultiples() {
        double x0, tol;
        Resources res = getResources();
        int iter, t_error;
        String funcionF, x0s, tols, iters, funcionD, funcion2D;

        x0s = edtX0.getText().toString();
        tols = edtTolerancia.getText().toString();
        iters = edtIteraciones.getText().toString();

        if (x0s.isEmpty() || tols.isEmpty() || iters.isEmpty())
            error(res.getString(R.string.error_datos));
        else {
            x0 = Double.parseDouble(x0s);
            tol = Double.parseDouble(tols);
            iter = Integer.parseInt(iters);
            funcionF = EstadoFunciones.solicitarEstado().getFuncion_f();
            funcionD = EstadoFunciones.solicitarEstado().getDerivada_f();
            funcion2D = EstadoFunciones.solicitarEstado().getDerivada2_f();

            if (rbtnErrorRelativo.isChecked()) t_error = 0;  //0 -> Relativo
            else t_error = 1;   //1 -> Absoluto
            Parser funcion_f = new Parser(funcionF);
            Parser funcion_d = new Parser(funcionD);
            Parser funcion_2d = new Parser(funcion2D);

            try {
                SalidaRaicesMultiples resultadoM = RaicesMultiples.evaluar(x0,tol,iter,funcion_f,funcion_d,funcion_2d,t_error);
                Comunicacion.send(resultadoM);
                Intervalo<Double, Double> raiz = resultadoM.obtenerRaiz();
                layout.setVisibility(View.VISIBLE);
                double valor = raiz.x;
                double errorRaiz = raiz.y;
                txtSolucion.setText(String.format("%.3g " + res.getString(R.string.respueta_newton) +
                        " %.2g", valor, errorRaiz));
            } catch (ExcepcionIteraciones excepcionIteraciones) {
                error(res.getString(R.string.error_iteraciones));
            } catch (ExcepcionTolerancia excepcionTolerancia) {
                error(res.getString(R.string.error_tolerancia));
            } catch (ExcepcionParser excepcionParser) {
                error(res.getString(R.string.error_parser));
            } catch (ExcepcionEvaluacion excepcionEvaluacion) {
                error(res.getString(R.string.error_evaluacion));
            } catch (ExcepcionRaiz excepcionRaiz) {
                error(res.getString(R.string.error_raiz));
            }
        }
    }

    private void realizarProceso(){
        Intent nuevaActivity = new Intent(this, RaicesMultiplesProceso.class);
        startActivity(nuevaActivity);
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
