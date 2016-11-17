package com.eafit.numericalanalysis.metodos.derivacion;

import com.eafit.numericalanalysis.util.Tupla;

/**
 * Created by jgavi on 15/11/2016.
 */

public class DerivadaCincoPuntos {
    public static double evaluar(Tupla[] puntos, int tipo){
        double h = puntos[1].getX()-puntos[0].getX();
        double rpta;
        switch (tipo){
            case 1:
                rpta = (1/(12*h))*(-25*puntos[0].getY()+48*puntos[1].getY()-36*puntos[2].getY()+16*puntos[3].getY()-3*puntos[4].getY());
                break;
            case 2:
                rpta = (1/(12*h))*(puntos[0].getY()-8*puntos[1].getY()+8*puntos[3].getY()-puntos[4].getY());
                break;
            case 3:
                rpta = (1/(12*(-h)))*(-25*puntos[4].getY()+48*puntos[3].getY()-36*puntos[2].getY()+16*puntos[1].getY()-3*puntos[0].getY());
                break;
            default:
                rpta = 0;
                break;
        };
        return rpta;
    }
}
