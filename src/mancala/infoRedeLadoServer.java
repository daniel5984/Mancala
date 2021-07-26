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
public class infoRedeLadoServer implements Serializable {

    private String tipo;
    private int numeroBuraco;
    private int IDBuraco;

    public int getIDBuraco() {
        return IDBuraco;
    }

    public String getTipo() {
        return tipo;
    }

    public int getNumeroBuraco() {
        return numeroBuraco;
    }

    public infoRedeLadoServer(String tipo, int numeroBuraco, int IDBuraco) {
        this.tipo = tipo;
        this.numeroBuraco = numeroBuraco;
        this.IDBuraco = IDBuraco;
    }
}
