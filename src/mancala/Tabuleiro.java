/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.Serializable;
import mancala.buracos.Posicao;
import mancala.buracos.Semente;
import mancala.buracos.Buraco;
import mancala.buracos.Kallah;
import java.util.Timer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * Aqui está representado o campo de jogo e todas as interações
 *
 * @author DanielSilva
 */
public class Tabuleiro implements Serializable {

    boolean ganhou;
    int jogadores[];
    int pontosJogadorA;
    int pontosJogadorB;

    private Buraco[] buracos;
    private TipoJogador jogadorAtual;
    private Label status;
    private Label avatar1_label;
    private Label avatar2_label;
    private Label tipo;
    private Timer tempo;
    private boolean seJogoAcabou;
    private boolean isServer;

    //Quando vence
    private Button butaoVencedor;
    private Rectangle rectangle;

    /**
     * Construtor do tabuleiro
     *
     * @param ArrayDeImageViewBuraco contém um array de ImageViews de todos os
     * buracos
     * @param status é um label que vai descrever a situaçao do jogo
     * @param coordx array das coordenadas em X de todos os buracos
     * @param coordy array das coordenadas em Y de todos os buracos
     * @param isServer diz se este mancala é servidor ou Client
     * @param avatar1_label a label do nome do jogador 1 que fica a esquerda
     * @param avatar2_label a label do nome do jogador 2 que fica a direita
     * @param tipo label que informa ao user se aquela versão do jogo é um
     * server ou um client
     * @param rectangle para fins de quando acaba o jogo
     * @param butaoVencedor quando acaba um jogo mostrar o botão para resetar
     */
    public Tabuleiro(ImageView[] ArrayDeImageViewBuraco, Label status, int[] coordx, int[] coordy, boolean isServer, Label avatar1_label, Label avatar2_label, Label tipo, Rectangle rectangle, Button butaoVencedor) {
        this.buracos = obterBuracos(ArrayDeImageViewBuraco, coordx, coordy);
        this.jogadorAtual = TipoJogador.JOGADOR_SERVIDOR;
        this.status = status;
        this.isServer = isServer;
        this.avatar1_label = avatar1_label;
        this.avatar2_label = avatar2_label;
        this.tipo = tipo;
        this.butaoVencedor = butaoVencedor;
        this.rectangle = rectangle;
        //Mancala.getInfo()..setAvatarJogadorServidor(avatarJogadorServidor); 
        //Mancala.getInfo().getAvatar().setAvatarJogadorServidor(avatarJogadorClient);         
        this.tempo = new Timer();
        this.seJogoAcabou = false;
        atualizaStatusTurno(false);

    }

    /**
     * Aqui vai criar todos os buracos na devida posição e com as respectivas
     * imageViews, verifica quais os buracos que são kallah
     *
     * @param ArrayDeImageViewBuraco array de Imageviews
     * @param coordx array das coordenadas em X de todos os buracos
     * @param coordy array das coordenadas em Y de todos os buracos
     * @return retorna um array de buracos preenchidos
     */
    private Buraco[] obterBuracos(ImageView[] ArrayDeImageViewBuraco, int[] coordx, int[] coordy) {

        Buraco[] buracoTemp = new Buraco[14];

        for (int i = 0; i < buracoTemp.length; i++) {

            Posicao pos = new Posicao(coordx[i], coordy[i]);
            if (i == 6) {
                buracoTemp[i] = new Kallah(pos, true, TipoJogador.JOGADOR_SERVIDOR, ArrayDeImageViewBuraco[i], i);
                continue;
            }
            if (i == 13) {
                buracoTemp[i] = new Kallah(pos, true, TipoJogador.JOGADOR_CLIENT, ArrayDeImageViewBuraco[i], i);
                continue;
            }
            buracoTemp[i] = new Buraco(pos, false, ArrayDeImageViewBuraco[i], i);
        }
        return buracoTemp;
    }

    /**
     * Este metodo certifica-se se o jogador pode fazer o turno fazendo uma
     * serie de verificações
     *
     * @param buracoSelecionado Este é o buraco ao qual o utilizador clicou em
     * forma de id
     * @param tipoJogador este é o tipo de jogador podendo ser servidor ou
     * client
     * @return
     */
    public boolean podeFazerTurno(int buracoSelecionado, TipoJogador tipoJogador) {
        Buraco buracoAtual = getBuraco(buracoSelecionado);
        if (buracoAtual.isBuracoKallah()) {
            return false;
        }
        if (buracoAtual.isBuracoVazio()) {
            return false;
        }
        if (tipoJogador == TipoJogador.JOGADOR_SERVIDOR && buracoSelecionado < 6) {
            return false;
        }
        if (tipoJogador == TipoJogador.JOGADOR_CLIENT && buracoSelecionado > 6) {
            return false;
        }
        if (tipoJogador == TipoJogador.JOGADOR_SERVIDOR && !Mancala.getInfo().isServer()) {
            return false;
        }
        if (tipoJogador == TipoJogador.JOGADOR_CLIENT && Mancala.getInfo().isServer()) {
            return false;
        }

        return true;
    }

    /**
     * Este metodo vai popular as sementes 4 em cada buraco exceto nos kallahs
     *
     * @param sementesInicio Aqui é onde as sementes vão ser iniciadas , é um
     * stack pane invisivel no topo do lado esquerdo aonde vão ser movidas para
     * os devidos buracos
     */
    public void popularSementes(StackPane sementesInicio) {
        for (Buraco buraco : buracos) {
            if (buraco.isBuracoKallah()) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                Semente sementeAtual = new Semente();
                sementesInicio.getChildren().add(sementeAtual.getImageView());//Adiciona a semente ao tabuleiro
                buraco.adicionaSemente(sementeAtual, 3);

            }

        }

    }

    /**
     * Vai verificar se o array de buracos está vazio
     *
     * @param ArrayIDBuracos array de ids de buracos
     * @return retorna True se todos os buracos que tem o id no array de buraco
     * estiverem vazios senão é falso
     */
    private boolean seBuracosVazios(int[] ArrayIDBuracos) {
        for (int idBuraco : ArrayIDBuracos) {
            if (!buracos[idBuraco].isBuracoVazio()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Metodo responsável por processar o turno faz turno e verifica se pode
     * jogar denovo e depois verifica se alguem ganhou
     *
     * @param buracoSelecionado o buraco que o user selecionou
     */
    public void processarTurno(int buracoSelecionado) {
        Turno turnoAtual = new Turno(buracos, buracoSelecionado, jogadorAtual);
        boolean podeJogarDenovo = turnoAtual.run();
        TipoJogador oldPlayer = jogadorAtual;
        jogadorAtual = obterJogadorOposto(oldPlayer);

        if (podeJogarDenovo) {
            jogadorAtual = oldPlayer;
        }

        int[] buracosJogador1 = {0, 1, 2, 3, 4, 5};
        int[] buracosJogador2 = {7, 8, 9, 10, 11, 12};

        if (verificarVencedor(buracosJogador1, buracosJogador2)) {
            seJogoAcabou = true;
            processarQuemGanha(buracosJogador1, buracosJogador2);
        } else {
            Platform.runLater(
                    () -> {
                        // Update UI here.
                        atualizaStatusTurno(podeJogarDenovo);
                    }
            );
        }
    }

    /**
     * Verifica se os buracos do jogador1 e os buracos do jogador2 estão vazios
     *
     * @param buracosJogador1 ids dos buracos do jogador 1
     * @param buracosJogador2 ids dos buracos do jogador 2
     * @return retorna true se algum dos jogadores tiver os buracos vazios
     */
    private boolean verificarVencedor(int[] buracosJogador1, int[] buracosJogador2) {
        if (seBuracosVazios(buracosJogador1)) {
            return true;
        }
        if (seBuracosVazios(buracosJogador2)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica quem ganhou
     *
     * @param buracosJogador1 buracos do jogador1
     * @param buracosJogador2 buracos do jogador2
     */
    private void processarQuemGanha(int[] buracosJogador1, int[] buracosJogador2) {
        limparSementes(TipoJogador.JOGADOR_SERVIDOR, buracosJogador1);
        limparSementes(TipoJogador.JOGADOR_CLIENT, buracosJogador2);

        TipoJogador vencedor = TipoJogador.JOGADOR_SERVIDOR;
        if (obterKallah(TipoJogador.JOGADOR_CLIENT).getMarbleCount() > obterKallah(TipoJogador.JOGADOR_SERVIDOR).getMarbleCount()) {
            vencedor = TipoJogador.JOGADOR_CLIENT;
        }
        String nome = Mancala.getInfo().getNome(vencedor);
        System.out.println(nome + "-> Ganhou!");
        Platform.runLater(
                () -> {
                    status.setText(nome + " Ganhou!");
                    this.rectangle.setVisible(true);
                    this.rectangle.setDisable(false);
                    this.butaoVencedor.setVisible(true);
                    this.butaoVencedor.setDisable(false);
                }
        );
    }

    /**
     * Vai limpar as sementes de todos os buracos
     *
     * @param tipo tipo de jogador
     * @param arrayBuracos
     */
    private void limparSementes(TipoJogador tipo, int[] arrayBuracos) {
        Buraco kallah = obterKallah(tipo);
        for (int buraco : arrayBuracos) {
            kallah.adicionaSementes(buracos[buraco].limparTodasAsSementes(), 1);
        }
    }

    /**
     * Atualiza o label que mostra o status do jogo e o label que informa se é
     * servidor ou client
     *
     * @param podeIrDeNovo quando o jogador pode fazer outra jogada
     */
    private void atualizaStatusTurno(boolean podeIrDeNovo) {
        String textoDefault = " está agora a Jogar";
        if (podeIrDeNovo) {
            textoDefault = " tem um turno extra por acertar ultima semente no Kallah";
        }
        status.setText(Mancala.getInfo().getNome(jogadorAtual) + textoDefault);
        System.out.println(Mancala.getInfo().getNome(jogadorAtual) + textoDefault);
        if (this.isServer) {
            tipo.setText("Server " + Mancala.getInfo().getJogador1());
        } else {
            tipo.setText("Client " + Mancala.getInfo().getJogador2());
        }

    }

    /**
     * Obtem o tipo de Jogador Oposto
     *
     * @param tipo
     * @return
     */
    private TipoJogador obterJogadorOposto(TipoJogador tipo) {
        if (tipo == TipoJogador.JOGADOR_SERVIDOR) {
            return TipoJogador.JOGADOR_CLIENT;
        }
        if (tipo == TipoJogador.JOGADOR_CLIENT) {
            return TipoJogador.JOGADOR_SERVIDOR;
        }
        return TipoJogador.JOGADOR_SERVIDOR;
    }

    /**
     * Obtem o buraco através do ID
     *
     * @param idBuracoSelecionado id do buraco que queremos
     * @return retorna o buraco
     */
    public Buraco getBuraco(int idBuracoSelecionado) {

        return buracos[idBuracoSelecionado];
    }

    /**
     *
     * @return obtem o tipo de jogador atual
     */
    public TipoJogador obterTipoJogadorAtual() {
        return jogadorAtual;
    }

    /**
     *
     * @param tipo TipoJogador
     * @return Obtem o kallah conforme o tipo
     */
    private Buraco obterKallah(TipoJogador tipo) {
        if (tipo == TipoJogador.JOGADOR_SERVIDOR) {

            return buracos[6];
        }

        return buracos[13];
    }

    /**
     * Atualiza as labels com os nomes que foram enviados pelo servidor e pelo
     * client
     *
     * @param nome nome do jogador enviado pelo client ou server
     * @param num .
     */
    public void atualizarLabelStatus(String nome, int num) {

        Platform.runLater(() -> {
            // Update UI here.

            if (num == 1) {

                Mancala.getInfo().setJogador1(nome);
                avatar1_label.setText(Mancala.getInfo().getJogador1());
            } else if (num == 2) {
                Mancala.getInfo().setJogador2(nome);
                avatar2_label.setText(Mancala.getInfo().getJogador2());
            }
            //Atualizar Status
            atualizaStatusTurno(false);
        }
        );

    }

}
