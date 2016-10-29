package com.eafit.numericalanalysis.estructuras;

import com.eafit.numericalanalysis.util.Intervalo;

import java.util.ArrayList;

/**
 * Created by jgavi on 29/10/2016.
 */

public class SalidaSecante {
    private ArrayList<ColumnaSecante> matriz;
    private Intervalo<Double,Double> respuesta;

    public SalidaSecante(){
        matriz = new ArrayList<>();
    }

    public void agregar(int n, double x0, double fx, double error){
        matriz.add(new ColumnaSecante(n, x0, fx, error));
    }

    public ArrayList<ColumnaSecante> getMatriz(){
        return this.matriz;
    }

    public void setRespuesta(double x0, double x1){
        this.respuesta = new Intervalo<>(x0, x1);
    }

    public Intervalo<Double,Double> obtenerRaiz(){
        return respuesta;
    }
}

class ColumnaSecante{
    private int n;
    private double x0;
    private double fx;
    private double error;

    public ColumnaSecante(int n, double x0, double fx, double error){
        this.n = n;
        this.x0 = x0;
        this.fx = fx;
        this.error = error;
    }

    public int getN(){
        return this.n;
    }
    public double getX0(){
        return this.x0;
    }
    public double getFx(){
        return this.fx;
    }
    public double getError(){
        return this.error;
    }
}
