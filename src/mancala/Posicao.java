package mancala;

public class Posicao {

	private int y;
	private int x;

	public Posicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
	
	public String toString() {
		return "x: "+ x+", "+"y: " + y;
	}

	public Posicao getSimilarPosition() {
		int range = 40;
		int newX = (int) (x + ((Math.random() * range) - (range/2)));
		int newY = (int) (y + ((Math.random() * range) - (range/2)));
		return new Posicao(newX, newY);
	}
}
