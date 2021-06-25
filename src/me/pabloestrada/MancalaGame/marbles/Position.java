package me.pabloestrada.MancalaGame.marbles;

/**
 *
 * @author DanielSilva
 */
public class Position {

	private int y;
	private int x;

    /**
     *
     * @param x
     * @param y
     */
    public Position(int x, int y) {
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
    public Position getSimilarPosition() {
		int range = 40;
		int newX = (int) (x + ((Math.random() * range) - (range/2)));
		int newY = (int) (y + ((Math.random() * range) - (range/2)));
		return new Position(newX, newY);
	}
}
