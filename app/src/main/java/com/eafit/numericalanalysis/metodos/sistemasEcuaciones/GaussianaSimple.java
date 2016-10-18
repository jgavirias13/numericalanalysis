package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;

import com.eafit.numericalanalysis.util.Fraccion;

public class GaussianaSimple {

    public static double[] evaluar(Fraccion a[][], int n) {
        Fraccion aux, acumulador;
        Fraccion m[] = new Fraccion[n];
        for (int k = 0; k < n - 1; k++) {
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
