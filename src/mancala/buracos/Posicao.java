package mancala.buracos;

import java.io.Serializable;

/**
 * Esta class é responsabel por conter a posição das sementes e randomizar cada
 * semente um pouco do lugar desejado
 *
 * @author DanielSilva
 */
public class Posicao {

    private int y;
    private int x;

    /**
     *
     * @param x posição x que recebe
     * @param y posição y que recebe
     */
    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return obter Y
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @return obter X
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return Retorna uma String com as posições
     */
    public String toString() {
        return "x: " + x + ", " + "y: " + y;
    }

    /**
     * Aqui as posições são mudadas aleatoriamente em um especifico range Isto
     * server para as sementes não ficarem uma em cima da outra e conseguirmos
     * destingi-las em cada buraco
     *
     * @return retorna posição ligeiramente alterada aleatóriamente
     */
    public Posicao obterPosicaoSemelhante() {
        int range = 30;
        int newX = (int) (x + ((Math.random() * range) - (range / 2)));
        int newY = (int) (y + ((Math.random() * range) - (range / 2)));
        return new Posicao(newX, newY);
    }
}
