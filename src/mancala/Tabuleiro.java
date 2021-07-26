/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import mancala.buracos.Posicao;
import mancala.buracos.Semente;
import mancala.buracos.Buraco;
import mancala.buracos.Kallah;
import java.util.Timer;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;

/**
 * Aqui está representado o campo de jogo
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
    private Timer tempo;
    private boolean seJogoAcabou;
    private boolean isServer;

    //private ImageView avatarJogador1;
    //private ImageView avatarJogador2;
    /**
     *
     * @param slotImages
     * @param status
     * @param avatarJogadorServidor
     * @param avatarJogadorClient
     */
    public Tabuleiro(ImageView[] slotImages, Label status, int[] coordx, int[] coordy, boolean isServer, Label avatar1_label, Label avatar2_label) {
        this.buracos = obterBuracos(slotImages, coordx, coordy);
        this.jogadorAtual = TipoJogador.JOGADOR_SERVIDOR;
        this.status = status;
        this.isServer = isServer;
        this.avatar1_label = avatar1_label;
        this.avatar2_label = avatar2_label;
        //Mancala.getInfo()..setAvatarJogadorServidor(avatarJogadorServidor); 
        //Mancala.getInfo().getAvatar().setAvatarJogadorServidor(avatarJogadorClient);         
        this.tempo = new Timer();
        this.seJogoAcabou = false;
        atualizaStatusTurno(false);

    }

    private Buraco[] obterBuracos(ImageView[] slotImages, int[] coordx, int[] coordy) {

        final int[] xPos = {614, 524, 434, 344, 254, 164, 75, 164, 254, 344, 434, 524, 614, 705};           //Coordenadas dos buracos em X
        final int[] yPos = {257, 257, 257, 257, 257, 257, 308, 367, 367, 367, 367, 367, 367, 308};          //Coordenadas dos buracos em Y

        Buraco[] buracoTemp = new Buraco[14];

        for (int i = 0; i < buracoTemp.length; i++) {

            // Bounds boundsInScreen = slotImages[i].localToScene(slotImages[i].getBoundsInLocal());
            Posicao pos = new Posicao(coordx[i], coordy[i]);
            if (i == 6) {
                buracoTemp[i] = new Kallah(pos, true, TipoJogador.JOGADOR_SERVIDOR, slotImages[i], i);
                continue;
            }
            if (i == 13) {
                buracoTemp[i] = new Kallah(pos, true, TipoJogador.JOGADOR_CLIENT, slotImages[i], i);
                continue;
            }
            buracoTemp[i] = new Buraco(pos, false, slotImages[i], i);
        }
        return buracoTemp;
    }

    public boolean podeFazerTurno(int buracoSelecionado, TipoJogador tipoJogador) {
        Buraco buracoAtual = getSlot(buracoSelecionado);
        if (buracoAtual.isBuracoKallah()) {
            return false;
        }
        if (buracoAtual.isEmpty()) {
            return false;
        }
        if (tipoJogador == TipoJogador.JOGADOR_SERVIDOR && buracoSelecionado < 6 ) {
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
     *
     * @param sementesInicio
     */
    public void popularSementes(StackPane sementesInicio) {

        //	MarbleColor[] colors = MarbleColor.values();
        for (Buraco buraco : buracos) {

            if (buraco.isBuracoKallah()) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                //int colorIndex = (int) (Math.random() * colors.length);

                Semente sementeAtual = new Semente();

                sementesInicio.getChildren().add(sementeAtual.getImageView());//Adiciona a semente ao tabuleiro
                buraco.adicionaSemente(sementeAtual, 3);

            }

        }

    }

    private boolean seBuracosVazios(int[] slotSet) {
        for (int slot : slotSet) {
            if (!buracos[slot].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void processarTurno(int buracoSelecionado) {
        Turno currentTurn = new Turno(buracos, buracoSelecionado, jogadorAtual);
        boolean podeJogarDenovo = currentTurn.run();

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
            // updatePlayerAvatars();
            Platform.runLater(
                    () -> {
                        // Update UI here.
                        atualizaStatusTurno(podeJogarDenovo);
                    }
            );

        }

        //  obterInfoBuracos();
    }

    private boolean verificarVencedor(int[] buracosJogador1, int[] buracosJogador2) {
        if (seBuracosVazios(buracosJogador1)) {
            return true;
        }
        if (seBuracosVazios(buracosJogador2)) {
            return true;
        }
        return false;
    }

    private void processarQuemGanha(int[] buracosJogador1, int[] buracosJogador2) {
        limparSementes(TipoJogador.JOGADOR_SERVIDOR, buracosJogador1);
        limparSementes(TipoJogador.JOGADOR_CLIENT, buracosJogador2);

        TipoJogador vencedor = TipoJogador.JOGADOR_SERVIDOR;
        if (obterKallah(TipoJogador.JOGADOR_CLIENT).getMarbleCount() > obterKallah(TipoJogador.JOGADOR_SERVIDOR).getMarbleCount()) {
            vencedor = TipoJogador.JOGADOR_CLIENT;
        }

        status.setText(Mancala.getInfo().getNome(vencedor) + " Ganhou!");
    }

    private void limparSementes(TipoJogador tipo, int[] slotSet) {
        Buraco kallah = obterKallah(tipo);
        for (int buraco : slotSet) {
            kallah.addMarbles(buracos[buraco].clearMarbels(), 1);
        }
    }

    private void atualizaStatusTurno(boolean podeIrDeNovo) {
        String defaultText = " está agora a Jogar";
        if (podeIrDeNovo) {
            defaultText = " tem um turno extra por acertar ultima semente no Kallah";
        }
        status.setText(Mancala.getInfo().getNome(jogadorAtual) + defaultText);
        System.out.println(Mancala.getInfo().getNome(jogadorAtual) + defaultText);
    }

    private TipoJogador obterJogadorOposto(TipoJogador tipo) {
        if (tipo == TipoJogador.JOGADOR_SERVIDOR) {
            return TipoJogador.JOGADOR_CLIENT;
        }
        if (tipo == TipoJogador.JOGADOR_CLIENT) {
            return TipoJogador.JOGADOR_SERVIDOR;
        }
        return TipoJogador.JOGADOR_SERVIDOR;
    }

    public Buraco getSlot(int selectedSlot) {

        return buracos[selectedSlot];
    }

    public TipoJogador getCurrentPlayer() {
        return jogadorAtual;
    }

    private Buraco obterKallah(TipoJogador tipo) {
        if (tipo == TipoJogador.JOGADOR_SERVIDOR) {

            return buracos[6];
        }

        return buracos[13];
    }

    private void obterInfoBuracos() {
        int index = 0;
        int infoBuraco[];
        infoBuraco = new int[buracos.length];

        for (Buraco buraco : buracos) {
            infoBuraco[index] = buraco.getNumeroSementes();
            index++;
        }

        // Mancala.getInfo().setBuracosinfo(infoBuraco);
    }

    public void atualizarLabelStatus(String s, int num) {

        Platform.runLater(
                () -> {
                    // Update UI here.

                    if (num == 1) {

                        Mancala.getInfo().setJogador1(s);
                        avatar1_label.setText(Mancala.getInfo().getJogador1());
                    } else if (num == 2) {
                        Mancala.getInfo().setJogador2(s);
                        avatar2_label.setText(Mancala.getInfo().getJogador2());
                    }
                    //Atualizar Status
                    atualizaStatusTurno(false);
                }
        );

    }

}
