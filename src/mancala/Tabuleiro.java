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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Aqui está representado o campo de jogo
 *
 * @author DanielSilva
 */
public class Tabuleiro implements Serializable{

    boolean ganhou;
    int jogadores[];
    int pontosJogadorA;
    int pontosJogadorB;

    private Buraco[] buracos;
    private TipoJogador jogadorAtual;
    private Label status;
    private Timer tempo;
    private boolean seJogoAcabou;
    private ImageView avatarJogador1;
    private ImageView avatarJogador2;

    /**
     *
     * @param slotImages
     * @param status
     * @param avatarJogador1
     * @param avatarJogador2
     */
    public Tabuleiro(ImageView[] slotImages, Label status, ImageView avatarJogador1, ImageView avatarJogador2, int[] coordx, int[] coordy) {
        this.buracos = obterBuracos(slotImages, coordx, coordy);
        this.jogadorAtual = TipoJogador.JOGADOR_1;
        this.status = status;
        this.avatarJogador1 = avatarJogador1;
        this.avatarJogador2 = avatarJogador2;
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
                buracoTemp[i] = new Kallah(pos, true, TipoJogador.JOGADOR_1, slotImages[i], i);
                continue;
            }
            if (i == 13) {
                buracoTemp[i] = new Kallah(pos, true, TipoJogador.JOGADOR_2, slotImages[i], i);
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
        if (tipoJogador == TipoJogador.JOGADOR_1 && buracoSelecionado < 6) {
            return false;
        }
        if (tipoJogador == TipoJogador.JOGADOR_2 && buracoSelecionado > 6) {
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

                Semente currentMarble = new Semente();

                sementesInicio.getChildren().add(currentMarble.getImageView());
                buraco.addMarble(currentMarble, 3);
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
            atualizaStatusTurno(podeJogarDenovo);
        }
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
        limparSementes(TipoJogador.JOGADOR_1, buracosJogador1);
        limparSementes(TipoJogador.JOGADOR_2, buracosJogador2);

        TipoJogador vencedor = TipoJogador.JOGADOR_1;
        if (obterKallah(TipoJogador.JOGADOR_2).getMarbleCount() > obterKallah(TipoJogador.JOGADOR_1).getMarbleCount()) {
            vencedor = TipoJogador.JOGADOR_2;
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
    }

    private TipoJogador obterJogadorOposto(TipoJogador tipo) {
        if (tipo == TipoJogador.JOGADOR_1) {
            return TipoJogador.JOGADOR_2;
        }
        if (tipo == TipoJogador.JOGADOR_2) {
            return TipoJogador.JOGADOR_1;
        }
        return TipoJogador.JOGADOR_1;
    }

    public Buraco getSlot(int selectedSlot) {

        return buracos[selectedSlot];
    }

    public TipoJogador getCurrentPlayer() {
        return jogadorAtual;
    }

    private Buraco obterKallah(TipoJogador tipo) {
        if (tipo == TipoJogador.JOGADOR_1) {

            return buracos[6];
        }

        return buracos[13];
    }

}
