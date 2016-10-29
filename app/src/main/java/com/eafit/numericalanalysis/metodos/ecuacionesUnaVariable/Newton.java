package com.eafit.numericalanalysis.metodos.ecuacionesUnaVariable;

import com.eafit.numericalanalysis.estructuras.SalidaNewton;
import com.eafit.numericalanalysis.excepciones.ExcepcionEvaluacion;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionParser;
import com.eafit.numericalanalysis.excepciones.ExcepcionRaiz;
import com.eafit.numericalanalysis.excepciones.ExcepcionTolerancia;
import com.eafit.numericalanalysis.util.Intervalo;
import com.eafit.numericalanalysis.util.Parser;
import com.eafit.numericalanalysis.util.Impresion;

import java.lang.Exception;

public class Newton {

    public static SalidaNewton evaluar(double x0,
                                       double tolerancia,
                                       double iteraciones,
                                       Parser funcionF,
                                       Parser derivadaF,
                                       int tipoError)
            throws ExcepcionTolerancia, ExcepcionIteraciones, ExcepcionParser, ExcepcionEvaluacion, ExcepcionRaiz {

        SalidaNewton salida = new SalidaNewton();

        //Se revisan los datos entrados
        if (tolerancia < 0)
            throw new ExcepcionTolerancia();
        if (iteraciones <= 0)
            throw new ExcepcionIteraciones();

        int n = 1;
        double error, y0, yd0, xm;
        error = tolerancia + 1;
        y0 = funcionF.getValue(x0);
        yd0 = derivadaF.getValue(x0);
        salida.agregar(n, x0, y0, yd0, error);

        while (y0 != 0 && error > tolerancia && yd0 != 0 && n < iteraciones) {
            xm = x0 - y0 / yd0;
            error = Math.abs(xm - x0);
            if (tipoError == 2) error = Math.abs(error / xm);
            x0 = xm;
            y0 = funcionF.getValue(x0);
            yd0 = derivadaF.getValue(x0);
            n++;
            salida.agregar(n, x0, y0, yd0, error);
        }

        if (y0 == 0 || error <= tolerancia) {
            salida.setRespuesta(x0, error);
            return salida;
        }
        throw new ExcepcionRaiz();
    }
}
