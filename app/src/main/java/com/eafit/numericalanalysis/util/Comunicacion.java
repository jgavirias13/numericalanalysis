package com.eafit.numericalanalysis.util;

/**
 * Created by jgavi on 23/10/2016.
 */

public class Comunicacion {

    private static Object objeto;

    public static void send(Object objeto){
        Comunicacion.objeto = objeto;
    }

    public static Object receive(){
        Object aux = objeto;
        objeto = null;
        return aux;
    }
}
