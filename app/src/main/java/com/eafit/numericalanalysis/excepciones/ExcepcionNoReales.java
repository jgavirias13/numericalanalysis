package com.eafit.numericalanalysis.excepciones;

/**
 * Created by jgavi on 12/11/2016.
 */

public class ExcepcionNoReales extends Exception{
    public ExcepcionNoReales(){
        super("No hay solucion en reales");
    }
}
