package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;

import com.eafit.numericalanalysis.estructuras.SalidaFactorizacionDoolittle;
import com.eafit.numericalanalysis.excepciones.ExcepcionNoSoluble;

import java.util.Scanner;

public class Doolittle{

	public static SalidaFactorizacionDoolittle evaluar(double[][] a, double[] b) throws ExcepcionNoSoluble{
		//Hallar LU
		int n = a.length;
		double[][] l = new double[n][n];
		double[][] u = new double[n][n];
		SalidaFactorizacionDoolittle salida = new SalidaFactorizacionDoolittle(n);

		//Iniciliazacion de matrices
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				l[i][j] = 0;
				u[i][j] = 0;
			}
			l[i][i] = 1;
		}
		salida.addEtapa(l,u);
		double determinante = 1;
		for(int k=0; k<n; k++){
			//Hallar u
			for(int j=k; j<n; j++){
				u[k][j] = a[k][j];
				for(int i=0; i<k; i++){
					u[k][j] -= u[i][j]*l[k][i];
				}
			}
			if(u[k][k] == 0)
				throw new ExcepcionNoSoluble();
			//Hallar l
			for(int j=k+1; j<n; j++){
				l[j][k] = a[j][k];
				for(int i=0; i<k; i++){
					l[j][k] -= u[i][k]*l[j][i];
				}
				l[j][k] /= u[k][k];
			}
			salida.addEtapa(l,u);
			determinante *= l[k][k]*u[k][k];
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
