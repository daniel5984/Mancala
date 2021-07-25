/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.Serializable;

/**
 *
 * @author DanielSilva
 */
public class infoRede implements Serializable {
 private String tipo;
 private int numeroBuraco;

    public String getTipo() {
        return tipo;
    }

    public int getNumeroBuraco() {
        return numeroBuraco;
    }

    public infoRede(String tipo, int numeroBuraco) {
        this.tipo = tipo;
        this.numeroBuraco = numeroBuraco;
    }
}
