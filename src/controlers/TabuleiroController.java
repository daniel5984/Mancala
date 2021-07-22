/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

//import java.awt.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import mancala.MudarLayout;
import mancala.buracos.Buraco;
import mancala.Info;
import mancala.Mancala;
import mancala.Tabuleiro;
import mancala.TipoJogador;

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

    private HashMap<ImageView, Buraco> imagemBuracoMap;
    @FXML
    private ImageView avatar1;
    @FXML
    private ImageView avatar2;
    @FXML
    private Label estado;
    private Tabuleiro tabuleiro;
    @FXML
    private StackPane sementesInicio;
    @FXML
    private StackPane principalView;
    @FXML
    private StackPane boardpane;
    @FXML
    private Rectangle rectangulo;
    @FXML
    private Button btn_iniciar;
    private boolean seJogoComecou;
    private ImageView[] imagensBuraco;
    @FXML
    private HBox tabuleiro_layout;
    @FXML
    private Label avatar1_label;
    @FXML
    private Label avatar2_label;

    private static Boolean isServer = false;

    private Thread thread1;
    private Thread thread2;
    private boolean pararThread = false;
    private Info info;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        centrarJanela(Mancala.getMainStage(), 800, 540);
        seJogoComecou = false;
        this.imagensBuraco = new ImageView[]{buraco_0, buraco_1, buraco_2, buraco_3, buraco_4, buraco_5, buraco_6, buraco_7, buraco_8, buraco_9, buraco_10, buraco_11, buraco_12, buraco_13};

        mudaCorAvatar(avatar1, "amarelo");
        mudaCorAvatar(avatar2, "azul");

        imagemBuracoMap = new HashMap<>(); //Associar cada imagem do buraco a um objeto da class Buraco

        startGame();

        info = info = Mancala.getInfo();

        info = Mancala.getInfo(); //Info objeto publico da class MenuControler
        avatar1_label.setText(info.getNomeJogador1());
        avatar2_label.setText(info.getNomeJogador2());

        if (isServer) {
            correrServer();
        } else {
            correrClient();
        }

    }

    private void correrServer() {

        thread1 = new Thread() {
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(6666);
                    System.out.println("Esperar conexões");
                    Socket socket = serverSocket.accept();
                    System.out.println("conexão estabelecida " + socket);

                    ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

                    Info receberInfo = (Info) inStream.readObject();

                    System.out.println("Jogador Atual?" + receberInfo.getNome(TipoJogador.JOGADOR_1));

                    serverSocket.close();

                } catch (IOException ex) {
                    Logger.getLogger(TabuleiroController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TabuleiroController.class.getName()).log(Level.SEVERE, null, ex);
                }

                while (!pararThread) {

                    System.out.println("A correr");

                }
            }
        };

        thread1.start();

    }

    private void correrClient() {
        thread2 = new Thread() {
            public void run() {
                try {
                    Socket socket = new Socket("localhost", 6666);

                    ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

                    outStream.writeObject(info);

                    outStream.close();
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(TabuleiroController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        thread2.start();

    }

    @FXML
    private void iniciar(MouseEvent event) {
        principalView.getChildren().remove(btn_iniciar);
        principalView.getChildren().remove(rectangulo);

        int[] coordx = new int[14];
        int[] coordy = new int[14];
        int cont = 0;
        for (ImageView img : imagensBuraco) {
            Bounds b = sementesInicio.sceneToLocal(img.localToScene(img.getBoundsInLocal()));
            double x = b.getMinX() + b.getWidth() / 2 - 10;
            double y = b.getMinY() + b.getHeight() / 2 - 10;
            coordx[cont] = (int) x;
            coordy[cont] = (int) y;
            //System.out.println(x + "\n " + y + "\n");
            //System.out.println(img.getId()+"Minx"+b.getMinX()+"Miny"+b.getMinY()); 
            cont++;
        }
        tabuleiro = new Tabuleiro(imagensBuraco, estado, avatar1, avatar2, coordx, coordy);
        tabuleiro.popularSementes(sementesInicio);//Popular as sementes no stack pane sementesInicio para depois animar para 4 sementes para cada buraco

        for (int i = 0; i < imagensBuraco.length; i++) {
            imagemBuracoMap.put(imagensBuraco[i], tabuleiro.getSlot(i));
        }

    }

    @FXML
    private void voltar(MouseEvent event) {
        new MudarLayout("Menu").load();
    }

    @FXML
    private void rato_saiu(MouseEvent e) {
        Buraco selectedSlot = imagemBuracoMap.get((ImageView) e.getSource());
        mudaOpacidade(selectedSlot.getImageView(), 0.6f);

    }

    @FXML
    private void rato_entrou(MouseEvent e) {
        Buraco selectedSlot = imagemBuracoMap.get((ImageView) e.getSource());
        mudaOpacidade(selectedSlot.getImageView(), 1.0f);

        Bounds boundsInScreen = selectedSlot.getImageView().localToScene(selectedSlot.getImageView().getBoundsInLocal());

        double x = boundsInScreen.getMaxX() - boundsInScreen.getWidth() / 2 - 20;
        double y = boundsInScreen.getMaxY() - boundsInScreen.getHeight() / 2 - 20;
        // System.out.println(selectedSlot.getImageView().getId() + "  X:" + x + "  Y" + y);

    }

    private void mudaOpacidade(ImageView node, float scale) {
        FadeTransition obj = new FadeTransition(Duration.millis(100), node);
        obj.setToValue(scale);
        obj.setCycleCount(1);
        obj.play();
    }

    private void centrarJanela(Stage stage, double width, double height) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - width) / 2);
        stage.setY((screenBounds.getHeight() - height) / 2);
    }

    private void mudaCorAvatar(ImageView avatar, String cor) {

        try {
            URL path = this.getClass().getResource("../images/avatar_" + cor + ".png");
            InputStream stream = new FileInputStream(path.getPath());
            avatar.setImage(new Image(stream));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabuleiroController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void startGame() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                seJogoComecou = true;
            }
        }, 2 * 1000);

    }

    @FXML
    private void click_rato(MouseEvent event) {

        if (!seJogoComecou) {
            return;
        }

        Buraco buracoSelecionado = imagemBuracoMap.get((ImageView) event.getSource());

        if (tabuleiro.podeFazerTurno(buracoSelecionado.getId(), tabuleiro.getCurrentPlayer())) {
            tabuleiro.processarTurno(buracoSelecionado.getId());
        }

    }

}
