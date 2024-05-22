/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.monopoly.entidades.juego;

import java.util.ArrayList;

/**
 *
 * @author mac
 */
public class Dado {

    private final ArrayList<Integer> numeros;

    public Dado() {
        this.numeros = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            this.numeros.add(i);
        }
    }

    /*
    genera un numero pseudoaleatorio con base en el tamaño del dado hasta que
    este numero generado este entre 1 y 6
    */
    public int getNumero() {
        int numero;
        do {
            numero = (int) (Math.random() * this.numeros.size());
        } while (numero < 1 || numero > 6);
        return numero;
    }

}
