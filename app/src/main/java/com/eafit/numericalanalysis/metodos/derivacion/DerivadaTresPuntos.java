package com.eafit.numericalanalysis.metodos.derivacion;

import com.eafit.numericalanalysis.util.Tupla;

/**
 * Created by jgavi on 15/11/2016.
 */

public class DerivadaTresPuntos {
    public static double evaluar(Tupla[] puntos, int tipo){
        double h = puntos[1].getX()-puntos[0].getX();
        double rpta;
        switch (tipo){
            case 1:
                rpta = (1/(2*h))*(-3*puntos[0].getY()+4*puntos[1].getY()-puntos[2].getY());
                break;
            case 2:
                rpta = (1/(2*h))*(-puntos[0].getY()+puntos[2].getY());
                break;
            case 3:
                rpta = (1/(2*h))*(puntos[0].getY()-4*puntos[1].getY()+3*puntos[2].getY());
                break;
            default:
                rpta = 0;
                break;
        };
        return rpta;
    }
}
