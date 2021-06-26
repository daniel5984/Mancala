/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


/**
 *
 * @author DanielSilva
 */
public class Kallah extends Buraco{
    
    
    
    	private TipoJogador type;
	
    /**
     *
     * @param position
     * @param isBank
     * @param type
     * @param imageView
     * @param id
     */
    public Kallah(Posicao position, boolean isBank, TipoJogador type, ImageView imageView, int id) {
		super(position, isBank, imageView, id);
		this.type = type;
	}
	
    /**
     *
     * @return
     */
    public boolean isPlayer() {
		if(type == TipoJogador.JOGADOR_1 || type == TipoJogador.JOGADOR_2)
			return true;
		return false;
	}
	
    /**
     *
     * @return
     */
    public boolean isCPU() {
		if(type == TipoJogador.COMPUTADOR)
			return true;
		return false;
	}
}
