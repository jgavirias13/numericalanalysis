package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;

import com.eafit.numericalanalysis.estructuras.SalidaPivoteoEscalonado;
import com.eafit.numericalanalysis.excepciones.ExcepcionNoSoluble;

public class PivoteoEscalonado{

	public static SalidaPivoteoEscalonado evaluar(double matriz[][]) throws ExcepcionNoSoluble{
		int n = matriz.length, posicion;
		double acumulador, mayor;
		double[] mayoresFilas = new double[n];
		double[] indice = new double[n];
		double[] auxiliar = new double[n];
		double[] multiplicador = new double[n];
		SalidaPivoteoEscalonado salida = new SalidaPivoteoEscalonado(n);

		for(int i = 0; i < n; i++){
			mayor = 0;
			for(int j = 0; j < n; j++){
				if(Math.abs(matriz[i][j]) > mayor)
					 mayor = Math.abs(matriz[i][j]);
			}
			mayoresFilas[i] = mayor;
			System.out.println(mayor);
			indice[i] = i;
			multiplicador[i] = 0;
		}

		salida.addEtapa(matriz,multiplicador,0,"",-1);
				
		//Proceso para llegar a la matriz triangular superior
		for(int k=0; k < n-1; k++){
			mayor = 0;
			posicion = k;
			for(int i=k; i < n; i++){
				if(Math.abs(matriz[i][k]/mayoresFilas[i])>mayor){
					mayor = Math.abs(matriz[i][k]/mayoresFilas[i]);
					posicion = i;
				}
			}
			mayor = mayoresFilas[k];
			mayoresFilas[k] = mayoresFilas[posicion];
			mayoresFilas[posicion] = mayor;

			auxiliar = matriz[k];
			matriz[k] = matriz[posicion];
			matriz[posicion] = auxiliar;
			salida.addEtapa(matriz,multiplicador,1,k+" - "+posicion,k);
			
			for(int i=k+1; i < n; i++){
				if(matriz[k][k] == 0)
					throw new ExcepcionNoSoluble();
				multiplicador[i] = matriz[i][k] / matriz[k][k];
				for(int j=k; j<n+1; j++){
					matriz[i][j] = matriz[i][j]-multiplicador[i]*matriz[k][j];
				}
			}
			salida.addEtapa(matriz,multiplicador,0,"",k);
		}

		//Proceso para despejar las variables
		double[] x = new double[n];
		for(int i=n-1; i >= 0; i--){
			acumulador = 0;
			for(int j=i+1; j < n; j++){
				acumulador += x[j]*matriz[i][j];
			}
			x[i] = (matriz[i][n] - acumulador)/matriz[i][i];
		}

		salida.setResultado(x);
		return salida;
	}

}
