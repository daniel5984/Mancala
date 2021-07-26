/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala.buracos;

import controlers.TabuleiroController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mancala.TipoJogador;

/**
 *
 * @author DanielSilva
 */
public class Buraco implements Serializable {

    private Posicao posicao;
    private ArrayList<Semente> sementes;

    public int getNumeroSementes() {

        return sementes.size();
    }

    private boolean isKallah;

    // private ImageView imageView;
    private String imageViewID;
    private Label label;
    private int id;
    private ImageView imageview;

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
        System.out.println("O ID do buraco é -> " + this.id);
        // this.imageView = imageView;
        this.imageViewID = imageView.getId();
        // System.out.println("ID -> "+imageView.getId());
        //this.label = label;
        this.isKallah = isBank;
        this.imageview = imageView;
        sementes = new ArrayList<Semente>();
        if (id < 6) { //É do Client
            mudaCorBuraco(this.imageview, "azul");
        }
        if (id > 6 && id < 13) {
            mudaCorBuraco(this.imageview, "amarelo");
        }
    }

    private void mudaCorBuraco(ImageView buraco, String cor) {
        try {
            URL path = this.getClass().getResource("../../images/buraco_" + cor + ".png");
            InputStream stream = new FileInputStream(path.getPath());
            System.out.println("FicheiroStream -> " + stream);
            buraco.setImage(new Image(stream));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabuleiroController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getImageViewID() {
        return imageViewID;
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
    public void adicionaSemente(Semente marble, int time) {
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
            adicionaSemente(marble, time);
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
        if (tipo == TipoJogador.JOGADOR_SERVIDOR && id < 6) {
            return false;
        }
        if (tipo == TipoJogador.JOGADOR_CLIENT && id > 6) {
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
    // public ImageView getImageView() {
    //     return imageView;
    //}
    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

}
