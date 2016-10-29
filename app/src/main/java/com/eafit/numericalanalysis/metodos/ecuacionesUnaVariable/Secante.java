package com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable;

import java.lang.Exception;

import com.eafit.numericalanalysis.estructuras.SalidaSecante;
import com.eafit.numericalanalysis.excepciones.ExcepcionEvaluacion;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionParser;
import com.eafit.numericalanalysis.excepciones.ExcepcionRaicesMultiples;
import com.eafit.numericalanalysis.excepciones.ExcepcionRaiz;
import com.eafit.numericalanalysis.excepciones.ExcepcionTolerancia;
import com.eafit.numericalanalysis.util.Parser;
import com.eafit.numericalanalysis.util.Intervalo;
import com.eafit.numericalanalysis.util.Impresion;

public class Secante {

    public static SalidaSecante evaluar(double x0, double x1,
                                        double tol, int iter,
                                        Parser funcion,
                                        int tipoError)
            throws ExcepcionTolerancia, ExcepcionIteraciones, ExcepcionParser, ExcepcionEvaluacion, ExcepcionRaicesMultiples, ExcepcionRaiz {

        SalidaSecante salida = new SalidaSecante();
        //Comprobacion de los datos
        if (tol < 0)
            throw new ExcepcionTolerancia();
        if (iter <= 0)
            throw new ExcepcionIteraciones();

        double y0, error, d, y1, xn;
        int cont;
        y0 = funcion.getValue(x0);
        if (y0 == 0)
            salida.setRespuesta(x0, 0d);

        y1 = funcion.getValue(x1);
        cont = 0;
        error = tol + 1;
        d = y1 - y0;
        salida.agregar(-1, x0, y0, 0);
        salida.agregar(cont, x1, y1, 0);

        while (y1 != 0 && error > tol && d != 0 && cont < iter) {
            xn = x1 - (y1 * (x1 - x0) / d);
            x0 = x1;
            x1 = xn;
            y0 = y1;
            y1 = funcion.getValue(x1);
            d = y1 - y0;
            error = Math.abs(x1 - x0);
            if (tipoError == 0) error = Math.abs(error / x1);
            cont++;
            salida.agregar(cont, x1, y1, error);
        }
        if (y1 == 0 || error < tol) {
            salida.setRespuesta(x1, error);
            return salida;
        }
        if (d == 0)
            throw new ExcepcionRaicesMultiples();
        throw new ExcepcionRaiz();
    }
}
