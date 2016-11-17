package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.valoresIniciales;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.EstadoFunciones;
import com.eafit.numericalanalysis.estructuras.SalidaBusquedasIncrementales;
import com.eafit.numericalanalysis.excepciones.ExcepcionDelta;
import com.eafit.numericalanalysis.excepciones.ExcepcionEvaluacion;
import com.eafit.numericalanalysis.excepciones.ExcepcionFinIntervalo;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionParser;
import com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable.BusquedasIncrementales;
import com.eafit.numericalanalysis.util.Comunicacion;
import com.eafit.numericalanalysis.util.Help;
import com.eafit.numericalanalysis.util.Intervalo;
import com.eafit.numericalanalysis.util.Parser;

public class BusquedaIncremental extends AppCompatActivity implements View.OnClickListener{

    private EditText edtX0;
    private EditText edtIteraciones;
    private EditText edtDelta;
    private Button btnCalcular;
    private Button btnProceso;
    private LinearLayout layoutBusquedas;
    private TextView txtSolucion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_incremental);
        findViewById(R.id.btnHelp).setOnClickListener(this);
        this.edtDelta = (EditText) findViewById(R.id.edtDelta);
        this.edtIteraciones = (EditText) findViewById(R.id.edtIteraciones);
        this.edtX0 = (EditText) findViewById(R.id.edtX0);
        this.btnCalcular = (Button) findViewById(R.id.btnCalcularBusquedas);
        this.btnProceso = (Button) findViewById(R.id.btnProcesoBusquedas);
        this.layoutBusquedas = (LinearLayout) findViewById(R.id.layoutBusquedas);
        this.txtSolucion = (TextView) findViewById(R.id.txtRespuestaBusquedas);

        btnCalcular.setOnClickListener(this);
        btnProceso.setOnClickListener(this);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnCalcularBusquedas:
                realizarBusqueda();
                break;
            case R.id.btnProcesoBusquedas:
                Intent nuevaActividad = new Intent(this, ProcesoBusqueda.class);
                startActivity(nuevaActividad);
                break;
            case R.id.btnHelp:
                Intent help = new Intent(this, Help.class);
                help.putExtra("id",R.string.help_busquedas);
                startActivity(help);
                break;
        }
    }

    private void realizarBusqueda(){
        Resources res = getResources();
        String edtXOs = this.edtX0.getText().toString();
        String edtDeltas = this.edtDelta.getText().toString();
        String edtIteracioness = this.edtIteraciones.getText().toString();
        if(edtXOs.isEmpty() || edtDeltas.isEmpty() || edtIteracioness.isEmpty())
            error(res.getString(R.string.error_datos));
        else {
            double x0 = Double.parseDouble(edtXOs);
            double delta = Double.parseDouble(edtDeltas);
            int iteraciones = Integer.parseInt(edtIteracioness);
            String funcion = EstadoFunciones.solicitarEstado().getFuncion_f();
            Parser parser = new Parser(funcion);
            try {
                SalidaBusquedasIncrementales salida = BusquedasIncrementales.evaluar(x0, delta, iteraciones, parser);
                Comunicacion.send(salida);
                Intervalo<Double, Double> respuesta = salida.obtenerRaiz();
                layoutBusquedas.setVisibility(View.VISIBLE);
                double x = respuesta.x;
                double y = respuesta.y;
                if (x == y) {
                    txtSolucion.setText(res.getText(R.string.respuesta_busquedas_raiz) + " " + String.format("%.2g", respuesta.x));
                } else {
                    txtSolucion.setText(respuesta.toString());
                }
            } catch (ExcepcionFinIntervalo excepcionFinIntervalo) {
                error(res.getString(R.string.error_intervalo));
            } catch (ExcepcionDelta excepcionDelta) {
                error(res.getString(R.string.error_delta));
            } catch (ExcepcionIteraciones excepcionIteraciones) {
                error(res.getString(R.string.error_iteraciones));
            } catch (ExcepcionParser excepcionParser) {
                error(res.getString(R.string.error_parser));
            } catch (ExcepcionEvaluacion excepcionEvaluacion) {
                error(res.getString(R.string.error_evaluacion));
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
