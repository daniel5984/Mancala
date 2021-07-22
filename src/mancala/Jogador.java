package mancala;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tiagofigueiredo
 */
public class Jogador implements Serializable{
    String nome;
    String cor;
    int num_pecas;
    int pontos; 

    public Jogador(String nome, int pontos) {
        this.nome = nome;
      
        this.pontos = pontos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getNum_pecas() {
        return num_pecas;
    }

    public void setNum_pecas(int num_pecas) {
        this.num_pecas = num_pecas;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    
}
