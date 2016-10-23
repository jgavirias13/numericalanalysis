package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable.valoresIniciales;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
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
        }
    }

    private void realizarBusqueda(){
        double x0 = Double.parseDouble(this.edtX0.getText().toString());
        double delta = Double.parseDouble(this.edtDelta.getText().toString());
        int iteraciones = Integer.parseInt(this.edtIteraciones.getText().toString());
        String funcion = EstadoFunciones.solicitarEstado().getFuncion_f();
        Parser parser = new Parser(funcion);

        try {
            SalidaBusquedasIncrementales salida = BusquedasIncrementales.evaluar(x0,delta,iteraciones, parser);
            Comunicacion.send(salida);
            Intervalo<Double,Double> respuesta = salida.obtenerRaiz();
            layoutBusquedas.setVisibility(View.VISIBLE);
            Resources resources = getResources();
            double x = respuesta.x;
            double y = respuesta.y;
            if(x == y){
                txtSolucion.setText(resources.getText(R.string.respuesta_busquedas_raiz)+" "+respuesta.x);
            }else{
                txtSolucion.setText(respuesta.toString());
            }
        } catch (ExcepcionFinIntervalo excepcionFinIntervalo) {
            excepcionFinIntervalo.printStackTrace();
        } catch (ExcepcionDelta excepcionDelta) {
            excepcionDelta.printStackTrace();
        } catch (ExcepcionIteraciones excepcionIteraciones) {
            excepcionIteraciones.printStackTrace();
        } catch (ExcepcionParser excepcionParser) {
            excepcionParser.printStackTrace();
        } catch (ExcepcionEvaluacion excepcionEvaluacion) {
            excepcionEvaluacion.printStackTrace();
        }
    }
}
