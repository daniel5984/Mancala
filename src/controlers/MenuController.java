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
 * FXML Controller class interface de seleção do username e no caso do client de
 * escolher o IP
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
     * Este controler é responsável pelo o layout do Menu
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Quando o Utilizador deseja sair
     *
     * @param event evento do rato
     */
    @FXML
    private void sairClick(MouseEvent event) {
        System.out.println("Sair");
        Platform.exit();
        System.exit(0);

    }

    /**
     * Aqui ao clicar em iniciar no layout , se o jogador tiver clicado na
     * checkbox para saber se é servidor é Criada uma instancia da Class info
     * que contém informação que possa ser acessada de modo global Static nas
     * outras classes
     *
     *
     * @param event evento do mouse
     */
    @FXML
    private void iniciarClick(MouseEvent event) {
        if (seServidorCheckBox.selectedProperty().get()) {
            Info info_Inicio = new Info(true);
            Mancala.setInfo(info_Inicio);
        } else {
            Info info_Inicio = new Info(false);
            Mancala.setInfo(info_Inicio);
        }
        System.out.println("É servidor (CheckBox)->  " + seServidorCheckBox.selectedProperty().get());
        new MudarLayout("PrepararJogador").load();

    }

    /**
     *
     * @param event evento do mouse
     */
    @FXML
    private void top10Click(MouseEvent event) {
        System.out.println("top10");
        System.out.println(VersionInfo.getRuntimeVersion());
    }

}
