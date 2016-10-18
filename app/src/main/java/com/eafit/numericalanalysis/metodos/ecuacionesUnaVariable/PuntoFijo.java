package com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable;

import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionRaiz;
import com.eafit.numericalanalysis.excepciones.ExcepcionTolerancia;
import com.eafit.numericalanalysis.util.Intervalo;
import com.eafit.numericalanalysis.util.Parser;
import com.eafit.numericalanalysis.util.Impresion;

import java.lang.Exception;


/**
 * Clase para implementar le metodo punto fijo
 *
 * @author: Juan Pablo Gaviria, Linda Gutierrez, Santiago Gallego
 * @see <a href="https://sites.google.com/site/numericalanalysis20162/"/> Pagina principal del proyecto </a>
 */
public class PuntoFijo {

    /**
     * Metodo que aplica el algoritmo de punto fijo
     *
     * @param x0          Punto inicial para iniciar la busqueda
     * @param iteraciones Numero maximo de iteraciones del metodo
     * @param tolerancia  Tolerancia que se busca alcanzar con el resultado
     * @return La raiz aproximada de la funcion encontrada
     */
    public static Intervalo<Double, Double> evaluar(double x0,
                                                    double tolerancia,
                                                    int iteraciones,
                                                    Parser funcionF,
                                                    Parser funcionG,
                                                    int tipoError)
            throws Exception {

        //Errores de entrada
        if (iteraciones <= 0)
            throw new ExcepcionIteraciones();
        if (tolerancia < 0)
            throw new ExcepcionTolerancia();

        double y0, xm, error;
        int n;
        y0 = funcionF.getValue(x0);
        n = 1;
        error = tolerancia + 1;
        Impresion.encabezadoPuntoFijo();
        Impresion.puntoFijo(n, x0, y0, error);
        while (y0 != 0 && error > tolerancia && n < iteraciones) {
            xm = funcionG.getValue(x0);
            y0 = funcionF.getValue(xm);
            error = Math.abs(xm - x0);
            if (tipoError == 2) error = Math.abs(error / xm);
            x0 = xm;
            n++;
            Impresion.puntoFijo(n, x0, y0, error);
        }
        if (y0 == 0 || error <= tolerancia)
            return new Intervalo<Double, Double>(x0, error);
        throw new ExcepcionRaiz();
    }
}
