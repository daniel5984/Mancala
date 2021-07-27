/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mancala.Info;
import mancala.Mancala;
import mancala.MudarLayout;

/**
 * FXML Controller class
 *
 * @author DanielSilva
 */
public class PrepararController implements Initializable {

    @FXML
    private TextField nome_input;
    @FXML
    private TextField ip_input;
    @FXML
    private Button iniciar_btn;
    public Runnable task3;
    public Runnable task4;
    public Info info;

    public boolean pararThread = false;
    private int count;
    @FXML
    private Label isServerLabel;
    @FXML
    private Text ip_label;

    /**
     * Este é o controller do layout the preparar Client
     *
     * Ao Iniciar Se o Jogador escolheu ser servidor o input text de introduzir
     * o IP desaparece e muda-se uma label para servidor para informação. Também
     * é feita uma gestão para centralizar as janelas uma ao lado da outra para
     * não ficarem as duas sobrepostas.
     *
     *
     * @param url .
     * @param rb .
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        info = Mancala.getInfo();

        if (info.isServer()) {
            isServerLabel.setText("Servidor");

            ip_label.setDisable(true);
            ip_label.setVisible(false);
            ip_input.setDisable(true);
            ip_input.setVisible(false);
        } else {
            isServerLabel.setText("Client");
        }

        if (info.isServer()) {
            centrarJanela(Mancala.getMainStage(), -200, 540);

        } else {
            centrarJanela(Mancala.getMainStage(), 1200, 540);

        }

    }

    /**
     * Este metodo Centra a Janela
     *
     * @param stage o stage
     * @param width a largura
     * @param height a altura
     */
    private void centrarJanela(Stage stage, double width, double height) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
    }

    /**
     * Quando se Clica em começar se o input text não tiver nome ele vai ter um
     * nome genérico caso o input tiver texto então fazemos set do nome na Class
     * global "Info"
     *
     * @param event evento do rato
     */
    @FXML
    private void comecar_click(MouseEvent event) {
        //Caso Seja servidor ou Client
        if (nome_input.textProperty().isEmpty().get()) {
            //System.out.println("O nome não tem texto em " + info.isServer());
        } else if (!nome_input.textProperty().isEmpty().get() && info.isServer()) {

            info.setJogador1(nome_input.getText());

        } else if (!nome_input.textProperty().isEmpty().get() && !info.isServer()) {
            info.setJogador2(nome_input.getText());
        }

        new MudarLayout("Tabuleiro").load();
    }
}
