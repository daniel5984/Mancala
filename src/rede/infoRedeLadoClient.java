/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import java.io.Serializable;

/**
 * Esta class implementa Serializable para que se possa enviar por rede , atraves do nome sabemos que ela vem do lado do Client
 * @author DanielSilva
 */
public class infoRedeLadoClient implements Serializable {

    private String tipo;
    private int numeroBuraco;
    private int IDBuraco;

    /**
     * 
     * @return Retorna o ID do Buraco que foi Selecionado
     */
    public int getIDBuraco() {
        return IDBuraco;
    }

    /**
     *
     * @return Retorna o Tipo de mudança transição feita
     */
    public String getTipo() {
        return tipo;
    }

    /**
     *
     * @return Retorna o numero do buraco
     */
    public int getNumeroBuraco() {
        return numeroBuraco;
    }

    /**
     *
     * @param tipo  o Tipo de mudança transição feita
     * @param numeroBuraco o numero do buraco
     * @param IDBuraco o ID do buraco
     */
    public infoRedeLadoClient(String tipo, int numeroBuraco, int IDBuraco) {
        this.tipo = tipo;
        this.numeroBuraco = numeroBuraco;
        this.IDBuraco = IDBuraco;
    }
}
