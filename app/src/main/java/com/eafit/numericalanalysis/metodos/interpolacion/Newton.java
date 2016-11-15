package com.eafit.numericalanalysis.metodos.interpolacion;

import com.eafit.numericalanalysis.estructuras.SalidaNewtonInterpolacion;


public class Newton{

	public static SalidaNewtonInterpolacion evaluar(double[] x, double[] y){
		int n = x.length;
		double[][] a = new double[n][n];
		double[] b = new double[n];
		SalidaNewtonInterpolacion salida = new SalidaNewtonInterpolacion(n);
		
		for(int i=0; i<n; i++){
			a[i][0] = y[i];
		}

		b[0] = a[0][0];
		
		for(int j=1; j<n; j++){
			for(int i=j; i<n; i++){
				a[i][j] = (a[i][j-1]-a[i-1][j-1])/(x[i]-x[i-j]);
			}
			b[j] = a[j][j];
		}
		salida.setProceso(a);
		salida.setResultado(b);
		return salida;
	}
}
