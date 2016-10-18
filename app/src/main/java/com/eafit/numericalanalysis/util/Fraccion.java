package com.eafit.numericalanalysis.util;


public class Fraccion{
	public long denominador;
	public long numerador;

	public Fraccion(long  numerador, long denominador){
		this.numerador = numerador;
		this.denominador = denominador;
	}

	public Fraccion(long numerador){
		this.numerador = numerador;
		this.denominador = 1;
	}

	public double resolver(){
		return this.numerador/(double)this.denominador;
	}

	public String toString(){
		return this.numerador+"/"+this.denominador;
	}

	private long mcd(){
		long u = Math.abs(this.numerador);
		long v = Math.abs(this.denominador);
		if(v == 0)
			return 1;
		long r;
		while(v != 0){
			r = u%v;
			u = v;
			v = r;
		}
		return u;
	}
	
	public void simplificar(){
		long dividir = mcd();
		this.numerador /= dividir;
		this.denominador /= dividir;
	}

	public static Fraccion sumar(Fraccion uno, Fraccion dos){
		long numerador = uno.numerador*dos.denominador;
		numerador += dos.numerador*uno.denominador;
		long denominador = uno.denominador*dos.denominador;
		Fraccion resultado = new Fraccion(numerador,denominador);
		resultado.simplificar();
		return resultado;
	}

	public static Fraccion restar(Fraccion uno, Fraccion dos){
		long numerador = uno.numerador*dos.denominador;
		numerador -= dos.numerador*uno.denominador;
		long denominador = uno.denominador*dos.denominador;
		Fraccion resultado = new Fraccion(numerador,denominador);
		resultado.simplificar();
		return resultado;
	}

	public static Fraccion multiplicar(Fraccion uno, Fraccion dos){
		long numerador = uno.numerador*dos.numerador;
		long denominador = uno.denominador*dos.denominador;
		Fraccion resultado = new Fraccion(numerador,denominador);
		resultado.simplificar();
		return resultado;
	}

	public static Fraccion dividir(Fraccion uno, Fraccion dos){
		long numerador = uno.numerador*dos.denominador;
		long denominador = uno.denominador*dos.numerador;
		Fraccion resultado = new Fraccion(numerador,denominador);
		resultado.simplificar();
		return resultado;
	}
}
