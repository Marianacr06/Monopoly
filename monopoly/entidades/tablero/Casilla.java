/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.monopoly.entidades.tablero;

/**
 *
 * @author mac
 */
public class Casilla {

    public static final String TIPO_CASILLA_PROPIEDAD = "propiedad";
    public static final String TIPO_CASILLA_ESPECIAL = "especial";

    private final int posicion;
    private String tipoCasilla;
    private final String nombre;

    public Casilla(int posicion, String nombre) {
        this.posicion = posicion;
        this.nombre = nombre;
    }

    /**
     * @return the posicion
     */
    public int getPosicion() {
        return posicion;
    }

    /**
     * @return the tipoCasilla
     */
    public String getTipoCasilla() {
        return tipoCasilla;
    }

    /**
     * @param tipoCasilla the tipoCasilla to set
     */
    public void setTipoCasilla(String tipoCasilla) {
        this.tipoCasilla = tipoCasilla;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
}
