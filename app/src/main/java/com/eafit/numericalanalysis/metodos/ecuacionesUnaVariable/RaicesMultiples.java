package com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable;

import java.lang.Exception;

import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionRaiz;
import com.eafit.numericalanalysis.excepciones.ExcepcionTolerancia;
import com.eafit.numericalanalysis.util.Impresion;
import com.eafit.numericalanalysis.util.Intervalo;
import com.eafit.numericalanalysis.util.Parser;

/**
 * Clase para implementar el metodo de raices multiples
 *
 * @author: Juan Pablo Gaviria, Linda Gutierrez, Santiago Gallego
 * @see <a href="https://sites.google.com/site/numericalanalysis20162/"/>Pagina principal del proyecto </a>
 */
public class RaicesMultiples {

    /**
     * Metodo que aplica el algoritmo de raices multiples
     *
     * @param x0          Punto inicial para iniciar la busqueda
     * @param iteraciones Numero maxio de iteraciones del metodo
     * @param tolerancia  Tolerancia que se busca alcanzar con el resultado
     * @return La raiz aproximada de la funcion encontrada
     */
    public static Intervalo<Double, Double> evaluar(double x0,
                                                    double tolerancia,
                                                    int iteraciones,
                                                    Parser funcion,
                                                    Parser derivada,
                                                    Parser derivada2,
                                                    int tipoError)
            throws Exception {

        //Errores de entrada
        if (iteraciones <= 0)
            throw new ExcepcionIteraciones();
        if (tolerancia < 0)
            throw new ExcepcionTolerancia();

        double y0, xm, error, y0d, y02d, diferencia;
        int n;
        y0 = funcion.getValue(x0);
        n = 1;
        error = tolerancia + 1;
        y0d = derivada.getValue(x0);
        y02d = derivada2.getValue(x0);
        diferencia = Math.pow(y0d, 2) - y0 * y02d;

        Impresion.encabezadoRaicesMultiples();
        Impresion.raicesMultiples(n, x0, y0, y0d, y02d, error);

        while (y0 != 0 && error > tolerancia && diferencia != 0 && n < iteraciones) {
            xm = x0 - (y0 * y0d) / diferencia;
            y0 = funcion.getValue(xm);
            y0d = derivada.getValue(xm);
            y02d = derivada2.getValue(xm);
            error = Math.abs(xm - x0);
            if (tipoError == 2) error = Math.abs(error / xm);
            x0 = xm;
            diferencia = Math.pow(y0d, 2) - y0 * y02d;
            n++;
            Impresion.raicesMultiples(n, x0, y0, y0d, y02d, error);
        }
        if (y0 == 0 || error <= tolerancia)
            return new Intervalo<Double, Double>(x0, error);
        throw new ExcepcionRaiz();
    }
}
