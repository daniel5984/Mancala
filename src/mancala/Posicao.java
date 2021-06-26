package mancala;

/**
 *
 * @author DanielSilva
 */
public class Posicao {

	private int y;
	private int x;

    /**
     *
     * @param x
     * @param y
     */
    public Posicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

    /**
     *
     * @return
     */
    public int getY() {
		return y;
	}

    /**
     *
     * @return
     */
    public int getX() {
		return x;
	}
	
	public String toString() {
		return "x: "+ x+", "+"y: " + y;
	}

    /**
     *
     * @return
     */
    public Posicao getSimilarPosition() {
		int range = 15;
		int newX = (int) (x + ((Math.random() * range) - (range/2)));
		int newY = (int) (y + ((Math.random() * range) - (range/2)));
		return new Posicao(newX, newY);
	}
}
