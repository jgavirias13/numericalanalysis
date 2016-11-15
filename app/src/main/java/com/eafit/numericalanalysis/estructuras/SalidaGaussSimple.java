package com.eafit.numericalanalysis.estructuras;

import java.util.ArrayList;

/**
 * Created by jgavi on 10/11/2016.
 */

public class SalidaGaussSimple {

    private ArrayList<double[][]> etapas;
    private ArrayList<double[]> multiplicadores;
    private double[] resultado;

    public SalidaGaussSimple(int n){
        etapas = new ArrayList<>();
        resultado = new double[n];
        multiplicadores = new ArrayList<>();
    }

    public void addEtapa(double[][] etapa, double[] multiplicador){
        int n = etapa.length;
        int m = etapa[0].length;
        double[][] nuevaEtapa = new double[n][m];
        double[] nuevoMultiplicador = new double[n];
        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++)
                nuevaEtapa[i][j] = etapa[i][j];
            nuevoMultiplicador[i] = multiplicador[i];
        }
        etapas.add(nuevaEtapa);
        multiplicadores.add(nuevoMultiplicador);
    }

    public void setResultado(double[] resultado){
        for(int i=0; i<resultado.length; i++){
            this.resultado[i] = resultado[i];
        }
    }

    public ArrayList<double[][]> getEtapas(){
        return this.etapas;
    }

    public double[] getResultado(){
        return this.resultado;
    }

    public ArrayList<double[]> getMultiplicadores() {
        return multiplicadores;
    }
}
