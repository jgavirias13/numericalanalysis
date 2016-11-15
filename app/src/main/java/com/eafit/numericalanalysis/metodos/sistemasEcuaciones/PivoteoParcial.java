package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;

import com.eafit.numericalanalysis.estructuras.SalidaPivoteoParcial;
import com.eafit.numericalanalysis.excepciones.ExcepcionNoSoluble;

import java.util.Scanner;

public class PivoteoParcial{

	public static SalidaPivoteoParcial evaluar(double matriz[][]) throws ExcepcionNoSoluble{
		int n = matriz.length, posicion, fila;
		double acumulador, mayor;
		double[] auxiliar = new double[n+1];
        double[] multiplicador = new double[n];
        for(int i=0; i<n; i++)
            multiplicador[i] = 0;
		mayor = 0;
		SalidaPivoteoParcial salida = new SalidaPivoteoParcial(n);
        salida.addEtapa(matriz,multiplicador,false,"",-1);
		//Proceso para llegar a la matriz triangular superior
		for(int k=0; k < n-1; k++){
            for(int i=0; i<n; i++)
                multiplicador[i] = 0;
			mayor = 0;
			fila = k;
			for(int i=k; i < n; i++)
				if(Math.abs(matriz[i][k]) > mayor){
					mayor = Math.abs(matriz[i][k]);
					fila = i;
				}

			//Cambio de fila
            if(k != fila) {
                System.arraycopy(matriz[k], 0, auxiliar, 0, n + 1);
                System.arraycopy(matriz[fila], 0, matriz[k], 0, n + 1);
                System.arraycopy(auxiliar, 0, matriz[fila], 0, n + 1);
                salida.addEtapa(matriz,multiplicador,true,k+" - "+fila,k);
            }
			
			for(int i=k+1; i < n; i++){
                if(matriz[k][k] == 0)
                    throw new ExcepcionNoSoluble();
				multiplicador[i] = matriz[i][k] / matriz[k][k];
				for(int j=k; j<n+1; j++){
					matriz[i][j] = matriz[i][j]-multiplicador[i]*matriz[k][j];
				}
			}
            salida.addEtapa(matriz,multiplicador,false,"",k);
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
