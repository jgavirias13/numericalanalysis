package com.eafit.numericalanalysis.excepciones;

/**
 * Created by jgavi on 15/10/2016.
 */

public class ExcepcionIteraciones extends Exception {

    public ExcepcionIteraciones(){
        super("Error, las iteraciones deben ser mayores a cero");
    }
}
