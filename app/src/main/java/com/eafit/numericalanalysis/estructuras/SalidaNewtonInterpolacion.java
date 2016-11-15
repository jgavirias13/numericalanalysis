package com.eafit.numericalanalysis.estructuras;

/**
 * Created by jgavi on 13/11/2016.
 */

public class SalidaNewtonInterpolacion {

    private double[][] proceso;
    private double[] resultado;
    private int n;

    public SalidaNewtonInterpolacion(int n){
        proceso = new double[n][n];
        resultado = new double[n];
        this.n = n;
    }

    public void setProceso(double[][] proceso) {
        for(int i =0; i<n; i++)
            for(int j=0; j<n; j++)
                this.proceso[i][j] = proceso[i][j];
    }

    public void setResultado(double[] resultado) {
        System.arraycopy(resultado,0,this.resultado,0,n);
    }

    public double[] getResultado() {
        return resultado;
    }

    public double[][] getProceso() {
        return proceso;
    }

    public int getN() {
        return n;
    }
}
