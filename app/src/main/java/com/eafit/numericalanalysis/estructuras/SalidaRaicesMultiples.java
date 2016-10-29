package com.eafit.numericalanalysis.estructuras;

import com.eafit.numericalanalysis.util.Intervalo;

import java.util.ArrayList;

/**
 * Created by jgavi on 29/10/2016.
 */

public class SalidaRaicesMultiples {
    private ArrayList<ColumnaRaicesMultiples> matriz;
    private Intervalo<Double,Double> respuesta;

    public SalidaRaicesMultiples(){
        matriz = new ArrayList<>();
    }

    public void agregar(int n, double x, double fx, double fdx, double f2dx, double error){
        matriz.add(new ColumnaRaicesMultiples(n, x, fx, fdx, f2dx, error));
    }

    public ArrayList<ColumnaRaicesMultiples> getMatriz(){
        return this.matriz;
    }

    public void setRespuesta(double x0, double x1){
        this.respuesta = new Intervalo<>(x0, x1);
    }

    public Intervalo<Double,Double> obtenerRaiz(){
        return respuesta;
    }
}

class ColumnaRaicesMultiples{
    private int n;
    private double x;
    private double fx;
    private double fdx;
    private double f2dx;
    private double error;

    public ColumnaRaicesMultiples(int n, double x, double fx, double fdx, double f2dx, double error){
        this.n = n;
        this.x = x;
        this.fx = fx;
        this.fdx = fdx;
        this.f2dx = f2dx;
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
    public double getF2dx(){
        return this.f2dx;
    }
    public double getError(){
        return this.error;
    }
}
