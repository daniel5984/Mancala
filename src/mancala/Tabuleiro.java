/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.util.Timer;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Aqui está representado o campo de jogo 
 * @author DanielSilva
 */
public class Tabuleiro {
    boolean ganhou;
    int jogadores[];
    int pontosJogadorA;
    int pontosJogadorB;
    private Buraco[] buracos;
    private TipoJogador jogadorAtual;
    private Label status;
    private Timer tempo;
    private boolean seJogoAcabou;
    public Tabuleiro(ImageView[] slotImages, Label[] labels, Label status, ImageView playeroneavatar,ImageView playertwoavatar) {
		this.buracos = obterBuracos(slotImages, labels);
		this.jogadorAtual = TipoJogador.JOGADOR_1;
		this.status = status;
		this.playeroneavatar = playeroneavatar;
		this.playertwoavatar = playertwoavatar;
		this.tempo = new Timer();
		this.seJogoAcabou = false;
		updatePlayerTurnStatus(false);
		updatePlayerAvatars();
	}

    
    
    
    private Buraco[] obterBuracos(ImageView[] slotImages, Label[] labels) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updatePlayerTurnStatus(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
