/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala.buracos;

import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import mancala.TipoJogador;

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

    /**
     *
     * @param position
     * @param isBank
     * @param imageView
     * @param id
     */
    public Buraco(Posicao position, boolean isBank, ImageView imageView, int id) {
        this.posicao = position;

        //Saber a posição
        this.id = id;
        this.imageView = imageView;
        //this.label = label;
        this.isKallah = isBank;
        sementes = new ArrayList<Semente>();
    }

    /**
     *
     * @return
     */
    public boolean isBuracoKallah() {
        return isKallah;
    }

    /**
     *
     * @return
     */
    public int getMarbleCount() {
        return sementes.size();
    }

    /**
     *
     * @return
     */
    public Posicao getPosition() {
        return posicao;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return sementes.isEmpty();
    }

    /**
     *
     * @return
     */
    public Semente[] clearMarbels() {
        Semente[] marblesArray = sementes.toArray(new Semente[sementes.size()]);
        sementes.clear();
        //updateMarbleLabel();
        return marblesArray;
    }

    /**
     *
     * @param marble
     * @param time
     */
    public void addMarble(Semente marble, int time) {
        sementes.add(marble);
        marble.moveTo(getPosition().getSimilarPosition(), time);
        //updateMarbleLabel();
    }

    /**
     *
     * @param marbles
     * @param time
     */
    public void addMarbles(Semente[] marbles, int time) {
        for (Semente marble : marbles) {
            addMarble(marble, time);
        }
    }

    /**
     *
     * @param tipo
     * @return
     */
    public boolean isMySide(TipoJogador tipo) {
        if (isKallah) {
            return false;
        }
        if (tipo == TipoJogador.JOGADOR_1 && id < 6) {
            return false;
        }
        if (tipo == TipoJogador.JOGADOR_2 && id > 6) {
            return false;
        }
        return true;
    }

//	public void updateMarbleLabel() {
//		label.setText("" + getMarbleCount());
//	}
//	public Label getLabel() {
//		return label;
//	}
    /**
     *
     * @return
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

}
