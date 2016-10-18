package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;

import com.eafit.numericalanalysis.excepciones.ExcepcionNoSoluble;
import com.eafit.numericalanalysis.util.Fraccion;
public class GaussPivoteoEscalonado {

    public static double[] evaluar(Fraccion a[][], int n)
            throws Exception {
        Fraccion aux, acumulador;
        Fraccion m[] = new Fraccion[n];
        double mayoresFilas[] = new double[n];
        double mayor, valor;
        int fila;

        //Se busca el mayor de cada fila
        for (int i = 0; i < n; i++) {
            mayor = 0;
            for (int j = 0; j < n; j++) {
                valor = Math.abs(a[i][j].resolver());
                if (valor > mayor) {
                    mayor = valor;
                }
            }
            mayoresFilas[i] = mayor;
        }

        for (int k = 0; k < n - 1; k++) {
            //Pivoteo
            //Se revisa la division mayor
            mayor = 0;
            fila = k;
            for (int i = k; i < n; i++) {
                valor = Math.abs(a[i][k].resolver() / mayoresFilas[i]);
                if (valor > mayor) {
                    mayor = valor;
                    fila = i;
                }
            }
            if (mayor == 0)
                throw new ExcepcionNoSoluble();
            if (k != fila) {
                Fraccion helper[] = a[k];
                a[k] = a[fila];
                a[fila] = helper;
                valor = mayoresFilas[k];
                mayoresFilas[k] = mayoresFilas[fila];
                mayoresFilas[fila] = valor;
            }


            for (int i = k + 1; i < n; i++) {
                m[i] = Fraccion.dividir(a[i][k], a[k][k]);
                for (int j = k; j < n + 1; j++) {
                    aux = Fraccion.multiplicar(m[i], a[k][j]);
                    a[i][j] = Fraccion.restar(a[i][j], aux);
                }
            }
        }
        Fraccion xFraccion[] = new Fraccion[n];
        for (int i = n - 1; i >= 0; i--) {
            acumulador = new Fraccion(0);
            for (int p = n - 1; p > i; p--) {
                aux = Fraccion.multiplicar(a[i][p], xFraccion[p]);
                acumulador = Fraccion.sumar(aux, acumulador);
            }
            aux = Fraccion.restar(a[i][n], acumulador);
            xFraccion[i] = Fraccion.dividir(aux, a[i][i]);
        }
        double xSolucion[] = new double[n];
        for (int i = 0; i < n; i++) {
            xSolucion[i] = xFraccion[i].resolver();
        }
        return xSolucion;
    }
}
