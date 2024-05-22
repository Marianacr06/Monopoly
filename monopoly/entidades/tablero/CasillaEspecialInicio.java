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
public class CasillaEspecialInicio extends CasillaEspecial {
    
    private static final int REGALO = 200;

    public CasillaEspecialInicio(int posicion, String nombre) {
        super(posicion, nombre);
        this.setTipoCasillaEspecial(CasillaEspecial.TIPO_CASILLA_ESPECIAL_INICIO);
    }

    @Override
    public void ejecutarAccion(Jugador jugador, Juego juego) {
        jugador.getCuenta().agregarDinero(CasillaEspecialInicio.REGALO);
        juego.getBanco().restarDinero(CasillaEspecialInicio.REGALO);
    }

}
