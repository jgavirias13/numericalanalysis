package com.eafit.numericalanalysis.estructuras;

import com.eafit.numericalanalysis.util.Intervalo;

import java.util.ArrayList;

/**
 * Created by jgavi on 23/10/2016.
 */

public class SalidaBusquedasIncrementales {

    private ArrayList<ColumnaBusquedas> matriz;
    private Intervalo<Double,Double> respuesta;

    public SalidaBusquedasIncrementales(){
        matriz = new ArrayList<>();
    }

    public void agregar(int n, double x0, double x1){
        matriz.add(new ColumnaBusquedas(n,x0,x1));
    }

    public ArrayList<ColumnaBusquedas> getMatriz(){
        return this.matriz;
    }

    public void setRespuesta(double x0, double x1){
        this.respuesta = new Intervalo<>(x0, x1);
    }

    public Intervalo<Double,Double> obtenerRaiz(){
        return respuesta;
    }
}

class ColumnaBusquedas{

    private int n;
    private double x0;
    private double x1;

    public ColumnaBusquedas(int n, double x0, double x1){
        this.n = n;
        this.x0 = x0;
        this.x1 = x1;
    }

    public int getN(){
        return this.n;
    }
    public double getX0(){
        return this.x0;
    }
    public double getX1(){
        return this.x1;
    }
}
