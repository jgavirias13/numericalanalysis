package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;

import com.eafit.numericalanalysis.estructuras.SalidaFactorizacionPivoteo;
import com.eafit.numericalanalysis.excepciones.ExcepcionNoSoluble;

public class FactorizacionPivoteo{

	public static SalidaFactorizacionPivoteo evaluar(double[][] a, double[] b) throws ExcepcionNoSoluble{

		int n = a.length, fila;
		double multiplicador, acumulador,mayor;
		double[] auxiliar = new double[n];
        double[][] u = new double[n][n];
        double[] newB = new double[n];
		SalidaFactorizacionPivoteo salida = new SalidaFactorizacionPivoteo(n);

		//Proceso para hallar l y u
		double[][] l = new double[n][n];
		for(int i=0; i<n; i++) {
            for (int j = 0; j < n; j++) {
                l[i][j] = 0;
                u[i][j] = a[i][j];
            }
            newB[i] = b[i];
        }
		salida.addEtapa(l,u);

		for(int k=0; k < n; k++){
			mayor = 0;
			fila = k;
			for(int i=k; i<n; i++){
				if(Math.abs(u[i][k]) > mayor){
					mayor = Math.abs(u[i][k]);
					fila = i;
				}
			}
			auxiliar = u[k];
			u[k] = u[fila];
			u[fila] = auxiliar;
			mayor = newB[k];
			newB[k] = newB[fila];
			newB[fila] = mayor;
			
			l[k][k] = 1;
			for(int i=k+1; i < n; i++){
                if(u[k][k] == 0)
                    throw new ExcepcionNoSoluble();
				multiplicador = u[i][k] / u[k][k];
				l[i][k] = multiplicador;
				for(int j=k; j<n; j++){
					u[i][j] = u[i][j]-multiplicador*u[k][j];
				}
				newB[i] = newB[i]-multiplicador*newB[k];
			}
			salida.addEtapa(l,u);
		}

		//Sustitucion progresiva para hallar z
		double[] z;
		z = newB;
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
