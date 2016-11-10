package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.ingresoEcuaciones;

/**
 * Created by jgavi on 8/11/2016.
 */

public class EstadoEcuaciones {
    private double[][] a;
    private double[] b;
    private int nPuntos;

    private static EstadoEcuaciones estado;

    private EstadoEcuaciones(){
        this.nPuntos = 0;
    }

    public static EstadoEcuaciones solicitarEstado(){
        if(estado == null){
            estado = new EstadoEcuaciones();
        }
        return estado;
    }

    public boolean setEcuaciones(double[][] a, double[] b){
        int n = a.length;
        if(n == 0)
            return false;
        this.a = new double[n][n];
        this.b = new double[n];
        for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++)
                this.a[i][j] = a[i][j];
            this.b[i] = b[i];
        }
        nPuntos = n;
        return true;
    }

    public double[][] getA(){
        return this.a;
    }

    public double[] getB(){
        return this.b;
    }

    public int getnPuntos(){
        return this.nPuntos;
    }
}
