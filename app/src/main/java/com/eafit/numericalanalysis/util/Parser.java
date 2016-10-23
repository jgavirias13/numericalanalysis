package com.eafit.numericalanalysis.util;

import com.eafit.numericalanalysis.excepciones.ExcepcionEvaluacion;
import com.eafit.numericalanalysis.excepciones.ExcepcionParser;

import java.util.StringTokenizer;
import java.util.Stack;

public class Parser
{
	private static final int EOL = 0;
	private static final int VALUE = 1;
	private static final int OPAREN = 2;
	private static final int CPAREN = 3;
	private static final int EXP = 4;
	private static final int MULT = 5;
	private static final int DIV = 6;
	private static final int PLUS = 7;
	private static final int MINUS = 8;
	private static final int FUNCT = 9;
	private static String[] function =
	 {
		 "sqrt","sen",
		 "cos","tan",
		 "asen","acos",
		 "atan","log",
		 "floor","e"
	 };
	private String string;
	private Stack opStack;
	private Stack postfixStack;
	private StringTokenizer str;

	public double getValue(double x) throws ExcepcionParser, ExcepcionEvaluacion {
		return getValue(x,0,0);
	}

	public double getValue(double x,double y) throws ExcepcionParser, ExcepcionEvaluacion {
		return getValue(x,y,0);
	}

	public double getValue(double x,double y,double z) throws ExcepcionParser, ExcepcionEvaluacion {
		opStack = new Stack( );
		postfixStack = new Stack( );
		str = new StringTokenizer(string,"+*-/^()xyz ",true);
		opStack.push( new Integer( EOL ) );
		EvalTokenizer tok = new EvalTokenizer(str,x,y,z);
		Token lastToken;
		do{
			lastToken = tok.getToken( );
			processToken( lastToken );
		} while( lastToken.getType( ) != EOL );
		if( postfixStack.isEmpty( ) ){
			throw new ExcepcionParser();
		}
		double theResult = postFixTopAndPop( );
		if( !postfixStack.isEmpty( ) )
			throw new ExcepcionParser();
		return theResult;
	}

	private String unary2Binary(String s){
		int i;
		s = s.trim();
		if(s.charAt(0) == '-')
			s = "0.0"+s;
		while((i = s.indexOf("(-"))>=0)
			s = s.substring(0,i+1)+"0.0"+s.substring(i+1);
		return s;
	}

	private double postFixTopAndPop( ){
		return ((Double)(postfixStack.pop())).doubleValue( );
	}

	private int opStackTop( ){
		return ( (Integer) ( opStack.peek( ) ) ).intValue( );
	}

	private void processToken( Token lastToken ) throws ExcepcionParser, ExcepcionEvaluacion {
		int topOp;
		int lastType = lastToken.getType();
		switch(lastType){
		case VALUE:
			postfixStack.push( new Double( lastToken.getValue( ) ) );
			return;
		case CPAREN:
			while((topOp = opStackTop( ) ) != OPAREN && topOp!= EOL)
				binaryOp( topOp );
			if( topOp == OPAREN )
				opStack.pop( ); // Get rid of opening parentheseis
			else
				throw new ExcepcionParser();
			break;
		default: // General operator case
			int last=(lastType>=FUNCT?FUNCT:lastType);
			while(precTable[last].inputSymbol<=
						precTable[opStackTop()>=FUNCT?FUNCT:opStackTop()].topOfStack)
				binaryOp( opStackTop() );
			if( lastType != EOL )
				opStack.push( new Integer( lastType ));
			break;
		}
	}

	private double getTop( ) throws ExcepcionParser {
		if ( postfixStack.isEmpty( ) ){
			throw new ExcepcionParser();
		}
		return postFixTopAndPop( );
	}

	private static double pow( double x, double n ) throws ExcepcionEvaluacion {
		if( x == 0 ){
			if( n == 0 )
				throw new ExcepcionEvaluacion();
			return 0;
		}
		return Math.pow(x,n);
	}

	private void binaryOp( int topOp ) throws ExcepcionParser, ExcepcionEvaluacion {
		if( topOp == OPAREN ){
			throw new ExcepcionParser();
		}
		if(topOp >= FUNCT ){
			double d=getTop();
			postfixStack.push(new
												Double(functionEval(topOp, d)));
			opStack.pop( );
			return;
		}
		double rhs = getTop( );
		double lhs = getTop( );
		if( topOp == EXP )
			postfixStack.push( new Double(pow(lhs,rhs)));
		else if( topOp == PLUS )
			postfixStack.push( new Double(lhs + rhs) );
		else if( topOp == MINUS )
			postfixStack.push( new Double(lhs - rhs) );
		else if( topOp == MULT )
			postfixStack.push( new Double(lhs * rhs) );
		else if( topOp == DIV )
			if( rhs != 0 )
				postfixStack.push( new Double(lhs / rhs) );
			else{
				throw new ExcepcionEvaluacion();
			}
		opStack.pop();
	}

	private double functionEval(int topOp, double d)
	{
		double y = 0;
		switch (topOp) {
		case 9:
			y = Math.sqrt(d); break;
		case 10:
			y = Math.sin(d); break;
		case 11:
			y = Math.cos(d); break;
		case 12:
			y = Math.tan(d); break;
		case 13:
			y = Math.asin(d); break;
		case 14:
			y = Math.acos(d); break;
		case 15:
			y = Math.atan(d); break;
		case 16:
			y = Math.log(d); break;
		case 17:
			y = Math.floor(d); break;
		case 18:
			y = Math.exp(d);
		}
		return y;
	}

	public Parser( String s )
	{
		opStack = new Stack( );
		postfixStack = new Stack( );
		string = unary2Binary(s);
		str = new StringTokenizer(string,"+*-/^()xyz ",true);
		opStack.push( new Integer( EOL ) );
	}

	private static class Precedence{
		public int inputSymbol;
		public int topOfStack;
		public Precedence( int inSymbol, int topSymbol){
			inputSymbol = inSymbol;
			topOfStack = topSymbol;
		}
	}
	
	 private static Precedence [ ] precTable = new Precedence[ ]{
		 new Precedence( 0, -1 ), // EOL
		 new Precedence( 0, 0 ), // VALUE
		 new Precedence( 100, 0 ), // OPAREN
		 new Precedence( 0, 99 ), // CPAREN
		 new Precedence( 6, 5 ), // EXP
		 new Precedence( 3, 4 ), // MULT
		 new Precedence( 3, 4 ), // DIV
		 new Precedence( 1, 2 ), // PLUS
		 new Precedence( 1, 2 ), // MINUS
		 new Precedence( 7, 6 ) // FUNCT
	 };

	private static class Token{

		public Token( ){
			this( EOL );
		}
		
		public Token( int t ){
			this( t, 0 );
		}
		
		public Token( int t, double v ){
			type = t;
			value = v;
		}
		
		public int getType( ){
			return type;
		}
		
		public double getValue( ){
			return value;
		}
		
		private int type = EOL;
		private double value = 0;
		
	}

	private static class EvalTokenizer{
		public EvalTokenizer( StringTokenizer is,double x,
													double y,double z){
			str = is;
			equis=x;
			ye=y;
			zeta=z;
		}
		
		public Token getToken( ) throws ExcepcionParser {
			double theValue;
			if(!str.hasMoreTokens( ))
				return new Token( );
			String s = str.nextToken();
			if( s.equals( " " ) ) return getToken( );
			if( s.equals( "^" ) ) return new Token(EXP);
			if( s.equals( "/" ) ) return new Token(DIV);
			if( s.equals( "*" ) ) return new Token(MULT);
			if( s.equals( "(" ) ) return new Token(OPAREN);
			if( s.equals( ")" ) ) return new Token(CPAREN);
			if( s.equals( "+" ) ) return new Token(PLUS);
			if( s.equals( "-" ) ) return new Token(MINUS);
			if( s.equals( "x" ) ) return new Token(VALUE,equis);
			if( s.equals( "y" ) ) return new Token(VALUE,ye);
			if( s.equals( "z" ) ) return new Token(VALUE,zeta);
			if(Character.isLetter(s.charAt(0))){
				int i=searchFunction(s);
				if(i>=0)
					return new Token(FUNCT+i);
				else{
					throw new ExcepcionParser();
				}
			}
			try
				{
					theValue = Double.valueOf(s).doubleValue();
				}
			catch( NumberFormatException e )
				{
					throw new ExcepcionParser();
				}
			return new Token( VALUE, theValue );
		}
		
		public int searchFunction(String s){
			for(int i=0;i<function.length;i++)
				if(s.equals(function[i]))
					return i;
			return -1;
		}
		private StringTokenizer str;
		private double equis;
		private double ye;
		private double zeta;
	}
}

