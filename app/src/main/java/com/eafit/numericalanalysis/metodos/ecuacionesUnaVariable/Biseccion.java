package com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable;

import com.eafit.numericalanalysis.excepciones.ExcepcionIntervalo;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionRaiz;
import com.eafit.numericalanalysis.util.Intervalo;
import com.eafit.numericalanalysis.util.Parser;
import com.eafit.numericalanalysis.util.Impresion;
import com.eafit.numericalanalysis.excepciones.ExcepcionTolerancia;

import java.lang.Exception;

public class Biseccion {
    public static Intervalo<Double, Double> evaluar(double xi, double xs,
                                                    double tol, int iter,
                                                    Parser funcion,
                                                    int tipoError)
            throws Exception {

        //Comprobar datos de entrada
        if (tol < 0)
            throw new ExcepcionTolerancia();
        if (iter <= 0)
            throw new ExcepcionIteraciones();

        //Evaluar extremos de los intervalos
        double fxi = funcion.getValue(xi);
        double fxs = funcion.getValue(xs);
        double xm = 0, fxm = 0, error = 0;

        if (fxi == 0) //Verificar si xi es una raiz
            return new Intervalo<Double, Double>(xi, 0d);
        if (fxs == 0) //Verificar si xs es una raiz
            return new Intervalo<Double, Double>(xs, 0d);
        if (fxi * fxs > 0) // Verificar que existan raices en el intervalo
            throw new ExcepcionIntervalo();
        xm = (xi + xs) / 2;
        fxm = funcion.getValue(xm);
        error = tol + 1;
        int cont = 1;
        Impresion.encabezadoBiseccion();
        Impresion.biseccion(cont, xi, xs, xm, fxm, error);
        //Mientras no encuentre una raiz
        while (fxm != 0 && error > tol && cont < iter) {
            //Escoger nuevo intervalo
            if (fxi * fxm < 0) {
                xs = xm;
                fxs = fxm;
            } else {
                xi = xm;
                fxi = fxm;
            }

            //Asegurar valores de cálculo del error
            double aux = xm;
            xm = (xi + xs) / 2;
            fxm = funcion.getValue(xm);
            error = Math.abs(xm - aux);
            if (tipoError == 2) error = Math.abs(error / xm);
            cont++;
            Impresion.biseccion(cont, xi, xs, xm, fxm, error);
        }

        //Evalución de resultados posibles
        if (fxm == 0 || error < tol)
            return new Intervalo<Double, Double>(xm, error);

        throw new ExcepcionRaiz();
    }
}
