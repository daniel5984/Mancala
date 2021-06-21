/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import mancala.Posicao;

/**
 *
 * @author DanielSilva
 */
public class Buraco {
    
        private Posicao posicao;
	private ArrayList<Semente> sementes;

	private boolean isKallah;

	private ImageView imageView;
	private Label label;
	private int id;

	public Buraco(Posicao position, boolean isBank, ImageView imageView, Label label, int id) {
		this.posicao = position;
		this.id = id;
		this.imageView = imageView;
		this.label = label;
		this.isKallah = isBank;
		sementes = new ArrayList<Semente>();
	}

	public boolean isBuracoKallah() {
		return isKallah;
	}

	public int getMarbleCount() {
		return sementes.size();
	}

	public Posicao getPosition() {
		return posicao;
	}

	public boolean isEmpty() {
		return sementes.isEmpty();
	}

	public Semente[] clearMarbels() {
		Semente[] marblesArray = sementes.toArray(new Semente[sementes.size()]);
		sementes.clear();
		//updateMarbleLabel();
		return marblesArray;
	}

	public void addMarble(Semente marble, int time) {
		sementes.add(marble);
		marble.moveTo(getPosition().getSimilarPosition(), time);
		//updateMarbleLabel();
	}

	public void addMarbles(Semente[] marbles, int time) {
		for (Semente marble : marbles)
			addMarble(marble, time);
	}

	public boolean isMySide(TipoJogador tipo) {
		if (isKallah)
			return false;
		if (tipo == TipoJogador.JOGADOR_1 && id < 6)
			return false;
		if (tipo == TipoJogador.JOGADOR_2 && id > 6)
			return false;
		if (tipo == TipoJogador.COMPUTADOR && id > 6)
			return false;
		return true;
	}

//	public void updateMarbleLabel() {
//		label.setText("" + getMarbleCount());
//	}

//	public Label getLabel() {
//		return label;
//	}

	public ImageView getImageView() {
		return imageView;
	}

	public int getId() {
		return id;
	}

}
