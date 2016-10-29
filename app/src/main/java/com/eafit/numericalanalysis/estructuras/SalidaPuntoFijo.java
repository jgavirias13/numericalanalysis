package com.eafit.numericalanalysis.estructuras;

import com.eafit.numericalanalysis.util.Intervalo;

import java.util.ArrayList;

/**
 * Created by jgavi on 29/10/2016.
 */

public class SalidaPuntoFijo {

    private ArrayList<ColumnaPuntoFijo> matriz;
    private Intervalo<Double,Double> respuesta;

    public SalidaPuntoFijo(){
        matriz = new ArrayList<>();
    }

    public void agregar(int n, double x, double fx, double error){
        matriz.add(new ColumnaPuntoFijo(n, x, fx, error));
    }

    public ArrayList<ColumnaPuntoFijo> getMatriz(){
        return this.matriz;
    }

    public void setRespuesta(double x0, double x1){
        this.respuesta = new Intervalo<>(x0, x1);
    }

    public Intervalo<Double,Double> obtenerRaiz(){
        return respuesta;
    }

}

class ColumnaPuntoFijo{
    private int n;
    private double x;
    private double fx;
    private double error;

    public ColumnaPuntoFijo(int n, double x, double fx, double error){
        this.n = n;
        this.x = x;
        this.fx = fx;
        this.error = error;
    }

    public int getN(){
        return this.n;
    }
    public double getX(){
        return this.x;
    }
    public double getFx(){
        return this.fx;
    }
    public double getError(){
        return this.error;
    }
}
