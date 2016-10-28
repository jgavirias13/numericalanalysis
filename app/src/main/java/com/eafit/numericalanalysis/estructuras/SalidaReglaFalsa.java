package com.eafit.numericalanalysis.estructuras;

import com.eafit.numericalanalysis.util.Intervalo;

import java.util.ArrayList;

/**
 * Created by jgavi on 28/10/2016.
 */

public class SalidaReglaFalsa {
    private ArrayList<ColumnaReglaFalsa> matriz;
    private Intervalo<Double,Double> respuesta;

    public SalidaReglaFalsa(){
        matriz = new ArrayList<>();
    }

    public void agregar(int n, double xi, double xs, double xm, double fxm, double error){
        matriz.add(new ColumnaReglaFalsa(n, xi, xs, xm, fxm, error));
    }

    public ArrayList<ColumnaReglaFalsa> getMatriz(){
        return this.matriz;
    }

    public void setRespuesta(double x0, double x1){
        this.respuesta = new Intervalo<>(x0, x1);
    }

    public Intervalo<Double,Double> obtenerRaiz(){
        return respuesta;
    }
}

class ColumnaReglaFalsa{
    private int n;
    private double xi;
    private double xs;
    private double xm;
    private double fxm;
    private double error;

    public ColumnaReglaFalsa(int n, double xi, double xs, double xm, double fxm, double error){
        this.n = n;
        this.xi = xi;
        this.xs = xs;
        this.xm = xm;
        this.fxm = fxm;
        this.error = error;
    }

    public int getN(){
        return this.n;
    }
    public double getXi(){
        return this.xi;
    }
    public double getXs(){
        return this.xs;
    }
    public double getXm(){
        return this.xm;
    }
    public double getFxm(){
        return this.fxm;
    }
    public double getError(){
        return this.error;
    }
}
