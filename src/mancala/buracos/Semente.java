/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala.buracos;

import java.io.Serializable;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Cada Jogador possui 24 sementes , ao todo o campo tem 48 sementes 24 para
 * cada jogador
 *
 * @author DanielSilva
 */
public class Semente implements Serializable {

    String cor;
    //int pos;

    private ImageView imageView;

    /**
     *
     */
    public Semente() {
        //Dropshadow é um efeito de sombra que vai ser aplicado a cada semente
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(8.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.color(0.0, 0.0, 0.0));

        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(1);
        innerShadow.setOffsetY(1);
        innerShadow.setRadius(2.0);
        innerShadow.setWidth(0.1);
        innerShadow.setColor(Color.web("0xffffff"));

        imageView = new ImageView(getMarbleImage());
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        imageView.setMouseTransparent(true);

        //effect2.setInput(effect3);
        // effect1.setInput(effect2);
        //    imageView.setEffect(effect1);
        dropShadow.setInput(innerShadow);
        imageView.setEffect(dropShadow);
        //  imageView.setEffect(innerShadow);
    }

    private Image getMarbleImage() {
        return new Image("./images/semente.png");
    }

    /**
     *
     * @return
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     *
     * @param position
     * @param time
     */
    public void moveTo(Posicao position, int time) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(time * 1000), this.imageView);
        tt.setToX(position.getX());
        tt.setToY(position.getY());
        tt.setCycleCount(1);
        tt.setAutoReverse(true);
        tt.play();
    }
}
