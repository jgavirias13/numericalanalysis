package com.eafit.numericalanalysis.excepciones;

/**
 * Created by jgavi on 16/10/2016.
 */

public class ExcepcionDelta extends Exception {

    public ExcepcionDelta(){
        super("Error, delta debe ser diferente de 0");
    }
}
