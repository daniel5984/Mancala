/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import com.sun.javafx.runtime.VersionInfo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import mancala.Info;
import mancala.Mancala;
import mancala.MudarLayout;

/**
 * FXML Controller class
 *
 * @author DanielSilva
 */
public class MenuController implements Initializable {

    @FXML
    private ImageView botaoSair;
    @FXML
    private ImageView botaoIniciar;
    @FXML
    private ImageView botaoTop10;
    @FXML
    private CheckBox seServidorCheckBox;

    private Info info;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Info info_Inicio = new Info();
        Mancala.setInfo(info_Inicio);
        info = Mancala.getInfo();
    }

    @FXML
    private void sairClick(MouseEvent event) {
        System.out.println("Sair");
        Platform.exit();
        System.exit(0);

    }

    @FXML
    private void iniciarClick(MouseEvent event) {

        if (info == null) {
            System.out.println("Info é Null");
        }
        if (seServidorCheckBox.selectedProperty().get()) {
            info.setIsServer(true);
        } else {
            info.setIsServer(false);
        }

        System.out.println("É servidor (CheckBox)->  " + seServidorCheckBox.selectedProperty().get());

        //System.out.println("iniciar");
        new MudarLayout("PrepararClient").load();

    }

    @FXML
    private void top10Click(MouseEvent event) {
        System.out.println("top10");
        System.out.println(VersionInfo.getRuntimeVersion());
    }

}
