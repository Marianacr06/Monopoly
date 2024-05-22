/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.monopoly.entidades.tablero;

import co.edu.udea.monopoly.entidades.juego.Juego;
import co.edu.udea.monopoly.entidades.juego.Jugador;

/**
 *
 * @author mac
 */
public abstract class CasillaEspecial extends Casilla {

    public static final String TIPO_CASILLA_ESPECIAL_INICIO = "inicio";
    public static final String TIPO_CASILLA_ESPECIAL_CARCEL = "carcel";
    public static final String TIPO_CASILLA_ESPECIAL_DELITO = "delito";
    public static final String TIPO_CASILLA_ESPECIAL_EVENTUALIDAD = "eventualidad";
    public static final String TIPO_CASILLA_ESPECIAL_IMPUESTO = "impuesto";
    public static final String TIPO_CASILLA_ESPECIAL_PARADA_LIBRE = "libre";
    public static final String TIPO_CASILLA_ESPECIAL_SUELDO = "sueldo";

    private String tipoCasillaEspecial;

    public CasillaEspecial(int posicion, String nombre) {
        super(posicion, nombre);
        this.setTipoCasilla(Casilla.TIPO_CASILLA_ESPECIAL);
    }

    /**
     * @return the tipoCasillaEspecial
     */
    public String getTipoCasillaEspecial() {
        return tipoCasillaEspecial;
    }

    /**
     * @param tipoCasillaEspecial the tipoCasillaEspecial to set
     */
    public void setTipoCasillaEspecial(String tipoCasillaEspecial) {
        this.tipoCasillaEspecial = tipoCasillaEspecial;
    }

    public abstract void ejecutarAccion(Jugador jugador, Juego juego);
}
