package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;

import com.eafit.numericalanalysis.estructuras.SalidaFactorizacionCrout;
import com.eafit.numericalanalysis.excepciones.ExcepcionNoSoluble;

public class Crout{
	
	public static SalidaFactorizacionCrout evaluar(double[][] a, double[] b) throws ExcepcionNoSoluble{
		//Hallar LU
		int n = a.length;
		double[][] l = new double[n][n];
		double[][] u = new double[n][n];
		SalidaFactorizacionCrout salida = new SalidaFactorizacionCrout(n);

		//Iniciliazacion de matrices
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				l[i][j] = 0;
				u[i][j] = 0;
			}
			u[i][i] = 1;
		}
		salida.addEtapa(l,u);
		double determinante = 1;
		for(int k=0; k<n; k++){
			//Hallar l
			for(int j=k; j<n; j++){
				l[j][k] = a[j][k];
				for(int i=0; i<k; i++){
					l[j][k] -= l[j][i]*u[i][k];
				}
			}
			if(l[k][k] == 0)
				throw new ExcepcionNoSoluble();
			//Hallar u
			for(int j=k+1; j<n; j++){
				u[k][j] = a[k][j];
				for(int i=0; i<k; i++){
					u[k][j] -= l[k][i]*u[i][j];
				}
				u[k][j] /= l[k][k];
			}
			salida.addEtapa(l,u);
			determinante *= l[k][k] * u[k][k];
		}
		
		//Sustitucion progresiva para hallar z
		double[] z = new double[n];
		double acumulador;
		for(int i = 0; i < n; i++){
			acumulador = 0;
			for(int j = 0; j < i; j++){
				acumulador += z[j]*l[i][j];
			}
			z[i] = b[i]-acumulador;
			z[i] /= l[i][i];
		}
		salida.setZ(z);
		salida.setDeterminante(determinante);
		
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
