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
 * Esta Class representa informações globais que todas as classes devem ter
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

    /**
     * Predefinir os nomes caso o utilizador não escrever
     *
     * @param isServer Definir se é servidor ou client
     */
    public Info(boolean isServer) {
        this.isServer = isServer;
        nomeJogador1 = "ServidorNaoMudado";
        nomeJogador2 = "ClientNaoMudado";
    }

    /**
     * Definir o nome do Jogador 1 que fica à esquerda
     *
     * @param nome o nome que queremos atribuir
     */
    public void setJogador1(String nome) {
        this.nomeJogador1 = nome;
    }

    /**
     * Definir o nome do Jogador 2 que fica à direita
     *
     * @param nome nome o nome que queremos atribuir
     */
    public void setJogador2(String nome) {
        this.nomeJogador2 = nome;
    }

    /**
     * obter o nome do jogador 1
     *
     * @return
     */
    public String getJogador1() {
        return nomeJogador1;
    }

    /**
     * obter o nome do jogador 2
     *
     * @return
     */
    public String getJogador2() {
        return nomeJogador2;
    }

    /**
     * Verificar se é Server ou Client
     *
     * @return true se for servidor , false se for client
     */
    public boolean isServer() {
        return isServer;
    }

    /**
     * Apartir do tipo de jogador saber o nome dele
     *
     * @param type TIPO de jogador 
     * @return retorna o nome do jogador ao qual corresponde o tipo
     */
    public String getNome(TipoJogador type) {
        if (type == TipoJogador.JOGADOR_SERVIDOR) {
            return nomeJogador1;
        }
        if (type == TipoJogador.JOGADOR_CLIENT) {
            return nomeJogador2;
        }
        return "CPU";
    }

    /**
     * Através do nome do jogagor saber o tipo
     * @param nomeJogador o nome do jogador que queremos saber o tipo
     * @return retorna o tipo do jogador para aquele nome de jogador
     */
    public TipoJogador getTipo(String nomeJogador) {
        if (nomeJogador.equals(nomeJogador1)) {
            return TipoJogador.JOGADOR_SERVIDOR;
        }

        return TipoJogador.JOGADOR_CLIENT;
    }

}
