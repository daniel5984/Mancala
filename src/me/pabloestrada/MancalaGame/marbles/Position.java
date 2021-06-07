package me.pabloestrada.MancalaGame.marbles;

public class Position {

	private int y;
	private int x;

	public Position(int x, int y) {
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

	public Position getSimilarPosition() {
		int range = 40;
		int newX = (int) (x + ((Math.random() * range) - (range/2)));
		int newY = (int) (y + ((Math.random() * range) - (range/2)));
		return new Position(newX, newY);
	}
}
