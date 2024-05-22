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
public class CasillaEspecialEventualidad extends CasillaEspecial {

    public static final String TIPO_CASILLA_ESPECIAL_EVENTUALIDAD_ARCA_COMUN = "arca comun";
    public static final String TIPO_CASILLA_ESPECIAL_EVENTUALIDAD_CASUALIDAD = "casualidad";

    private final String tipoCasillaEspecialEventualidad;

    public CasillaEspecialEventualidad(int posicion, String nombre, String tipoCasillaEspecialEventualidad) {
        super(posicion, nombre);
        this.setTipoCasillaEspecial(CasillaEspecial.TIPO_CASILLA_ESPECIAL_EVENTUALIDAD);
        this.tipoCasillaEspecialEventualidad = tipoCasillaEspecialEventualidad;
    }

    /**
     * @return the tipoCasillaEspecialEventualidad
     */
    public String getTipoCasillaEspecialEventualidad() {
        return tipoCasillaEspecialEventualidad;
    }

    @Override
    public void ejecutarAccion(Jugador jugador, Juego juego) {
        switch (tipoCasillaEspecialEventualidad) {
            case CasillaEspecialEventualidad.TIPO_CASILLA_ESPECIAL_EVENTUALIDAD_ARCA_COMUN:
                juego.getTarjetaArcaComun().ejecutarAccion(jugador, juego);
                break;
            case CasillaEspecialEventualidad.TIPO_CASILLA_ESPECIAL_EVENTUALIDAD_CASUALIDAD:
                juego.getTarjetaCasualidad().ejecutarAccion(jugador, juego);
                break;
        }
    }
}
