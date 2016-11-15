package com.eafit.numericalanalysis.metodos.interpolacion;

import java.util.Scanner;

public class LagrangeCalculo{

	public static double evaluar(double[] x, double[] y, double valor){
		int n = x.length;
		double numerador, denominador, resultado;
		resultado = 0;

		for(int i=0; i<n; i++){
			numerador = 1;
			denominador = 1;

			for(int j=0; j<n; j++){
				if(j!=i){
					numerador *= valor-x[j];
					denominador *= x[i]-x[j];
				}
			}
			resultado += (numerador/denominador)*y[i];
		}

		return resultado;
	}
}
