/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author DanielSilva
 */
public class FxDemo extends Application {
    private static Stage stagePrincipal;
    @Override
    public void start(Stage stage) throws Exception {
        
        try{
                stagePrincipal = stage;
		new MudarLayout("FxMenu").load();
		stagePrincipal.setResizable(false);
		stagePrincipal.show();
		stagePrincipal.setTitle("Mancala");
        
        }catch(Exception e){
            System.out.println(e.toString());
            
        }
    }

    	public static Stage getMainStage() {
		return stagePrincipal;
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
