package com.eafit.numericalanalysis.metodos.interpolacion;

import java.util.Scanner;

public class Lagrange{

	public static String[] evaluar(double[] x){
		int n = x.length;
		String[] l = new String[n];;
		String numerador, denominador;
		double denominadorNum;

		for(int i=0; i<n; i++){
			numerador = "";
			denominador = "";
			denominadorNum = 1;

			for(int j=0; j<n; j++){
				if(j!=i){
					numerador+="(x-("+String.valueOf(x[j])+"))";
					denominadorNum *= x[i]-x[j];
				}
			}
			denominador = String.valueOf(denominadorNum);
			l[i] = numerador+"/"+denominador;
		}

		return l;
	}
}
