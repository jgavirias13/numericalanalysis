package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;


import com.eafit.numericalanalysis.estructuras.SalidaFactorizacionGaussina;
import com.eafit.numericalanalysis.excepciones.ExcepcionNoSoluble;

public class FactorizacionGaussiana{

	public static SalidaFactorizacionGaussina evaluar(double[][] a, double[] b) throws ExcepcionNoSoluble{

		int n = a.length;
		double multiplicador, acumulador;
		SalidaFactorizacionGaussina salida = new SalidaFactorizacionGaussina(n);
		double[][] u = new double[n][n];

		//Proceso para hallar l y u
		double[][] l = new double[n][n];
		for(int i=0; i<n; i++) {
			for (int j = 0; j < n; j++) {
				l[i][j] = 0;
				u[i][j] = a[i][j];
			}
		}
		salida.addEtapa(l,u);

		for(int k=0; k < n; k++){
			l[k][k] = 1;
			for(int i=k+1; i < n; i++){
				if(u[k][k] == 0)
					throw new ExcepcionNoSoluble();
				multiplicador = u[i][k] / u[k][k];
				l[i][k] = multiplicador;
				for(int j=k; j<n; j++){
					u[i][j] = u[i][j]-multiplicador*u[k][j];
				}
			}
			salida.addEtapa(l,u);
		}

		//Sustitucion progresiva para hallar z
		double[] z = new double[n];
		for(int i = 0; i < n; i++){
			acumulador = 0;
			for(int j = 0; j < i; j++){
				acumulador += z[j]*l[i][j];
			}
			z[i] = b[i]-acumulador;
		}
		salida.setZ(z);

		//Sustitucion regresiva para hallar x
		double[] x = new double[n];
		for(int i = n-1; i >= 0; i--){
			acumulador = 0;
			for(int j = i+1; j < n; j++){
				acumulador += x[j]*u[i][j];
			}
			x[i] = (z[i]-acumulador)/u[i][i];
		}
		salida.setX(x);

		return salida;
	}
}
