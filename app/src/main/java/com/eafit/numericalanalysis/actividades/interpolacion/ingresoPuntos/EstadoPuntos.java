package com.eafit.numericalanalysis.actividades.interpolacion.ingresoPuntos;

import com.eafit.numericalanalysis.util.Tupla;

/**
 * Created by jgavi on 13/11/2016.
 */

public class EstadoPuntos {

    private int nPuntos;
    private Tupla[] puntos;

    private static EstadoPuntos estado;

    private EstadoPuntos(){
        this.nPuntos = 0;
    }

    public static EstadoPuntos solicitarEstado(){
        if(estado == null){
            estado = new EstadoPuntos();
        }
        return estado;
    }

    public void setPuntos(Tupla[] puntos){
        int n = puntos.length;
        this.puntos = new Tupla[n];
        System.arraycopy(puntos,0,this.puntos,0,n);
        this.nPuntos = n;
    }

    public int getnPuntos() {
        return nPuntos;
    }

    public Tupla[] getPuntos() {
        return puntos;
    }
}
