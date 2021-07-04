/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.util.Timer;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import me.pabloestrada.MancalaGame.marbles.Position;

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
    private  ImageView avatarJogador1;
    private  ImageView avatarJogador2;
    
    /**
     *
     * @param slotImages
     * @param status
     * @param avatarJogador1
     * @param avatarJogador2
     */
    public Tabuleiro(ImageView[] slotImages, Label status, ImageView avatarJogador1,ImageView avatarJogador2, int[] coordx, int[] coordy) {
       // System.out.println("Tabuleiro Constructor");
		this.buracos = obterBuracos(slotImages,coordx,coordy);
                //System.out.println("Depois?");
		this.jogadorAtual = TipoJogador.JOGADOR_1;
		this.status = status;
		this.avatarJogador1 = avatarJogador1;
		this.avatarJogador2 = avatarJogador2;
		this.tempo = new Timer();
		this.seJogoAcabou = false;
		//updatePlayerTurnStatus(false);
		//updatePlayerAvatars();
	}



    
    
    private Buraco[] obterBuracos(ImageView[] slotImages,int[] coordx, int[] coordy) {
        
                final int[] xPos = { 614, 524, 434, 344, 254, 164, 75, 164, 254, 344, 434, 524, 614, 705 };//Coordenadas dos buracos em X
		final int[] yPos = { 257, 257, 257, 257, 257, 257, 308, 367, 367, 367, 367, 367, 367, 308 };//Coordenadas dos buracos em Y
                
            
        
		Buraco[] buracoTemp = new Buraco[14];
                
		for (int i = 0; i < buracoTemp.length; i++) {
                    
                    
                            // Bounds boundsInScreen = slotImages[i].localToScene(slotImages[i].getBoundsInLocal());
                   
			Posicao pos = new Posicao(coordx[i], coordy[i]);
			if (i == 6) {
				buracoTemp[i] = new Kallah(pos, true, TipoJogador.COMPUTADOR, slotImages[i], i);
				continue;
			}
			if (i == 13) {
				buracoTemp[i] = new Kallah(pos, true, TipoJogador.COMPUTADOR, slotImages[i], i);
				continue;
			}
			buracoTemp[i] = new Buraco(pos, false, slotImages[i], i);
		}
		return buracoTemp;
    }
    
   

    private void updatePlayerTurnStatus(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void updatePlayerAvatars() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param sementesInicio
     */
    public void popularSementes(StackPane sementesInicio) {
       
	//	MarbleColor[] colors = MarbleColor.values();
		for (Buraco buraco : buracos) {
			if (buraco.isBuracoKallah())
				continue;
			for (int i = 0; i < 4; i++) {
				//int colorIndex = (int) (Math.random() * colors.length);
                                
				Semente currentMarble = new Semente();
                                
				sementesInicio.getChildren().add(currentMarble.getImageView());
				buraco.addMarble(currentMarble, 3);
			}
		}
	
    }

    /**
     *
     * @param selectedSlot
     * @return
     */
    public Buraco getSlot(int selectedSlot) {
       
		return buracos[selectedSlot];
	}
    
}


    //System.out.println(boundsInScreen);
            //maxx - width/2 para obter  cordenadas x no meio do node
            //maxy - height/2 para obter coordenada y no meio do node
           //     for (ImageView slot : slotImages) {
           //       Bounds boundsInScreen = slot.localToScene(slot.getBoundsInLocal()); 
           //       System.out.println(boundsInScreen);
           //     }
            // double newX = (double)boundsInScreen.getMaxX() - (double)boundsInScreen.getWidth()/2;
                    //System.out.println("i: "+i+"x: "+newX+"width: "+boundsInScreen.getWidth());
                   // System.out.println(boundsInScreen);
                    //--Bounds boundsInScreen = selectedSlot.getImageView().localToScene(selectedSlot.getImageView().getBoundsInLocal());
                

//buraco_0  X:615.6774291992188  Y257.0
//buraco_1  X:525.6774291992188  Y257.0
//buraco_2  X:435.6774139404297  Y257.0
//buraco_3  X:345.6774139404297  Y257.0
//buraco_4  X:255.6774139404297  Y257.0
//buraco_5  X:165.6774139404297  Y257.0
//buraco_6  X:76.26706314086914  Y298.0
//buraco_7  X:165.6774139404297  Y367.0
//buraco_8  X:255.6774139404297  Y367.0
//buraco_9  X:345.6774139404297  Y367.0
//buraco_10  X:435.6774139404297  Y367.0
//buraco_11  X:525.6774291992188  Y367.0
//buraco_12  X:615.6774291992188  Y367.0
//buraco_13  X:706.2670593261719  Y298.0
