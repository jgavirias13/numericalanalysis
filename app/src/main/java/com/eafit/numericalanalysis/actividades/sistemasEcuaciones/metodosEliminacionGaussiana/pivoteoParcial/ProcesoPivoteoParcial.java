package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosEliminacionGaussiana.pivoteoParcial;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.SalidaGaussSimple;
import com.eafit.numericalanalysis.estructuras.SalidaPivoteoParcial;
import com.eafit.numericalanalysis.util.Comunicacion;

public class ProcesoPivoteoParcial extends AppCompatActivity implements View.OnClickListener{

    private SalidaPivoteoParcial salida;
    private int iteracion;
    private TextView txtMatriz;
    private TextView txtTitulo;
    private TextView txtMultiplicador;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_pivoteo_parcial);
        findViewById(R.id.btnAnterior).setOnClickListener(this);
        findViewById(R.id.btnSiguiente).setOnClickListener(this);

        txtMatriz = (TextView) findViewById(R.id.txtMatriz);
        txtTitulo = (TextView) findViewById(R.id.txtGaussTitulo);
        txtMultiplicador = (TextView) findViewById(R.id.txtMultiplicador);

        salida = (SalidaPivoteoParcial) Comunicacion.receive();
        iteracion = 0;
        res = getResources();
        txtTitulo.setText(res.getString(R.string.etapa)+" "+(salida.getEtapaPropia().get(iteracion)+1));
        double[][] matrizEtapa = salida.getEtapas().get(iteracion);
        txtMatriz.setText(armarSalida(matrizEtapa));
        txtMultiplicador.setText(armarMultiplicador(salida.getMultiplicadores().get(iteracion)));

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnAnterior:
                if(iteracion >0){
                    iteracion--;
                    double[][] matrizEtapa = salida.getEtapas().get(iteracion);
                    txtMatriz.setText(armarSalida(matrizEtapa));
                    if(salida.getCambio().get(iteracion)){
                        txtMultiplicador.setText(res.getString(R.string.cambio_fila)+" "+salida.getStrCambio().get(iteracion));
                    }else{
                        txtMultiplicador.setText(armarMultiplicador(salida.getMultiplicadores().get(iteracion)));
                    }
                    txtTitulo.setText(res.getString(R.string.etapa)+" "+(salida.getEtapaPropia().get(iteracion)+1));
                }
                break;
            case R.id.btnSiguiente:
                if(iteracion < salida.getEtapas().size()-1){
                    System.out.println("Entre");
                    iteracion++;
                    double[][] matrizEtapa = salida.getEtapas().get(iteracion);
                    txtMatriz.setText(armarSalida(matrizEtapa));
                    txtTitulo.setText(res.getString(R.string.etapa)+" "+(salida.getEtapaPropia().get(iteracion)+1));
                    if(salida.getCambio().get(iteracion)){
                        txtMultiplicador.setText(res.getString(R.string.cambio_fila)+" "+salida.getStrCambio().get(iteracion));
                    }else{
                        txtMultiplicador.setText(armarMultiplicador(salida.getMultiplicadores().get(iteracion)));
                    }
                }
                break;
        }
    }

    private String armarSalida(double[][] matrizEtapa){
        String matriz = "";
        for(int i=0; i<matrizEtapa.length; i++){
            for(int j=0; j<matrizEtapa[0].length; j++){
                matriz += String.format("%g ",matrizEtapa[i][j]);
            }
            matriz += "\n\n";
        }
        return matriz;
    }

    private String armarMultiplicador(double[] multiplicador){
        String salida = "Multiplicadores: ";
        for(int i=0; i<multiplicador.length; i++){
            salida += String.format("%g, ", multiplicador[i]);
        }
        return salida;
    }
}
