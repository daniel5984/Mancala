/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Esta classe serve para mudar o layout dos fxml passando o nome do layout
 * @author DanielSilva
 */
public class MudarLayout {
  
    private String address;
	
    /**
     *
     * @param address
     */
    public MudarLayout(String address) {
		this.address = address;
	}

    /**
     *
     */
    public void load() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/" + address + ".fxml"));
		try {
			FxDemo.getMainStage().setScene(new Scene((Pane)loader.load()));
		} catch (IOException e) {
			e.printStackTrace();
                        System.out.println(e.toString());
		}
	}
}
