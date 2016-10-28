package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosIntervalos.biseccion;

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
import com.eafit.numericalanalysis.estructuras.SalidaBiseccion;
import com.eafit.numericalanalysis.excepciones.ExcepcionEvaluacion;
import com.eafit.numericalanalysis.excepciones.ExcepcionIntervalo;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionParser;
import com.eafit.numericalanalysis.excepciones.ExcepcionRaiz;
import com.eafit.numericalanalysis.excepciones.ExcepcionTolerancia;
import com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable.Biseccion;
import com.eafit.numericalanalysis.util.Comunicacion;
import com.eafit.numericalanalysis.util.Intervalo;
import com.eafit.numericalanalysis.util.Parser;

public class BiseccionIngreso extends AppCompatActivity implements View.OnClickListener{

    private RadioButton rbtnErrorAbsoluto;
    private RadioButton rbtnErrorRelativo;
    private EditText edtXi;
    private EditText edtXs;
    private EditText edtIteraciones;
    private EditText edtTolerancia;
    private Button btnCalcular;
    private LinearLayout layout;
    private TextView txtSolucion;
    private Button btnProceso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biseccion_ingreso);

        rbtnErrorAbsoluto = (RadioButton) findViewById(R.id.rbtnBiseccionAbsoluto);
        rbtnErrorRelativo = (RadioButton) findViewById(R.id.rbtnBiseccionRelativo);
        edtXi = (EditText) findViewById(R.id.edtBiseccionXi);
        edtXs = (EditText) findViewById(R.id.edtBiseccionXs);
        edtIteraciones = (EditText) findViewById(R.id.edtBiseccionIteraciones);
        edtTolerancia = (EditText) findViewById(R.id.edtBiseccionTolerancia);
        btnCalcular = (Button) findViewById(R.id.btnBiseccionCalcular);
        layout = (LinearLayout) findViewById(R.id.layoutBiseccion);
        txtSolucion = (TextView) findViewById(R.id.txtRespuestaBiseccion);
        btnProceso = (Button) findViewById(R.id.btnBiseccionProceso);

        btnCalcular.setOnClickListener(this);
        btnProceso.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnBiseccionCalcular:
                realizarBiseccion();
                break;
            case R.id.btnBiseccionProceso:
                realizarProceso();
                break;
        }
    }

    private void realizarProceso(){
        Intent nuevaActivity = new Intent(this, BiseccionProceso.class);
        startActivity(nuevaActivity);
    }

    private void realizarBiseccion(){
        double xi, xs, tol;
        Resources res = getResources();
        int iter, t_error;
        String funcion, xis, xss, tols, iters;

        xis = edtXi.getText().toString();
        xss = edtXs.getText().toString();
        tols = edtTolerancia.getText().toString();
        iters = edtIteraciones.getText().toString();

        if(xis.isEmpty() || xss.isEmpty() || tols.isEmpty() || iters.isEmpty())
            error(res.getString(R.string.error_datos));
        else {
            xi = Double.parseDouble(xis);
            xs = Double.parseDouble(xss);
            tol = Double.parseDouble(tols);
            iter = Integer.parseInt(iters);
            funcion = EstadoFunciones.solicitarEstado().getFuncion_f();
            if (rbtnErrorRelativo.isChecked()) t_error = 0;  //0 -> Relativo
            else t_error = 1;   //1 -> Absoluto
            Parser funcion_f = new Parser(funcion);

            try {
                SalidaBiseccion resultadoM = Biseccion.evaluar(xi, xs, tol, iter, funcion_f, t_error);
                Comunicacion.send(resultadoM);
                Intervalo<Double, Double> raiz = resultadoM.obtenerRaiz();
                layout.setVisibility(View.VISIBLE);
                double valor = raiz.x;
                double errorRaiz = raiz.y;
                txtSolucion.setText(String.format("%.2g " + res.getString(R.string.respuesta_biseccion) +
                        " %.2g", valor, errorRaiz));
            } catch (ExcepcionTolerancia excepcionTolerancia) {
                error(res.getString(R.string.error_tolerancia));
            } catch (ExcepcionIteraciones excepcionIteraciones) {
                error(res.getString(R.string.error_iteraciones));
            } catch (ExcepcionParser excepcionParser) {
                error(res.getString(R.string.error_parser));
            } catch (ExcepcionEvaluacion excepcionEvaluacion) {
                error(res.getString(R.string.error_evaluacion));
            } catch (ExcepcionIntervalo excepcionIntervalo) {
                error(res.getString(R.string.error_intervalo_malo));
            } catch (ExcepcionRaiz excepcionRaiz) {
                error(res.getString(R.string.error_raiz));
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
