package com.eafit.numericalanalysis.estructuras;

import com.eafit.numericalanalysis.util.Intervalo;

import java.util.ArrayList;

/**
 * Created by jgavi on 29/10/2016.
 */

public class SalidaNewton {
    private ArrayList<ColumnaNewton> matriz;
    private Intervalo<Double,Double> respuesta;

    public SalidaNewton(){
        matriz = new ArrayList<>();
    }

    public void agregar(int n, double x, double fx, double fdx, double error){
        matriz.add(new ColumnaNewton(n, x, fx, fdx, error));
    }

    public ArrayList<ColumnaNewton> getMatriz(){
        return this.matriz;
    }

    public void setRespuesta(double x0, double x1){
        this.respuesta = new Intervalo<>(x0, x1);
    }

    public Intervalo<Double,Double> obtenerRaiz(){
        return respuesta;
    }
}

class ColumnaNewton{
    private int n;
    private double x;
    private double fx;
    private double fdx;
    private double error;

    public ColumnaNewton(int n, double x, double fx, double fdx, double error){
        this.n = n;
        this.x = x;
        this.fx = fx;
        this.fdx = fdx;
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
    public double getFdx(){
        return this.fdx;
    }
    public double getError(){
        return this.error;
    }
}
