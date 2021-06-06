/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author DanielSilva
 */
public class FxMenuController implements Initializable {

    @FXML
    private ImageView botaoSair;
    @FXML
    private ImageView botaoIniciar;
    @FXML
    private ImageView botaoTop10;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sairClick(MouseEvent event) {
        System.out.println("Sair");
        Platform.exit();
        System.exit(0);
        
    }

    @FXML
    private void iniciarClick(MouseEvent event) {
        System.out.println("iniciar");
        new MudarLayout("tabuleiro").load();
    }

    @FXML
    private void top10Click(MouseEvent event) {
        System.out.println("top10");
    }
    
}
