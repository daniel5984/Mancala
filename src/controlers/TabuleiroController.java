/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import rede.Client;
import mancala.MudarLayout;
import mancala.buracos.Buraco;
import mancala.Info;
import mancala.Mancala;
import rede.Server;
import mancala.Tabuleiro;
import rede.infoRedeLadoClient;
import rede.infoRedeLadoServer;

/**
 * FXML Controller class Esta Controller class é responsável por controlar a
 * interação com a interface do jogo
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

    private Info info;
    private boolean tabuleiroIniciado;
    private int contador;

    private Client client;
    private Server server;
    @FXML
    private Label label_mostrarTipo;
    @FXML
    private Label label_sementes;
    @FXML
    private Rectangle vencedorBackgroud;
    @FXML
    private Button butaoVencedor;

    /**
     *
     * @param url .
     * @param rb .
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        centrarJanela(Mancala.getMainStage(), 800, 540);
        info = Mancala.getInfo(); //Info objeto publico da class MenuControler
        tabuleiroIniciado = false;
        seJogoComecou = false;
        this.imagensBuraco = new ImageView[]{buraco_0, buraco_1, buraco_2, buraco_3, buraco_4, buraco_5, buraco_6, buraco_7, buraco_8, buraco_9, buraco_10, buraco_11, buraco_12, buraco_13};

        mudaCorAvatar(avatar1, "amarelo");
        mudaCorAvatar(avatar2, "azul");

        imagemBuracoMap = new HashMap<>(); //Associar cada imagem do buraco a um objeto da class Buraco

        startGame();

        avatar1_label.setText(info.getJogador1());
        avatar2_label.setText(info.getJogador2());

        if (info.isServer()) {
            centrarJanela(Mancala.getMainStage(), -200, 540);
            label_mostrarTipo.setText("Servidor");
        } else {
            centrarJanela(Mancala.getMainStage(), 1200, 540);
            label_mostrarTipo.setText("Client");
        }
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
            cont++;
        }
        tabuleiro = new Tabuleiro(imagensBuraco, estado, coordx, coordy, info.isServer(), avatar1_label, avatar2_label, label_mostrarTipo, vencedorBackgroud, butaoVencedor);
        tabuleiro.popularSementes(sementesInicio);//Popular as sementes no stack pane sementesInicio para depois animar para 4 sementes para cada buraco
        for (int i = 0; i < imagensBuraco.length; i++) {
            imagemBuracoMap.put(imagensBuraco[i], tabuleiro.getBuraco(i)); //Key -> ImageView Value -> Buraco
        }
        tabuleiroIniciado = true;

        startServer();
    }

    /**
     * Começar o Servidor se for servidor ou o client se escolheu ser client na
     * checkbox no layout inicial
     */
    public void startServer() {
        if (info.isServer()) {
            try {
                server = new Server(tabuleiro);
                server.enviarObject(Mancala.getInfo().getJogador1());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else {

            try {
                client = new Client(tabuleiro);
                client.enviarObject(Mancala.getInfo().getJogador2());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Quando Clica na ImageView que contem a seta de voltar para trás
     *
     * @param event rato
     */
    @FXML
    private void voltar(MouseEvent event) {
        new MudarLayout("Menu").load();
    }

    /**
     * Quando o rato sai de um Buraco diminuimos a Opacidade para 60%
     *
     * @param e rato
     */
    @FXML
    private void rato_saiu(MouseEvent e) {
        mudaOpacidade((ImageView) e.getSource(), 0.6f);
    }

    /**
     * Quando o rato entra em um buraco Aumentamos a opacidade para 100% e se o
     * tabuleiro tiver iniciado mostramos quantas sementes tem naquele buraco em
     * uma label
     *
     * @param e
     */
    @FXML
    private void rato_entrou(MouseEvent e) {

        mudaOpacidade((ImageView) e.getSource(), 1.0f);
        ImageView imgV = (ImageView) e.getSource();
        if (tabuleiroIniciado) {
            Buraco buracoSelecionado = imagemBuracoMap.get((ImageView) e.getSource());
            label_sementes.setText(buracoSelecionado.getMarbleCount() + " sementes no buraco " + buracoSelecionado.getId());
        }
    }

    /**
     * Metodo responsável por fazer a mudança da opacidade através de um
     * FadeTransition
     *
     * @param node é a ImageView do Buraco
     * @param scale
     */
    private void mudaOpacidade(ImageView node, float scale) {
        FadeTransition obj = new FadeTransition(Duration.millis(100), node);
        obj.setToValue(scale);
        obj.setCycleCount(1);
        obj.play();
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
     * metodo para mudar a cor Do avatar carregando a imagem
     *
     * @param avatar a imageView do avatar
     * @param cor a cor que escolhemos
     */
    private void mudaCorAvatar(ImageView avatar, String cor) {
        try {
            URL path = this.getClass().getResource("../images/avatar_" + cor + ".png");
            InputStream stream = new FileInputStream(path.getPath());
            avatar.setImage(new Image(stream));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TabuleiroController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo para começar o jogo
     */
    private void startGame() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                seJogoComecou = true;
            }
        }, 2 * 1000);

    }

    /**
     * Método que é ativado quando clicamos em um buraco são feitas várias
     * verificações : a primeira é se o jogo começou, se não começou faz return
     * e sai do metodo. depois verifica se o jogador pode fazer o turno , se
     * pode fazer avançar verifica se é servidor ou client , se for Servidor
     * cria uma nova instancia do objeto que representa a informação vinda do
     * lado do servidor e envia para o Client através do Thread na class
     * Connection, se for client faz o mesmo mas para a classe que representa o
     * lado do Client e depois processa o turno para o atual
     * estado(client/server)
     *
     * @param event
     */
    @FXML
    private void click_rato(MouseEvent event) {

        if (!seJogoComecou) {
            return;
        }

        //Verificar se o Buraco é o do jogador e processar a jogada
        Buraco buracoSelecionado = imagemBuracoMap.get((ImageView) event.getSource());
        if (tabuleiro.podeFazerTurno(buracoSelecionado.getId(), tabuleiro.obterTipoJogadorAtual())) {

            if (info.isServer()) {
                try {
                    // System.out.println("Enviar informação do server ");
                    infoRedeLadoServer infoServer = new infoRedeLadoServer("Jogada", buracoSelecionado.getId(), buracoSelecionado.getId());
                    server.enviarObject(infoServer);
                    tabuleiro.processarTurno(buracoSelecionado.getId()); //contador
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else if (!info.isServer()) {
                try {
                    //  System.out.println("Enviar informação do client ");
                    infoRedeLadoClient infoClient = new infoRedeLadoClient("Jogada", buracoSelecionado.getId(), buracoSelecionado.getId());
                    client.enviarObject(infoClient);
                    tabuleiro.processarTurno(buracoSelecionado.getId());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        } else {
            System.out.println("Não é a sua vez de Jogar ");
        }

    }

    /**
     * Quando o Jogo acaba aparece um botão para reniciar o jogo Se clicamos e
     * formos servidor fecha os sockets e streams do servidor e muda para este
     * tabuleiro outraves para resetar todas as variaveis, se for client fecha
     * os sockets e streams do client
     *
     * @param event rato
     */
    @FXML
    private void clickReniciar(MouseEvent event) {
        try {
            System.out.println("Clicou Reniciar");
            if (Mancala.getInfo().isServer()) {
                server.closeServer();
            } else {
                client.closeClient();
            }
            tabuleiroIniciado = false;
            new MudarLayout("Tabuleiro").load();
        } catch (IOException ex) {
            new MudarLayout("Tabuleiro").load();
            Logger.getLogger(TabuleiroController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
