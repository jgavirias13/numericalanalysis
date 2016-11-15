package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;

import com.eafit.numericalanalysis.estructuras.SalidaFactorizacionCholesky;
import com.eafit.numericalanalysis.excepciones.ExcepcionNoReales;
import com.eafit.numericalanalysis.excepciones.ExcepcionNoSoluble;

public class Cholesky{
	
	public static SalidaFactorizacionCholesky evaluar(double[][] a, double[] b) throws ExcepcionNoSoluble,ExcepcionNoReales{
		//Hallar LU
		int n = a.length;
		double[][] l = new double[n][n];
		double[][] u = new double[n][n];
		SalidaFactorizacionCholesky salida = new SalidaFactorizacionCholesky(n);

		//Iniciliazacion de matrices
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				l[i][j] = 0;
				u[i][j] = 0;
			}
		}
		salida.addEtapa(l,u);
		double determinante = 1;
		for(int k=0; k<n; k++){
			//Hallar la diagonal
			l[k][k] = a[k][k];
			for(int j=0; j<k; j++){
				l[k][k] -= l[k][j]*u[j][k];
			}
			if(l[k][k] < 0)
				throw new ExcepcionNoReales();
			l[k][k] = u[k][k] = Math.sqrt(l[k][k]);
			determinante *= l[k][k]*u[k][k];
			
			//Hallar l
			if(u[k][k] == 0)
				throw new ExcepcionNoSoluble();
			for(int j=k+1; j<n; j++){
				l[j][k] = a[j][k];
				for(int i=0; i<k; i++){
					l[j][k] -= l[j][i]*u[i][k];
				}
				l[j][k] /= u[k][k];
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
