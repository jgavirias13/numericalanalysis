package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.metodosAbiertos.secante;

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
import com.eafit.numericalanalysis.estructuras.SalidaSecante;
import com.eafit.numericalanalysis.excepciones.ExcepcionEvaluacion;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionParser;
import com.eafit.numericalanalysis.excepciones.ExcepcionRaicesMultiples;
import com.eafit.numericalanalysis.excepciones.ExcepcionRaiz;
import com.eafit.numericalanalysis.excepciones.ExcepcionTolerancia;
import com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable.Secante;
import com.eafit.numericalanalysis.util.Comunicacion;
import com.eafit.numericalanalysis.util.Help;
import com.eafit.numericalanalysis.util.Intervalo;
import com.eafit.numericalanalysis.util.Parser;

public class SecanteIngreso extends AppCompatActivity implements View.OnClickListener{

    private RadioButton rbtnErrorAbsoluto;
    private RadioButton rbtnErrorRelativo;
    private EditText edtX0;
    private EditText edtX1;
    private EditText edtIteraciones;
    private EditText edtTolerancia;
    private Button btnCalcular;
    private LinearLayout layout;
    private TextView txtSolucion;
    private Button btnProceso;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secante_ingreso);
        findViewById(R.id.btnHelp).setOnClickListener(this);

        rbtnErrorAbsoluto = (RadioButton) findViewById(R.id.rbtnSecanteAbsoluto);
        rbtnErrorRelativo = (RadioButton) findViewById(R.id.rbtnSecanteRelativo);
        edtX0 = (EditText) findViewById(R.id.edtSecanteX0);
        edtX1 = (EditText) findViewById(R.id.edtSecanteX1);
        edtIteraciones = (EditText) findViewById(R.id.edtSecanteIteraciones);
        edtTolerancia = (EditText) findViewById(R.id.edtSecanteTolerancia);
        btnCalcular = (Button) findViewById(R.id.btnSecanteCalcular);
        layout = (LinearLayout) findViewById(R.id.layoutSecante);
        txtSolucion = (TextView) findViewById(R.id.txtRespuestaSecante);
        btnProceso = (Button) findViewById(R.id.btnSecanteProceso);

        btnCalcular.setOnClickListener(this);
        btnProceso.setOnClickListener(this);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnSecanteCalcular:
                realizarSecante();
                break;
            case R.id.btnSecanteProceso:
                realizarProceso();
                break;
            case R.id.btnHelp:
                Intent help = new Intent(this, Help.class);
                help.putExtra("id",R.string.help_secante);
                startActivity(help);
                break;
        }
    }

    public void realizarSecante(){
        double x0, x1, tol;
        Resources res = getResources();
        int iter, t_error;
        String funcionF, x0s, x1s, tols, iters;

        x0s = edtX0.getText().toString();
        x1s = edtX1.getText().toString();
        tols = edtTolerancia.getText().toString();
        iters = edtIteraciones.getText().toString();

        if(x0s.isEmpty() || x1s.isEmpty() || tols.isEmpty() || iters.isEmpty())
            error(res.getString(R.string.error_datos));
        else {
            x0 = Double.parseDouble(x0s);
            x1 = Double.parseDouble(x1s);
            tol = Double.parseDouble(tols);
            iter = Integer.parseInt(iters);
            funcionF = EstadoFunciones.solicitarEstado().getFuncion_f();
            if (rbtnErrorRelativo.isChecked()) t_error = 0;  //0 -> Relativo
            else t_error = 1;   //1 -> Absoluto
            Parser funcion_f = new Parser(funcionF);

            try {
                SalidaSecante resultadoM = Secante.evaluar(x0,x1,tol,iter,funcion_f,t_error);
                Comunicacion.send(resultadoM);
                Intervalo<Double, Double> raiz = resultadoM.obtenerRaiz();
                layout.setVisibility(View.VISIBLE);
                double valor = raiz.x;
                double errorRaiz = raiz.y;
                txtSolucion.setText(String.format("%.3g " + res.getString(R.string.respueta_newton) +
                        " %.2g", valor, errorRaiz));
            } catch (ExcepcionTolerancia excepcionTolerancia) {
                error(res.getString(R.string.error_tolerancia));
            } catch (ExcepcionIteraciones excepcionIteraciones) {
                error(res.getString(R.string.error_iteraciones));
            } catch (ExcepcionParser excepcionParser) {
                error(res.getString(R.string.error_parser));
            } catch (ExcepcionEvaluacion excepcionEvaluacion) {
                error(res.getString(R.string.error_evaluacion));
            } catch (ExcepcionRaicesMultiples excepcionRaicesMultiples) {
                error(res.getString(R.string.error_raices_multiples));
            } catch (ExcepcionRaiz excepcionRaiz) {
                error(res.getString(R.string.error_raiz));
            }
        }
    }

    public void realizarProceso(){
        Intent nuevaAvtivity = new Intent(this, SecanteProceso.class);
        startActivity(nuevaAvtivity);
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
