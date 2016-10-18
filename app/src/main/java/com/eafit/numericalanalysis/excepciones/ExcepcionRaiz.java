package com.eafit.numericalanalysis.excepciones;

/**
 * Created by jgavi on 16/10/2016.
 */

public class ExcepcionRaiz extends Exception {

    public ExcepcionRaiz(){
        super("Error, no se encontro una raiz en las iteraciones dadas");
    }
}
