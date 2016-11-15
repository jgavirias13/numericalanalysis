package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;

import com.eafit.numericalanalysis.estructuras.SalidaGaussSimple;
import com.eafit.numericalanalysis.excepciones.ExcepcionNoSoluble;

public class EliminacionGaussiana {

	public static SalidaGaussSimple evaluar(double[][] matriz) throws ExcepcionNoSoluble{
		int n = matriz.length;
		double acumulador;
		double[] multiplicador = new double[n];
		for(int i=0; i<n; i++){
			multiplicador[i] = 0;
		}
		SalidaGaussSimple salida = new SalidaGaussSimple(n);
		salida.addEtapa(matriz,multiplicador);

		//Proceso para llegar a la matriz triangular superior
		for(int k=0; k < n-1; k++){
			for(int i=0; i<n; i++){
				multiplicador[i] = 0;
			}
			for(int i=k+1; i < n; i++){
				if(matriz[k][k] == 0) throw new ExcepcionNoSoluble();
				multiplicador[i] = matriz[i][k] / matriz[k][k];
				for(int j=k; j<n+1; j++){
					matriz[i][j] = matriz[i][j]-multiplicador[i]*matriz[k][j];
				}
			}
			salida.addEtapa(matriz,multiplicador);
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
