/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author DanielSilva
 */
public class PrepararClientController implements Initializable {

    @FXML
    private TextField nome_input;
    @FXML
    private TextField ip_input;
    @FXML
    private Button text_btn;
    @FXML
    private Button iniciar_btn;
    @FXML
    private Label test_label;

    public Runnable task3;
    public boolean pararThread=false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void teste_click(MouseEvent event) {
       task3 = () -> {
           int i=0;
            while(!pararThread){
                i++;
                System.out.println(Thread.currentThread().getName() + " is running "+i);
            }        
        };    
          new Thread(task3).start();
    }

    
    
    @FXML
    private void comecar_click(MouseEvent event) {
    
        pararThread=!pararThread;
        
        test_label.setText(Boolean.toString(pararThread));
    }
    
    
    
    
}
