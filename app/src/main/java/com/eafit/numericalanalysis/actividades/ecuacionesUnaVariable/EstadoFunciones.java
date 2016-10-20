package com.eafit.numericalanalysis.actividades.ecuacionesUnaVariable;

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

    public void setFuncion_f(String funcion_f){
        this.funcion_f = funcion_f;
    }

    public void setFuncion_g(String funcion_g){
        this.funcion_g = funcion_g;
    }

    public void setDerivada_f(String derivada_f){
        this.derivada_f = derivada_f;
    }

    public void setDerivada2_f(String derivada2_f){
        this.derivada2_f = derivada2_f;
    }
}
