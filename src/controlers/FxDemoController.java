/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author DanielSilva
 */
public class FxDemoController implements Initializable {

    private Label label;


    private TextField caixaTexto;

    private Slider slider1;
    private Slider slider2;
    private Label label3;

    private TextField text_input1;
    private TextField text_input2;
    private Label label1;
    private Label label2;
    @FXML
    private HBox hBox;
    private ComboBox<String> cmbBox;
    private Label labelOld;
    private Label labelNew;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
     private void sairClick(ActionEvent event) {

         System.out.println("Sair");;

     }
      private void iniciarClick(ActionEvent event) {

         System.out.println("iniciar");;

     }
       private void top10Click(ActionEvent event) {

         System.out.println("top10");;

     }

}
