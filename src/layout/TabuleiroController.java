/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import mancala.MudarLayout;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void voltar(MouseEvent event) {
        new MudarLayout("FxMenu").load();
    }
    
    
    
    /*
    Esta class atribui transisção de opacidade de cada buraco quando o rato entra
    */
    private void mudaOpacidade(ImageView node, float scale) {
		FadeTransition obj = new FadeTransition(Duration.millis(500), node);
		obj.setToValue(scale);
		obj.setCycleCount(1);
		obj.play();
	}

    @FXML
    private void rato_saiu(MouseEvent event) {
    }

    @FXML
    private void rato_entrou(MouseEvent event) {
            Slot selectedSlot = selectionMap.get((ImageView) e.getSource());
		fadeNode(selectedSlot.getImageView(), 0.5f);
    }
}
