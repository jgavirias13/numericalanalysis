package com.eafit.numericalanalysis.util;

public class Impresion{

	//Metodos para la impresion de busqueda incremental
	public static void encabezadoBusquedaIncremental(){
		System.out.println("  n  |     Xn     |     f(xn)     |");
	}
	
	public static void  busquedaIncremental(int n, double xn, double fxn){
		System.out.printf("%5d|%12e|%15e|\n", n, xn, fxn);
	}

	//Metodos para la impresion del metodo biseccion
	public static void encabezadoBiseccion(){
		System.out.println("  n  |     Xi     |     Xf     |     Xm     |"+
											 "     f(xm)     |    Error   |");
	}

	public static void biseccion(int n, double xi, double xf, double xm,
															 double fxm, double error){
		System.out.printf("%5d|%12e|%12e|%12e|%15e|%11e|\n",n,xi,xf,xm,fxm,error);
	}

	//Metodos para la impresion del metodo regla falsa
	public static void encabezadoReglaFalsa(){
		System.out.println("  n  |     Xi     |     Xf     |     Xm     |"+
											 "     f(xm)     |    Error   |");
	}

	public static void reglaFalsa(int n, double xi, double xf, double xm,
																double fxm, double error){
		System.out.printf("%5d|%12e|%12e|%12e|%15e|%11e|\n",n,xi,xf,xm,fxm,error);
	}

	//Metodos para la impresion del metodo punto fijo
	public static void encabezadoPuntoFijo(){
		System.out.println("  n  |     g(xn)     |     f(xn)     |   Error    |");
	}

	public static void puntoFijo(int n, double gxn, double fxn, double error){
		System.out.printf("%5d|%15e|%15e|%11e|\n",n,gxn,fxn,error);
	}

	//Metodos para la impresion del metodo Newton
	public static void encabezadoNewton(){
		System.out.println("  n  |     Xn     |     f(xn)     |     f'(xn)    |"+
											 "   Error    |");
	}

	public static void newton(int n, double x0, double y0, double yd0,
														double error){
		System.out.printf("%5d|%12e|%15e|%15e|%11e|\n",n,x0,y0,yd0,error);
	}

	//Metodos para la impresion del metodo Secante
	public static void encabezadoSecante(){
		System.out.println("  n  |     Xn     |     f(xn)     |   Error    |");
	}

	public static void secante(int n, double x0, double y0, double error){
		System.out.printf("%5d|%12e|%15e|%11e|\n",n,x0,y0,error);
	}

	//Metodos para la impresion del metodo de raices multiples
	public static void encabezadoRaicesMultiples(){
		System.out.println("  n  |     Xn     |     f(xn)     |     f'(xn)     |"+
											 "     f''(xn)     |   Error    |");
	}

	public static void raicesMultiples(int n, double x0, double y0, double yd0,
																		 double yd20, double error){
		System.out.printf("%5d|%12e|%15e|%16e|%17e|%11e|\n",n,x0,y0,yd0,yd20,error);
	}
}
