package com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable;

import com.eafit.numericalanalysis.excepciones.ExcepcionDelta;
import com.eafit.numericalanalysis.excepciones.ExcepcionFinIntervalo;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.util.Intervalo;
import com.eafit.numericalanalysis.util.Parser;
import com.eafit.numericalanalysis.util.Impresion;

import java.lang.Exception;

public class BusquedasIncrementales {
    /**
     * Metodo para realizar la busqueda de un intervalo segun datos ingresados
     * por el usuario
     *
     * @return String con la salida del metodo
     */
    public static Intervalo<Double, Double> evaluar(double x0, double delta,
                                                    int iteraciones,
                                                    Parser funcionF)
            throws Exception {

        if (delta == 0)
            throw new ExcepcionDelta();
        if (iteraciones <= 0)
            throw new ExcepcionIteraciones();
        Impresion.encabezadoBusquedaIncremental();
        int contador, iter;
        double y0, y1, x1;
        y0 = funcionF.getValue(x0);
        Impresion.busquedaIncremental(0, x0, y0);
        if (y0 != 0) {
            x1 = x0 + delta;
            y1 = funcionF.getValue(x1);
            contador = 1;
            Impresion.busquedaIncremental(contador, x1, y1);
            while (y0 * y1 > 0 && contador < iteraciones) {
                x0 = x1;
                y0 = y1;
                x1 = x0 + delta;
                y1 = funcionF.getValue(x1);
                contador++;
                Impresion.busquedaIncremental(contador, x1, y1);
            }
            if (y1 == 0) {
                return new Intervalo<Double, Double>(x1, x1);
            } else {
                if (y0 * y1 < 0) {
                    return new Intervalo<Double, Double>(x0, x1);
                } else {
                    throw new ExcepcionFinIntervalo();
                }
            }
        } else {
            return new Intervalo<Double, Double>(x0, x0);
        }
    }
}
