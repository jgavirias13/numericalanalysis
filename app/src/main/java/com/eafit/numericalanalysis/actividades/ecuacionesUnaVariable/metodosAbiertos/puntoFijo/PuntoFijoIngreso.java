package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.puntoFijo;

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
import com.eafit.numericalanalysis.estructuras.SalidaPuntoFijo;
import com.eafit.numericalanalysis.excepciones.ExcepcionEvaluacion;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionParser;
import com.eafit.numericalanalysis.excepciones.ExcepcionRaiz;
import com.eafit.numericalanalysis.excepciones.ExcepcionTolerancia;
import com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable.PuntoFijo;
import com.eafit.numericalanalysis.util.Comunicacion;
import com.eafit.numericalanalysis.util.Intervalo;
import com.eafit.numericalanalysis.util.Parser;

public class PuntoFijoIngreso extends AppCompatActivity implements View.OnClickListener{

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
        setContentView(R.layout.activity_punto_fijo_ingreso);

        rbtnErrorAbsoluto = (RadioButton) findViewById(R.id.rbtnPuntoFijoAbsoluto);
        rbtnErrorRelativo = (RadioButton) findViewById(R.id.rbtnPuntoFijoRelativo);
        edtX0 = (EditText) findViewById(R.id.edtPuntoFijoX0);
        edtIteraciones = (EditText) findViewById(R.id.edtPuntoFijoIteraciones);
        edtTolerancia = (EditText) findViewById(R.id.edtPuntoFijoTolerancia);
        btnCalcular = (Button) findViewById(R.id.btnPuntoFijoCalcular);
        layout = (LinearLayout) findViewById(R.id.layoutPuntoFijo);
        txtSolucion = (TextView) findViewById(R.id.txtRespuestaPuntoFijo);
        btnProceso = (Button) findViewById(R.id.btnPuntoFijoProceso);

        btnCalcular.setOnClickListener(this);
        btnProceso.setOnClickListener(this);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnPuntoFijoCalcular:
                realizarPuntoFijo();
                break;
            case R.id.btnPuntoFijoProceso:
                realizarProceso();
                break;
        }
    }

    private void realizarPuntoFijo(){
        double x0, tol;
        Resources res = getResources();
        int iter, t_error;
        String funcionF, x0s, tols, iters, funcionG;

        x0s = edtX0.getText().toString();
        tols = edtTolerancia.getText().toString();
        iters = edtIteraciones.getText().toString();

        if(x0s.isEmpty() || tols.isEmpty() || iters.isEmpty())
            error(res.getString(R.string.error_datos));
        else{
            x0 = Double.parseDouble(x0s);
            tol = Double.parseDouble(tols);
            iter = Integer.parseInt(iters);
            funcionF = EstadoFunciones.solicitarEstado().getFuncion_f();
            funcionG = EstadoFunciones.solicitarEstado().getFuncion_g();
            if (rbtnErrorRelativo.isChecked()) t_error = 0;  //0 -> Relativo
            else t_error = 1;   //1 -> Absoluto
            Parser funcion_f = new Parser(funcionF);
            Parser funcion_g = new Parser(funcionG);

            try {
                SalidaPuntoFijo resultadoM = PuntoFijo.evaluar(x0,tol,iter,funcion_f,funcion_g,t_error);
                Comunicacion.send(resultadoM);
                Intervalo<Double, Double> raiz = resultadoM.obtenerRaiz();
                layout.setVisibility(View.VISIBLE);
                double valor = raiz.x;
                double errorRaiz = raiz.y;
                txtSolucion.setText(String.format("%.3g " + res.getString(R.string.respuesta_punto_fijo) +
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
        Intent nuevaActivity = new Intent(this, PuntoFijoProceso.class);
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
