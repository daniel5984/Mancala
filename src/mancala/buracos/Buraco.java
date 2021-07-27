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
 * Esta Class representa cada buraco do Jogo
 *
 * @author DanielSilva
 */
public class Buraco implements Serializable {

    private Posicao posicao;
    private ArrayList<Semente> sementes;
    private boolean isKallah;
    private String imageViewID;
    private Label label;
    private int id;
    private ImageView imageview;

    /**
     *
     * @return retorna o numero de sementes que o buraco tem atualmente
     */
    public int getNumeroSementes() {

        return sementes.size();
    }

    /**
     * Este é o construtor da Class Buraco onde faz distinção dos jogadores por
     * cor
     *
     * @param position posição do Buraco
     * @param isKallah Se é um Kallah
     * @param imageView A imageView do Buraco
     * @param id O ID do buraco
     */
    public Buraco(Posicao position, boolean isKallah, ImageView imageView, int id) {
        this.posicao = position;
        this.id = id;
        this.imageViewID = imageView.getId();
        this.isKallah = isKallah;
        this.imageview = imageView;
        sementes = new ArrayList<Semente>();

        if (id < 6) {//É do Client(Jogador2)
            mudaCorBuraco(this.imageview, "azul");
        }
        if (id > 6 && id < 13) {//É Server(Jogador1)
            mudaCorBuraco(this.imageview, "amarelo");
        }
    }

    /**
     * Este metodo Muda a cor do Buraco conforme o ID
     *
     * @param buraco a ImageView do buraco para alterar a imagem
     * @param cor a cor que existem 6 imagens criadas onde estão organizadas de
     * forma a poder automatizar a sua escolha através do nome da cor
     */
    private void mudaCorBuraco(ImageView buraco, String cor) {
        try {
            URL path = this.getClass().getResource("../../images/buraco_" + cor + ".png");
            InputStream stream = new FileInputStream(path.getPath());
            //System.out.println("FicheiroStream -> " + stream);
            buraco.setImage(new Image(stream));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabuleiroController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @return retorna o ID da imagem
     */
    public String getImageViewID() {
        return imageViewID;
    }

    /**
     *
     * @return retorna true se o buraco for um Kallah e false se não for
     */
    public boolean isBuracoKallah() {
        return isKallah;
    }

    /**
     *
     * @return retorna o numero de sementes neste buraco
     */
    public int getMarbleCount() {
        return sementes.size();
    }

    /**
     *
     * @return retorna a posição desde buraco
     */
    public Posicao getPosition() {
        return posicao;
    }

    /**
     *
     * @return retorna true se o buraco estiver Vazio
     */
    public boolean isBuracoVazio() {
        return sementes.isEmpty();
    }

    /**
     *
     * @return retorna um array de Sementes vazio
     */
    public Semente[] limparTodasAsSementes() {
        Semente[] arrayDeSementes = sementes.toArray(new Semente[sementes.size()]);
        sementes.clear();
        return arrayDeSementes;
    }

    /**
     *
     * @param semente A semente que queremos adicionar
     * @param tempo o tempo que demora a fazer a transição(Animação)
     */
    public void adicionaSemente(Semente semente, int tempo) {
        sementes.add(semente);
        semente.moveTo(getPosition().obterPosicaoSemelhante(), tempo);
    }

    /**
     *
     * @param sementes um array de sementes
     * @param tempo o tempo que demora a fazer a transição(Animação)
     */
    public void adicionaSementes(Semente[] sementes, int tempo) {
        for (Semente marble : sementes) {
            adicionaSemente(marble, tempo);
        }
    }

    /**
     * Verifica se o buraco está no lado deste jogador ou no lado do inimigo
     *
     * @param tipo de jogador
     * @return se for true está no meu lado senão está no lado do inimigo
     */
    public boolean isMeuLado(TipoJogador tipo) {
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

    /**
     * Obter o Id do Buraco
     *
     * @return ID do buraco
     */
    public int getId() {
        return id;
    }

}
