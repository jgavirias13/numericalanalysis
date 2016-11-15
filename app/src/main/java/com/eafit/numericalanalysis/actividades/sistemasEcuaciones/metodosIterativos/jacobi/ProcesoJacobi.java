package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosIterativos.jacobi;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.SalidaGaussSeidel;
import com.eafit.numericalanalysis.util.Comunicacion;

public class ProcesoJacobi extends AppCompatActivity implements View.OnClickListener{

    private SalidaGaussSeidel salida;
    private int etapa;
    private TextView txtMatriz;
    private TextView txtTitulo;
    private TextView txtError;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_jacobi);
        findViewById(R.id.btnAnterior).setOnClickListener(this);
        findViewById(R.id.btnSiguiente).setOnClickListener(this);

        txtMatriz = (TextView) findViewById(R.id.txtMatriz);
        txtTitulo = (TextView) findViewById(R.id.txtGaussTitulo);
        txtError = (TextView) findViewById(R.id.txtError);

        salida = (SalidaGaussSeidel) Comunicacion.receive();
        etapa = 0;
        res = getResources();
        txtTitulo.setText(res.getString(R.string.etapa)+" "+(etapa));
        double[] x = salida.getEtapas().get(0);
        txtMatriz.setText("X:\n"+armarSalida(x));
        txtError.setText("Error: " + String.format("%e",salida.getErrores().get(0)));
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnAnterior:
                if(etapa >0){
                    etapa--;
                    double[] x = salida.getEtapas().get(etapa);
                    double error = salida.getErrores().get(etapa);
                    txtMatriz.setText("X:\n" + armarSalida(x));
                    txtError.setText("Error: "+String.format("%e",error));
                    txtTitulo.setText(res.getString(R.string.etapa) + " " + (etapa));
                }
                break;
            case R.id.btnSiguiente:
                if(etapa != salida.getEtapas().size()-1){
                    etapa++;
                    double[] x = salida.getEtapas().get(etapa);
                    double error = salida.getErrores().get(etapa);
                    txtMatriz.setText("X:\n" + armarSalida(x));
                    txtError.setText("Error: "+String.format("%e",error));
                    txtTitulo.setText(res.getString(R.string.etapa) + " " + (etapa));
                }
                break;
        }
    }

    private String armarSalida(double[] matrizEtapa){
        String matriz = "";
        for(int i=0; i<matrizEtapa.length; i++){
            matriz += String.format("X%d: %.14f",i, matrizEtapa[i]);
            matriz += "\n\n";
        }
        return matriz;
    }
}
