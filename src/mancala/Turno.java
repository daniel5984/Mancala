/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancala;

import mancala.buracos.Semente;
import mancala.buracos.Buraco;

/**
 * Esta class contem vários metodos importantes quando se faz cada turno
 *
 * @author DanielSilva
 */
public class Turno {

    private Buraco[] buracos;
    private int buracoSelecionado;
    private TipoJogador tipoJogador;

    /**
     * Construtor do turno
     *
     * @param buracos um array de buracos
     * @param buracoSelecionado o buraco que clickei
     * @param tipoJogador o tipo de jogador que sou
     */
    public Turno(Buraco[] buracos, int buracoSelecionado, TipoJogador tipoJogador) {
        this.buracoSelecionado = buracoSelecionado;
        this.buracos = buracos;
        this.tipoJogador = tipoJogador;
    }

    /**
     * O run faz toda a logica de processar a captura de sementes
     *
     * @return retorna true se pode jogar denovo, quando a utima semente cai em
     * um kallah
     */
    public boolean run() {
        Buraco buracoAtual = buracos[buracoSelecionado];
        Semente[] sementes = buracoAtual.limparTodasAsSementes();

        int sementeAtual = 0;
        int indexAtual = buracoSelecionado + 1;
        boolean podeJogarDenovo = false;
        while (sementeAtual < sementes.length) {
            if (indexAtual == 14) {
                indexAtual = 0;
            }
            Buraco objBuracoAtual = buracos[indexAtual];
            if (objBuracoAtual.isBuracoKallah() && !verificaSeKallah(indexAtual)) {
                indexAtual++;
                continue;
            }
            if (!objBuracoAtual.isBuracoKallah() && seUltimaSemente(sementeAtual, sementes.length)) {//Se for ultima semente e não for kallah
                processarCaptura(indexAtual);
            }
            if (seUltimaSemente(sementeAtual, sementes.length)) { //Se for a ultima semente
                if (objBuracoAtual.isBuracoKallah() && verificaSeKallah(indexAtual)) { //Se for ultima semente e cair em um kallah pode jogar de novo
                    podeJogarDenovo = true;
                }
                if (!objBuracoAtual.isBuracoKallah()) {
                    processarCaptura(indexAtual);
                }
            }
            objBuracoAtual.adicionaSemente(sementes[sementeAtual], 1);
            sementeAtual++;
            indexAtual++;
        }
        return podeJogarDenovo;
    }

    /**
     * Verifica se o buraco é kallah
     *
     * @param buraco O id do buraco
     * @return retorna true se for kalla e falso se não for
     */
    private boolean verificaSeKallah(int buraco) {
        int kallahID = obterKallah().getId();
        if (buraco == kallahID) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se esta é a ultima semente
     *
     * @param sementeAtual
     * @param totalSementes o número total de sementes desta jogada
     * @return Se for a ultima semente retorna true, senão retorna false
     */
    private boolean seUltimaSemente(int sementeAtual, int totalSementes) {
        if (sementeAtual == totalSementes - 1) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return o Buraco kallah confrome o tipo de jogador
     */
    private Buraco obterKallah() {
        if (tipoJogador == TipoJogador.JOGADOR_SERVIDOR) {
            return buracos[13];

        }
        return buracos[6];
    }

    /**
     * Verifica se o buraco é vazio ou se está no meu lado (tipodeJogador)atual
     * Caso esteja no meu lado e o buraco seja vazio fazemos a captura das
     * sementes do lado oposto
     *
     * @param buracoAtual_index
     */
    private void processarCaptura(int buracoAtual_index) {
        Buraco buracoAtual = buracos[buracoAtual_index];
        if (!buracoAtual.isBuracoVazio()) {
            return;
        }
        if (!buracoAtual.isMeuLado(tipoJogador)) {
            return;
        }
        obterKallah().adicionaSementes(obterBuracoOposto(buracoAtual).limparTodasAsSementes(), 1);
    }

    /**
     * Vai obter o buraco do lado oposto
     *
     * @param buraco o buraco do nosso lado
     * @return o buraco do lado oposto
     */
    private Buraco obterBuracoOposto(Buraco buraco) {
        if (buraco.isBuracoKallah()) {
            return null;
        }
        int idBuraco = buraco.getId();
        return buracos[((2 * (idBuraco - 6)) * -1) + idBuraco];
    }

}
