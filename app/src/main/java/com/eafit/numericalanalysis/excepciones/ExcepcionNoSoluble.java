package com.eafit.numericalanalysis.excepciones;

/**
 * Created by jgavi on 16/10/2016.
 */

public class ExcepcionNoSoluble extends Exception {

    public ExcepcionNoSoluble(){
        super("Error, el sistema no se puede resolver");
    }
}
