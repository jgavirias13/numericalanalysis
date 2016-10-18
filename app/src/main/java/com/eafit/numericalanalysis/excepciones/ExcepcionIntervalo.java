package com.eafit.numericalanalysis.excepciones;

/**
 * Created by jgavi on 16/10/2016.
 */

public class ExcepcionIntervalo extends Exception {

    public ExcepcionIntervalo(){
        super("Error, no se puede asegurar que el intervalo contenga una raiz");
    }
}
