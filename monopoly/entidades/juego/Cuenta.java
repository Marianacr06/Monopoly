/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.monopoly.entidades.juego;

import co.edu.udea.monopoly.entidades.tablero.CasillaPropiedad;
import java.util.ArrayList;

/**
 *
 * @author mac
 */
public class Cuenta {

    private int dinero;
    private final ArrayList<CasillaPropiedad> propiedades;

    public Cuenta() {
        this.propiedades = new ArrayList<>();
        this.dinero = 2500;
    }

    public boolean agregarPropiedad(CasillaPropiedad p) {
        try {
            propiedades.add(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean borraPropiedad(CasillaPropiedad propiedad) {
        try {
            propiedades.remove(propiedad);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return the dinero
     */
    public int getDinero() {
        return dinero;
    }

    /**
     * @param dinero the dinero to set
     */
    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    /**
     * @return the propiedades
     */
    public ArrayList<CasillaPropiedad> getPropiedades() {
        return propiedades;
    }

    public void agregarDinero(int dinero) {
        this.dinero += dinero;
    }

    public boolean restarDinero(int dinero) {
        this.dinero -= dinero;
        if (this.dinero < 0) {
            this.dinero += dinero;
            return false;
        }
        return true;
    }

}
