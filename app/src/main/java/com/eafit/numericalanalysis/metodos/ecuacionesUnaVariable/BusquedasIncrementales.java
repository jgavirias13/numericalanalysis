package com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable;

import com.eafit.numericalanalysis.estructuras.SalidaBusquedasIncrementales;
import com.eafit.numericalanalysis.excepciones.ExcepcionDelta;
import com.eafit.numericalanalysis.excepciones.ExcepcionEvaluacion;
import com.eafit.numericalanalysis.excepciones.ExcepcionFinIntervalo;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionParser;
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
    public static SalidaBusquedasIncrementales evaluar(double x0, double delta,
                                                       int iteraciones,
                                                       Parser funcionF)
            throws ExcepcionFinIntervalo, ExcepcionDelta, ExcepcionIteraciones, ExcepcionParser, ExcepcionEvaluacion {

        SalidaBusquedasIncrementales salida = new SalidaBusquedasIncrementales();

        if (delta == 0)
            throw new ExcepcionDelta();
        if (iteraciones <= 0)
            throw new ExcepcionIteraciones();
        int contador;
        double y0, y1, x1;
        y0 = funcionF.getValue(x0);
        salida.agregar(0, x0, y0);
        if (y0 != 0) {
            x1 = x0 + delta;
            y1 = funcionF.getValue(x1);
            contador = 1;
            salida.agregar(contador, x1, y1);
            while (y0 * y1 > 0 && contador < iteraciones) {
                x0 = x1;
                y0 = y1;
                x1 = x0 + delta;
                y1 = funcionF.getValue(x1);
                contador++;
                salida.agregar(contador, x1, y1);
            }
            if (y1 == 0) {
                salida.setRespuesta(x1,x1);
                return salida;
            } else {
                if (y0 * y1 < 0) {
                    salida.setRespuesta(x0,x1);
                    return salida;
                } else {
                    throw new ExcepcionFinIntervalo();
                }
            }
        } else {
            salida.setRespuesta(x0,x0);
            return salida;
        }
    }
}
