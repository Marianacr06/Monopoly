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
public class CasillaEspecialDelito extends CasillaEspecial {

    public CasillaEspecialDelito(int posicion, String nombre) {
        super(posicion, nombre);
        this.setTipoCasillaEspecial(CasillaEspecial.TIPO_CASILLA_ESPECIAL_DELITO);
    }

    @Override
    public void ejecutarAccion(Jugador jugador, Juego juego) {
        //mueve la ficha hasta la posicion 31 hasta la posicion 11 y el estado del 
        //jugador cambia de libre a preso.
        jugador.getFicha().setPosicion(11);
        jugador.setEstado(Jugador.ESTADO_ENCARCELDADO);
    }

}
