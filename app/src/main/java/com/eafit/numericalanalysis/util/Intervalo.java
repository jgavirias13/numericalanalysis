/**
 *Clase para representar un intervalo
 *@autor: Juan Pablo Gaviria, Linda Gutierrez, Santiago Gallego
 */
package com.eafit.numericalanalysis.util;

public class Intervalo<X, Y>{
	public final X x;
	public final Y y;
	public Intervalo(X x, Y y){
		this.x = x;
		this.y = y;
	}

	public String toString(){
		return "["+String.format("%.5g",this.x)+", "+String.format("%.5g",this.y)+"]";
	}
}
