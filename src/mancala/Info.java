/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;



/**
 *
 * @author DanielSilva
 */
public  class Info implements Serializable {
    private String nomeJogador1;
    private String nomeJogador2;
    private boolean isServer;

    public boolean isIsServer() {
        return isServer;
    }

    public void setIsServer(boolean isServer) {
        this.isServer = isServer;
    }

    public String getNomeJogador1() {
        return nomeJogador1;
    }

    public void setNomeJogador1(String nomeJogador1) {
        this.nomeJogador1 = nomeJogador1;
    }

    public String getNomeJogador2() {
        return nomeJogador2;
    }

    public void setNomeJogador2(String nomeJogador2) {
        this.nomeJogador2 = nomeJogador2;
    }
    public String getNome(TipoJogador type) {
		if(type == TipoJogador.JOGADOR_1)
			return nomeJogador1;
		if(type == TipoJogador.JOGADOR_2)
			return nomeJogador2;
		return "CPU";
	}

  


   
}
