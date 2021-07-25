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

    private String nomeJogador_servidor;
    private String nomeJogador_client;
    private boolean isServer;
    private String avatarServidorCor;
    private String avatarClientCor;
    private int Buracosinfo[];

    public int[] getBuracosinfo() {
        return Buracosinfo;
    }

    public void setBuracosinfo(int[] Buracosinfo) {
        this.Buracosinfo = Buracosinfo;
        for (int i=0;i<Buracosinfo.length;i++) {
        System.out.println("Buraco ->"+i+" com "+this.Buracosinfo[i]+" sementes.");
    }
    }

    public String getAvatarServidorCor() {
        return avatarServidorCor;
    }

    public void setAvatarServidorCor(String avatarServidorCor) {
        this.avatarServidorCor = avatarServidorCor;
    }

    public String getAvatarClientCor() {
        return avatarClientCor;
    }

    public void setAvatarClientCor(String avatarClientCor) {
        this.avatarClientCor = avatarClientCor;
    }

    public boolean isServer() {
        return isServer;
    }

    public void setIsServer(boolean isServer) {
        this.isServer = isServer;
    }

    public String getNomeJogadorServidor() {
        return nomeJogador_servidor;
    }

    public void setNomeJogadorServidor(String nomeJogador1) {
        this.nomeJogador_servidor = nomeJogador1;
    }

    public String getNomeJogadorClient() {
        return nomeJogador_client;
    }

    public void setNomeJogadorClient(String nomeJogador2) {
        this.nomeJogador_client = nomeJogador2;
    }

    public String getNome(TipoJogador type) {
        if (type == TipoJogador.JOGADOR_SERVIDOR) {
            return nomeJogador_servidor;
        }
        if (type == TipoJogador.JOGADOR_CLIENT) {
            return nomeJogador_client;
        }
        return "CPU";
    }

}
