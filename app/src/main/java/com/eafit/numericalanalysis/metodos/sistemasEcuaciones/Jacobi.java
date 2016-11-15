package com.eafit.numericalanalysis.metodos.sistemasEcuaciones;

import com.eafit.numericalanalysis.estructuras.SalidaGaussSeidel;
import com.eafit.numericalanalysis.excepciones.ExcepcionIteraciones;
import com.eafit.numericalanalysis.excepciones.ExcepcionTolerancia;

public class Jacobi{

	public static SalidaGaussSeidel evaluar(double [][] a, double b[], double xant[],
											int iteraciones, double tolerancia) throws ExcepcionIteraciones, ExcepcionTolerancia {
        if(iteraciones <= 0){
            throw new ExcepcionIteraciones();
        }
        if(tolerancia < 0){
            throw new ExcepcionTolerancia();
        }
		int n = a.length;
		double[] x = new double[n];
		double error = tolerancia+1;
		double errores[] = new double[n];
		SalidaGaussSeidel salida = new SalidaGaussSeidel();

		for(int k=0; k<iteraciones && error > tolerancia; k++){
			salida.addEtapa(xant,error);
			
			for(int i=0; i<n; i++){
				x[i] = b[i];
				for(int j=0; j<i; j++){
					x[i] -= a[i][j]*xant[j];
				}
				for(int j=i+1; j<n; j++){
					x[i] -= a[i][j]*xant[j];
				}
				x[i] /= a[i][i];
				errores[i] = x[i]-xant[i];
			}
			double mayorError = 0;
			double mayorX = 0;
			for(int i=0; i<n; i++){
				if(Math.abs(errores[i]) > mayorError)
					mayorError = Math.abs(errores[i]);
				if(Math.abs(x[i]) > mayorX)
					mayorX = Math.abs(x[i]);
			}
			error = mayorError/mayorX;
			System.arraycopy(x,0,xant,0,n);
		}

		return salida;
	}

}
