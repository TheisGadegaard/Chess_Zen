package Logic;

import staticValues.Colour;
import staticValues.Piece;

public class Evaluation {

	Piece[][] board;
	double SUM;
	boolean isWhite;
	int[][] knightBoard;
	int[] WpawnRow = new int[] {0, 30, 14, 2, 0, -1, 0, 0};
	int[] BpawnRow = new int[] {0, 0, -1, 0, 2, 14, 30, 0};
	int[] pawnLine = new int[] {-2, 0, 3, 4, 5, 1, -2, -2};

	public double evaluateState(Gamestate gamestate, boolean isWhite) {

		board = gamestate.getBoard();
		SUM = 0;
		this.isWhite = isWhite;
		knightBoard = generateKnightBoard();
		

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if((isWhite && board[i][j].getColour() == Colour.WHITE) || !isWhite && board[i][j].getColour() == Colour.BLACK) {
					switch(board[i][j].getType()) {
					case EMPTY:
						break;
					case PAWN:		SUM += evaluatePawn(i, j);
					break;	
					case KNIGHT:	SUM += evaluateKnight(i, j);
					break;
					case BISHOP:	SUM += evaluetateBishop(i, j);
					break;
					case ROOK:		SUM += evaluateRook(i, j);
					break;
					case QUEEN:		SUM += evaluateQueen(i, j);
					break;
					case KING: 		SUM += evaluateKing();
					break;
					default:
						System.err.println("Illegal evaluation in method 'evaluateState' in class 'Evaluation'");
					}
				}
			}
		}

		return SUM;
	}
	//shorthand method for how many spaces the knight is covering based on its position
	private int[][] generateKnightBoard() {

		int[][] kb = new int[8][8];
		kb[0] = new int[]{2, 3, 4, 4, 4, 4, 3, 2};
		kb[1] = new int[]{3, 4, 6, 6, 6, 6, 4, 3};
		kb[2] = new int[]{4, 6, 8, 8, 8, 8, 6, 4};
		kb[3] = new int[]{4, 6, 8, 8, 8, 8, 6, 4};
		kb[4] = new int[]{4, 6, 8, 8, 8, 8, 6, 4};
		kb[5] = new int[]{4, 6, 8, 8, 8, 8, 6, 4};
		kb[6] = new int[]{3, 4, 6, 6, 6, 6, 4, 3};
		kb[7] = new int[]{2, 3, 4, 4, 4, 4, 3, 2};

		return kb;
	}
	//queen is worth 900 points plus the number of covered spaces
	private double evaluateQueen(int yPos, int xPos) {
		double value = 900 + (coverDirection(yPos, xPos, 1, 1) +
				coverDirection(yPos, xPos, 1, 0) +
				coverDirection(yPos, xPos, 1, -1) +
				coverDirection(yPos, xPos, 0, 1) +
				coverDirection(yPos, xPos, 0, -1) +
				coverDirection(yPos, xPos, -1, 1) +
				coverDirection(yPos, xPos, -1, 0) +
				coverDirection(yPos, xPos, -1, -1));
	//	System.out.println("evaluateQueen(" + isWhite + ")=" + value);
		return value;
	}
	private double evaluateKing() {
		return 10000;

	}
	//rook is worth 500 points plus 1.5 times the number of covered spaces
	private double evaluateRook(int yPos, int xPos) {
		double value = 500 + 1.5*(coverDirection(yPos, xPos, 1, 0) +
				coverDirection(yPos, xPos, 0, 1) +
				coverDirection(yPos, xPos, -1, 0) +
				coverDirection(yPos, xPos, 0, -1));
	//	System.out.println("evaluateRook(" + isWhite + ")=" + value);
		return value;

	}
	//bishop is worth 300 points plus 2 times the number of covered spaces
	private double evaluetateBishop(int yPos, int xPos) {
		double value = 300 + 2*(coverDirection(yPos, xPos, 1, 1) +
				coverDirection(yPos, xPos, -1, -1) +
				coverDirection(yPos, xPos, -1, 1) +
				coverDirection(yPos, xPos, 1, -1));
	//	System.out.println("evaluateBishop(" + isWhite + ")=" + value);
		return value;

	}
	//knight is worth 300 points plus 3 times the number of covered spaces
	private double evaluateKnight(int yPos, int xPos) {
		double value = 300 + 3*knightBoard[yPos][xPos];
	//	System.out.println("evaluateKnight(" + isWhite + ")=" + value);
		return value;

	}
	//pawn is worth 100 points plus a value dependent on how close it is to the opponents side of the field
	private double evaluatePawn(int posY, int posX) {
		double value;
		if(isWhite) {
			value = 100 + WpawnRow[posY] + ((double) (pawnLine[posX]*(7-posY))/2);
		} else {
			value = 100 + BpawnRow[posY] + ((double) (pawnLine[posX]*posY)/2);
		}
	//	System.out.println("evaluatePawn(" + isWhite + ")=" + value);
		return value;
	}
	//method used to cover directions for bishops, rooks and queens
	private int coverDirection(int yPos, int xPos, int yDir, int xDir) {
		int nextY = yPos+yDir;
		int nextX = xPos+xDir;
		if(nextY <= 7 && nextY >=0 && nextX <= 7 && nextX >= 0) {
			if(board[nextY][nextX].getColour() == Colour.NONE) {
				return 1+coverDirection(nextY, nextX, yDir, xDir);
			} else {
				return 1;
			}
		} else {
			return 0;
		}
	}
}