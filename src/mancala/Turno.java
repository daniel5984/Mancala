/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import mancala.buracos.Semente;
import mancala.buracos.Buraco;

/**
 *
 * @author DanielSilva
 */
public class Turno {

    private Buraco[] buracos;
    private int buracoSelecionado;
    private TipoJogador tipoJogador;

    public Turno(Buraco[] buracos, int buracoSelecionado, TipoJogador tipoJogador) {
        this.buracoSelecionado = buracoSelecionado;
        this.buracos = buracos;
        this.tipoJogador = tipoJogador;
    }

    public boolean run() {
        Buraco buracoAtual = buracos[buracoSelecionado];
        Semente[] sementes = buracoAtual.clearMarbels();
        //buracoAtual.updateMarbleLabel();
        int sementeAtual = 0;
        int currentIndex = buracoSelecionado + 1;
        boolean podeJogarDenovo = false;
        while (sementeAtual < sementes.length) {
            if (currentIndex == 14) {
                currentIndex = 0;
            }
            Buraco currentSlotObject = buracos[currentIndex];
            if (currentSlotObject.isBuracoKallah() && !verificaSeKallah(currentIndex)) {
                currentIndex++;
                continue;
            }
            if (!currentSlotObject.isBuracoKallah() && isLastMarbleInTurn(sementeAtual, sementes.length)) {
                processarCaptura(currentIndex);
            }
            if (isLastMarbleInTurn(sementeAtual, sementes.length)) {
                if (currentSlotObject.isBuracoKallah() && verificaSeKallah(currentIndex)) {
                    podeJogarDenovo = true;
                }
                if (!currentSlotObject.isBuracoKallah()) {
                    processarCaptura(currentIndex);
                }
            }
            currentSlotObject.addMarble(sementes[sementeAtual], 1);
            sementeAtual++;
            currentIndex++;
        }
        return podeJogarDenovo;
    }

    private boolean verificaSeKallah(int buraco) {
        int kallahID = obterKallah().getId();
        if (buraco == kallahID) {
            return true;
        }
        return false;
    }

    private boolean isLastMarbleInTurn(int currentMarble, int totalMarbles) {
        if (currentMarble == totalMarbles - 1) {
            return true;
        }
        return false;
    }

    private Buraco obterKallah() {
        if (tipoJogador == TipoJogador.JOGADOR_1) {
            return buracos[13];
           
        }
         return buracos[6];
    }

    private void processarCaptura(int buracoAtual_index) {
        Buraco buracoAtual = buracos[buracoAtual_index];
        if (!buracoAtual.isEmpty()) {
            return;
        }
        if (!buracoAtual.isMySide(tipoJogador)) {
            return;
        }
        obterKallah().addMarbles(obterBuracoOposto(buracoAtual).clearMarbels(), 1);
    }

    private Buraco obterBuracoOposto(Buraco buraco) {
        if (buraco.isBuracoKallah()) {
            return null;
        }
        int slotId = buraco.getId();
        return buracos[((2 * (slotId - 6)) * -1) + slotId];
    }

}
