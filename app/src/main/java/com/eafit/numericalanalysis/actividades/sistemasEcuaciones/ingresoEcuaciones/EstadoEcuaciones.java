package com.eafit.numericalanalysis.actividades.sistemasEcuaciones.ingresoEcuaciones;

/**
 * Created by jgavi on 8/11/2016.
 */

public class EstadoEcuaciones {
    private double[][] ecuaciones;
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

    public boolean setEcuaciones(double[][] ecuaciones){
        int n = ecuaciones.length;
        if(n == 0)
            return false;
        int m = ecuaciones[0].length;
        if(m == 0)
            return false;
        for(int i=0; i<n; i++)
            for(int j=0; j<m; j++)
                this.ecuaciones[i][j] = ecuaciones[i][j];
        nPuntos = n;
        return true;
    }

    public double[][] getEcuaciones(){
        return this.ecuaciones;
    }

    public int getnPuntos(){
        return this.nPuntos;
    }
}
