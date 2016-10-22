package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable;

import com.eafit.numericalanalysis.excepciones.ExcepcionEvaluacion;
import com.eafit.numericalanalysis.excepciones.ExcepcionParser;
import com.eafit.numericalanalysis.util.Parser;

/**
 * Created by jgavi on 20/10/2016.
 */

public class EstadoFunciones {

    private String funcion_f;
    private String funcion_g;
    private String derivada_f;
    private String derivada2_f;

    private static EstadoFunciones estado;

    private EstadoFunciones(){
        funcion_f = "";
        funcion_g= "";
        derivada2_f = "";
        derivada_f = "";
    }

    public static EstadoFunciones solicitarEstado(){
        if(estado == null){
            estado = new EstadoFunciones();
        }
        return estado;
    }

    public String getFuncion_f(){
        return this.funcion_f;
    }

    public String getFuncion_g(){
        return this.funcion_g;
    }

    public String getDerivada_f(){
        return this.derivada_f;
    }

    public String getDerivada2_f(){
        return this.derivada2_f;
    }

    public boolean setFuncion_f(String funcion_f){
        if(funcion_f.isEmpty()){
            this.funcion_f = funcion_f;
            return true;
        }
        try {
            new Parser(funcion_f).getValue(0);
        } catch (ExcepcionEvaluacion excepcionEvaluacion) {
            this.funcion_f = funcion_f;
            return true;
        } catch (ExcepcionParser excepcionParser) {
            return false;
        }
        this.funcion_f = funcion_f;
        return true;
    }

    public boolean setFuncion_g(String funcion_g){
        if(funcion_g.isEmpty()){
            this.funcion_g = funcion_g;
            return true;
        }
        try {
            new Parser(funcion_f).getValue(0);
        } catch (ExcepcionEvaluacion excepcionEvaluacion) {
            this.funcion_g = funcion_g;
            return true;
        } catch (ExcepcionParser excepcionParser) {
            return false;
        }
        this.funcion_g = funcion_g;
        return true;
    }

    public boolean setDerivada_f(String derivada_f){
        if(derivada_f.isEmpty()){
            this.derivada_f = derivada_f;
            return true;
        }
        try {
            new Parser(funcion_f).getValue(0);
        } catch (ExcepcionEvaluacion excepcionEvaluacion) {
            this.derivada_f = derivada_f;
            return true;
        } catch (ExcepcionParser excepcionParser) {
            return false;
        }
        this.derivada_f = derivada_f;
        return true;
    }

    public boolean setDerivada2_f(String derivada2_f){
        if(derivada2_f.isEmpty()){
            this.derivada2_f = derivada2_f;
            return true;
        }
        try {
            new Parser(funcion_f).getValue(0);
        } catch (ExcepcionEvaluacion excepcionEvaluacion) {
            this.derivada2_f = derivada2_f;
            return true;
        } catch (ExcepcionParser excepcionParser) {
            return false;
        }
        this.derivada2_f = derivada2_f;
        return true;
    }
}
