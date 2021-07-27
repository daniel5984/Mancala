/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala.buracos;

import javafx.scene.image.ImageView;
import mancala.TipoJogador;

/**
 * Esta Class representa o Kallah que é um Buraco especial por isso extende
 * Buraco
 *
 * @author DanielSilva
 */
public class Kallah extends Buraco {

    private TipoJogador tipo;

    /**
     * Construtor do Kallah
     *
     * @param posicao posiçao do kallah
     * @param isKallah se é kallah
     * @param type o tipo de Jogador
     * @param imageView a ImageView do kallah
     * @param id o id do buraco
     */
    public Kallah(Posicao posicao, boolean isKallah, TipoJogador type, ImageView imageView, int id) {
        super(posicao, isKallah, imageView, id);
        this.tipo = type;
    }

}
