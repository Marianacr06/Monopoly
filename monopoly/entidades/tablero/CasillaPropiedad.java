/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.monopoly.entidades.tablero;

import co.edu.udea.monopoly.entidades.juego.Jugador;

/**
 *
 * @author mac
 */
public abstract class CasillaPropiedad extends Casilla {

    public static final String TIPO_CASILLA_PROPIEDAD_TERRENO = "terreno";
    public static final String TIPO_CASILLA_PROPIEDAD_SERVICIO = "servicio";

    public static final String HIPOTECADA = "hipotecada";
    public static final String DISPONIBLE = "disponible";
    public static final String ADQUIRIDA = "adquirida";

    private String estado;
    private int renta;
    private final int rentaBase;
    private String tipoCasillaPropiedad;
    private final int valor;
    private final int valorHipoteca;
    private Jugador propietario;

    public CasillaPropiedad(int posicion, String nombre, int rentaBase,
            int valor, int valorHipoteca) {
        super(posicion, nombre);
        this.setTipoCasilla(Casilla.TIPO_CASILLA_PROPIEDAD);
        this.rentaBase = rentaBase;
        this.valor = valor;
        this.valorHipoteca = valorHipoteca;
        this.estado = CasillaPropiedad.DISPONIBLE;
        this.renta = this.rentaBase;
    }

    /**
     * @return the renta
     */
    public int getRenta() {
        return renta;
    }

    /**
     * @param renta the renta to set
     */
    public void setRenta(int renta) {
        this.renta = renta;
    }

    /**
     * @return the rentaBase
     */
    public int getRentaBase() {
        return rentaBase;
    }

    /**
     * @return the tipoCasillaPropiedad
     */
    public String getTipoCasillaPropiedad() {
        return tipoCasillaPropiedad;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @return the valorHipoteca
     */
    public int getValorHipoteca() {
        return valorHipoteca;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public abstract void calculaRenta();

    /**
     * @param tipoCasillaPropiedad the tipoCasillaPropiedad to set
     */
    public void setTipoCasillaPropiedad(String tipoCasillaPropiedad) {
        this.tipoCasillaPropiedad = tipoCasillaPropiedad;
    }

    /**
     * @return the propietario
     */
    public Jugador getPropietario() {
        return propietario;
    }

    /**
     * @param propietario the propietario to set
     */
    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }

}
