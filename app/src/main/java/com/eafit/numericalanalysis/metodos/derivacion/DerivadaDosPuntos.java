package com.eafit.numericalanalysis.metodos.derivacion;

import com.eafit.numericalanalysis.util.Tupla;

/**
 * Created by jgavi on 15/11/2016.
 */

public class DerivadaDosPuntos {
    public static double evaluar(Tupla[] puntos, int tipo){
        double h = puntos[1].getX()-puntos[0].getX();
        double rpta;
        if(tipo == 1)
            rpta = (puntos[1].getY()-puntos[0].getY())/(h);
        else
            rpta = (-puntos[1].getY()+puntos[0].getY())/(-h);
        return rpta;
    }
}
