package com.eafit.numericalanalysis.estructuras;

import java.util.ArrayList;

/**
 * Created by jgavi on 12/11/2016.
 */

public class SalidaFactorizacionCholesky {
    private ArrayList<double[][]> etapasL;
    private ArrayList<double[][]> etapasU;
    private double[] z;
    private double[] x;
    private double determinante;

    public SalidaFactorizacionCholesky(int n){
        etapasL = new ArrayList<>();
        etapasU = new ArrayList<>();
        z = new double[n];
        x = new double[n];
    }

    public void addEtapa(double[][] l, double[][] u){
        int n = l.length;
        double[][] nuevoL = new double[n][n];
        double[][] nuevoU = new double[n][n];
        for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                nuevoU[i][j] = u[i][j];
                nuevoL[i][j] = l[i][j];
            }
        }
        etapasU.add(nuevoU);
        etapasL.add(nuevoL);
    }

    public void setX(double[] x){
        for(int i=0; i<x.length; i++){
            this.x[i] = x[i];
        }
    }

    public void setZ(double[] z){
        for(int i=0; i<z.length; i++){
            this.z[i] = z[i];
        }
    }

    public void setDeterminante(double determinante) {
        this.determinante = determinante;
    }

    public double getDeterminante() {
        return determinante;
    }

    public ArrayList<double[][]> getEtapasL() {
        return etapasL;
    }

    public ArrayList<double[][]> getEtapasU() {
        return etapasU;
    }

    public double[] getX() {
        return x;
    }

    public double[] getZ() {
        return z;
    }
}
