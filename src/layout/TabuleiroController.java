/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import mancala.MudarLayout;
import mancala.Buraco;
import mancala.Tabuleiro;

/**
 * FXML Controller class
 *
 * @author DanielSilva
 */
public class TabuleiroController implements Initializable {

    @FXML
    private ImageView buraco_6;
    @FXML
    private ImageView buraco_5;
    @FXML
    private ImageView buraco_7;
    @FXML
    private ImageView buraco_4;
    @FXML
    private ImageView buraco_8;
    @FXML
    private ImageView buraco_3;
    @FXML
    private ImageView buraco_9;
    @FXML
    private ImageView buraco_2;
    @FXML
    private ImageView buraco_10;
    @FXML
    private ImageView buraco_1;
    @FXML
    private ImageView buraco_11;
    @FXML
    private ImageView buraco_0;
    @FXML
    private ImageView buraco_12;
    @FXML
    private ImageView buraco_13;
    @FXML
    private ImageView voltar_butao;
    
    private HashMap<ImageView, Buraco> imagemBuracoAss;
    
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ImageView[] imagensBuraco = { buraco_0, buraco_1, buraco_2, buraco_3, buraco_4, buraco_5, buraco_6, buraco_7, buraco_8, buraco_9,
				buraco_10, buraco_11, buraco_12, buraco_13 };
        
        
        
        imagemBuracoAss = new HashMap<ImageView,Buraco>();//Associar cada imagem do buraco a um objeto da class Buraco
        tabuleiro = new Tabuleiro(imagensBuraco, status, playeroneavatar, playertwoavatar);
	tabuleiro.populateMarbles(marbleholder);
        for (int i = 0; i < imagensBuraco.length; i++)
			imagemBuracoAss.put(selectionImageViews[i], tabuleiro.getSlot(i));
        
        
    }    

    @FXML
    private void voltar(MouseEvent event) {
        new MudarLayout("FxMenu").load();
    }
    
    
    
   /**
    * Esta class atribui transisção de opacidade de cada buraco quando o rato entra
    * @param node
    * @param scale 
    */
    private void mudaOpacidade(ImageView node, float scale) {
		FadeTransition obj = new FadeTransition(Duration.millis(500), node);
		obj.setToValue(scale);
		obj.setCycleCount(1);
		obj.play();
	}

    @FXML
    private void rato_saiu(MouseEvent e) {
       Buraco selectedSlot = imagemBuraco.get((ImageView) e.getSource());
		mudaOpacidade(selectedSlot.getImageView(), 0.5f);
        
    }

    @FXML
    private void rato_entrou(MouseEvent e) {
        Buraco selectedSlot = imagemBuraco.get((ImageView) e.getSource());
		mudaOpacidade(selectedSlot.getImageView(), 0.3f);
    }

}
//    private void rato_entrou(MouseEvent event) {
//            Buraco selectedSlot = selectionMap.get((ImageView) event.getSource());
//		fadeNode(selectedSlot.getImageView(), 0.5f);
//    }

