package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosIterativos.gaussSeidel;

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
import android.widget.ListView;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.actividades.sistemasEcuaciones.ingresoEcuaciones.EstadoEcuaciones;
import com.eafit.numericalanalysis.estructuras.IterativosAdapter;
import com.eafit.numericalanalysis.estructuras.SalidaGaussSeidel;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionLambda;
import com.eafit.numericalanalysis.excepciones.ExcepcionTolerancia;
import com.eafit.numericalanalysis.metodos.sistemasEcuaciones.GaussSeidel;
import com.eafit.numericalanalysis.util.Comunicacion;
import com.eafit.numericalanalysis.util.Help;

import java.util.ArrayList;

public class IngresoGaussSeidel extends AppCompatActivity implements View.OnClickListener{

    private ListView listaValores;
    private EditText edtIteraciones;
    private EditText edtTolerancia;
    private EditText edtLambda;
    private LinearLayout layoutRespuesta;
    private TextView txtRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_gauss_seidel);
        findViewById(R.id.btnCalcular).setOnClickListener(this);
        findViewById(R.id.btnProceso).setOnClickListener(this);
        findViewById(R.id.btnHelp).setOnClickListener(this);

        listaValores = (ListView) findViewById(R.id.listValores);
        edtIteraciones = (EditText) findViewById(R.id.edtIteraciones);
        edtTolerancia = (EditText) findViewById(R.id.edtTolerancia);
        edtLambda = (EditText) findViewById(R.id.edtLamda);
        layoutRespuesta = (LinearLayout) findViewById(R.id.layoutRespuesta);
        txtRespuesta = (TextView) findViewById(R.id.txtRespuesta);

        EstadoEcuaciones estado = EstadoEcuaciones.solicitarEstado();

        ArrayList<Double> valores = new ArrayList<>();
        for(int i=0; i<estado.getnPuntos(); i++){
            valores.add(0d);
        }

        IterativosAdapter adapter = new IterativosAdapter(this, valores);
        listaValores.setAdapter(adapter);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnCalcular:
                layoutRespuesta.requestFocus();
                EstadoEcuaciones estado = EstadoEcuaciones.solicitarEstado();
                String strInteraciones = edtIteraciones.getText().toString();
                String strTolerancia = edtTolerancia.getText().toString();
                String strLambda = edtLambda.getText().toString();
                Resources res = getResources();

                if(strInteraciones.isEmpty()){
                    error(res.getString(R.string.error_iteraciones));
                }else if(strTolerancia.isEmpty()){
                    error(res.getString(R.string.error_tolerancia));
                }else if(strLambda.isEmpty()){
                    error(res.getString(R.string.error_lambda));
                }else {

                    int iteraciones = Integer.parseInt(edtIteraciones.getText().toString());
                    double tolerancia = Double.parseDouble(edtTolerancia.getText().toString());
                    double lambda = Double.parseDouble(edtLambda.getText().toString());
                    double[] valores = new double[estado.getnPuntos()];
                    IterativosAdapter adapter = (IterativosAdapter) listaValores.getAdapter();
                    for (int i = 0; i < estado.getnPuntos(); i++) {
                        valores[i] = adapter.getValor(i);
                    }
                    SalidaGaussSeidel salida = null;
                    try {
                        salida = GaussSeidel.evaluar(estado.getA(), estado.getB(), valores, iteraciones, tolerancia, lambda);
                    } catch (ExcepcionIteraciones excepcionIteraciones) {
                        error(res.getString(R.string.error_iteraciones));
                    } catch (ExcepcionTolerancia excepcionTolerancia) {
                        error(res.getString(R.string.error_tolerancia));
                    } catch (ExcepcionLambda excepcionLambda) {
                        error(res.getString(R.string.error_lambda));
                    }
                    layoutRespuesta.setVisibility(View.VISIBLE);
                    Comunicacion.send(salida);
                    String valoresRespuesta = "";
                    for (int i = 0; i < estado.getnPuntos(); i++) {
                        valoresRespuesta += String.format("x%d: %.14f\n", i, salida.getRespuesta()[i]);
                    }
                    txtRespuesta.setText(valoresRespuesta);
                }
                break;
            case R.id.btnProceso:
                Intent proceso = new Intent(this, ProcesoGaussSeidel.class);
                startActivity(proceso);
                break;
            case R.id.btnHelp:
                Intent help = new Intent(this, Help.class);
                help.putExtra("id", R.string.help_gauss_seidel);
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
