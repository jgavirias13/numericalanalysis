package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.metodosFactorizacion.factorizacionCrout;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.eafit.numericalanalysis.R;
import com.eafit.numericalanalysis.estructuras.SalidaFactorizacionCrout;
import com.eafit.numericalanalysis.estructuras.SalidaFactorizacionPivoteo;
import com.eafit.numericalanalysis.util.Comunicacion;

public class ProcesoFactorizacionCrout extends AppCompatActivity implements View.OnClickListener{

    private SalidaFactorizacionCrout salida;
    private int etapa;
    private TextView txtMatriz;
    private TextView txtTitulo;
    private TextView txtMultiplicador;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_factorizacion_crout);
        findViewById(R.id.btnAnterior).setOnClickListener(this);
        findViewById(R.id.btnSiguiente).setOnClickListener(this);

        txtMatriz = (TextView) findViewById(R.id.txtMatriz);
        txtTitulo = (TextView) findViewById(R.id.txtGaussTitulo);
        txtMultiplicador = (TextView) findViewById(R.id.txtMultiplicador);

        salida = (SalidaFactorizacionCrout) Comunicacion.receive();
        etapa = 0;
        res = getResources();
        txtTitulo.setText(res.getString(R.string.etapa)+" "+(etapa));
        double[][] l = salida.getEtapasL().get(etapa);
        double[][] u = salida.getEtapasU().get(etapa);
        txtMatriz.setText("L:\n"+armarSalida(l));
        txtMultiplicador.setText("U:\n"+armarSalida(u));
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnAnterior:
                if(etapa >0){
                    etapa--;
                    if(etapa == salida.getEtapasL().size()) {
                        double[] z = salida.getZ();
                        txtMatriz.setText(armarMultiplicador(z));
                        txtMultiplicador.setText("");
                    }else {
                        double[][] l = salida.getEtapasL().get(etapa);
                        double[][] u = salida.getEtapasU().get(etapa);
                        txtMatriz.setText("L:\n" + armarSalida(l));
                        txtMultiplicador.setText("U:\n" + armarSalida(u));
                        txtTitulo.setText(res.getString(R.string.etapa) + " " + (etapa));
                    }
                }
                break;
            case R.id.btnSiguiente:
                if(etapa < salida.getEtapasL().size()-1){
                    etapa++;
                    double[][] l = salida.getEtapasL().get(etapa);
                    double[][] u = salida.getEtapasU().get(etapa);
                    txtMatriz.setText("L:\n"+armarSalida(l));
                    txtMultiplicador.setText("U:\n"+armarSalida(u));
                    txtTitulo.setText(res.getString(R.string.etapa)+" "+(etapa));
                }else if(etapa == salida.getEtapasL().size()-1) {
                    etapa++;
                    double[] z = salida.getZ();
                    txtMatriz.setText(armarMultiplicador(z));
                    txtMultiplicador.setText("");
                    txtTitulo.setText(res.getText(R.string.matriz_z));
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
        String salida = "Z: ";
        for(int i=0; i<multiplicador.length; i++){
            salida += String.format("%g, ", multiplicador[i]);
        }
        return salida;
    }
}
