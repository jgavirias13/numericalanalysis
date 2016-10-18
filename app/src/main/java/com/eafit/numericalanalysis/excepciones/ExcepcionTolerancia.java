package com.eafit.numericalanalysis.excepciones;

/**
 * Created by jgavi on 15/10/2016.
 */

public class ExcepcionTolerancia extends Exception {

    public ExcepcionTolerancia(){
        super("Error, la tolerancia debe ser mayor o igual a cero");
    }

}
