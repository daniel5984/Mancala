/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Cada Jogador possui 24 sementes , ao todo o campo tem 48 sementes 24 para cada jogador
 * @author DanielSilva
 */
public class Semente {
    String cor;
    int pos;

    private ImageView imageView;
	
    /**
     *
     */
    public Semente() {
		imageView = new ImageView(getMarbleImage());
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
                imageView.setMouseTransparent(true);
                
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



