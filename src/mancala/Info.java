/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import mancala.buracos.Posicao;

/**
 *
 * @author DanielSilva
 */
public class Info implements Serializable {

    private String nomeJogador1;
    private String nomeJogador2;
    private boolean isServer;
    private String avatarServidorCor;
    private String avatarClientCor;
    private int Buracosinfo[];

    public Info(boolean isServer) {
        this.isServer = isServer;
        nomeJogador1 = "ServidorNaoMudado";
        nomeJogador2 = "ClientNaoMudado";
    }

    public void setJogador1(String nome) {
        this.nomeJogador1 = nome;
    }

    public void setJogador2(String nome) {
        this.nomeJogador2 = nome;
    }

    public String getJogador1() {
        return nomeJogador1;
    }

    public String getJogador2() {
        return nomeJogador2;
    }

    public int[] getBuracosinfo() {
        return Buracosinfo;
    }

    public void setBuracosinfo(int[] Buracosinfo) {
        this.Buracosinfo = Buracosinfo;
        for (int i = 0; i < Buracosinfo.length; i++) {
            System.out.println("Buraco ->" + i + " com " + this.Buracosinfo[i] + " sementes.");
        }
    }

    public boolean isServer() {
        return isServer;
    }

    public String getNome(TipoJogador type) {
        if (type == TipoJogador.JOGADOR_SERVIDOR) {
            return nomeJogador1;
        }
        if (type == TipoJogador.JOGADOR_CLIENT) {
            return nomeJogador2;
        }
        return "CPU";
    }

    public TipoJogador getTipo(String nomeJogador) {
        if (nomeJogador.equals(nomeJogador1)) {
            return TipoJogador.JOGADOR_SERVIDOR;
        }

        return TipoJogador.JOGADOR_CLIENT;
    }

}
