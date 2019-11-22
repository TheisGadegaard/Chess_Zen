package Logic;

import staticValues.Piece;

public class Move {

	private int startPosX, startPosY, endPosX, endPosY;
	
	public Move(int startPosX, int startPosY, int endPosX, int endPosY) {
		this.startPosX = startPosX;
		this.startPosY = startPosY;
		this.endPosX = endPosX;
		this.endPosY = endPosY;
	}

	public int getStartPosX() {
		return startPosX;
	}

	public int getStartPosY() {
		return startPosY;
	}

	public int getEndPosX() {
		return endPosX;
	}

	public int getEndPosY() {
		return endPosY;
	}

	public void printMove() {
		System.out.println(startPosX + ", " + startPosY + " --> " + endPosX + ", " + endPosY);
	}
}