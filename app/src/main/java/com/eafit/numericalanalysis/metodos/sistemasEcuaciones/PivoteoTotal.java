package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;

import com.eafit.numericalanalysis.estructuras.SalidaPivoteoTotal;
import com.eafit.numericalanalysis.excepciones.ExcepcionNoSoluble;

public class PivoteoTotal{

	public static SalidaPivoteoTotal evaluar(double matriz[][]) throws ExcepcionNoSoluble{
		int n = matriz.length, posicion, fila, columna, auxiliarI;
		double acumulador, mayor;
		int[] indices = new int[n];
		double[] multiplicador = new double[n];
		double[] auxiliar = new double[n];
		mayor = 0;
		for(int i = 0; i < n; i++){
			multiplicador[i] = 0;
			indices[i] = i;
		}
		SalidaPivoteoTotal salida = new SalidaPivoteoTotal(n);
		salida.addEtapa(matriz,multiplicador,0,"",-1);
				
		//Proceso para llegar a la matriz triangular superior
		for(int k=0; k < n-1; k++){
			for(int i = 0; i<n; i++)
				multiplicador[i] = 0;
			mayor = 0;
			fila = k;
			columna = k;
			for(int i=k; i < n; i++)
				for(int j=k; j < n; j++)
					if(Math.abs(matriz[i][j]) > mayor){
						mayor = Math.abs(matriz[i][j]);
						fila = i;
						columna = j;
					}

			if(k!=fila) {
				//Cambio de fila
				auxiliar = matriz[k];
				matriz[k] = matriz[fila];
				matriz[fila] = auxiliar;
				salida.addEtapa(matriz,multiplicador,1,k+" - "+fila,k);
			}

			if(k!=columna) {
				auxiliarI = indices[k];
				indices[k] = indices[columna];
				indices[columna] = auxiliarI;
				//Cambio de columna
				for (int i = 0; i < n; i++) {
					mayor = matriz[i][k];
					matriz[i][k] = matriz[i][columna];
					matriz[i][columna] = mayor;
				}
				salida.addEtapa(matriz,multiplicador,2,k+" - "+columna,k);
			}
			
			
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
				acumulador += x[indices[j]]*matriz[i][j];
			}
			x[indices[i]] = (matriz[i][n] - acumulador)/matriz[i][i];
		}
		salida.setResultado(x);
		return salida;
	}

}
