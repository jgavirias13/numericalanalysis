package com.eafit.numericalanalysis.estructuras;

import java.util.ArrayList;

/**
 * Created by jgavi on 13/11/2016.
 */

public class SalidaGaussSeidel {

    private ArrayList<double[]> etapas;
    private ArrayList<Double> errores;

    public SalidaGaussSeidel(){
        etapas = new ArrayList<>();
        errores = new ArrayList<>();
    }

    public void addEtapa(double[] valores, double error){
        double[] copia = new double[valores.length];
        System.arraycopy(valores,0,copia,0,valores.length);
        etapas.add(copia);
        errores.add(error);
    }

    public ArrayList<Double> getErrores() {
        return errores;
    }

    public ArrayList<double[]> getEtapas() {
        return etapas;
    }

    public double[] getRespuesta(){
        return etapas.get(etapas.size()-1);
    }
}
