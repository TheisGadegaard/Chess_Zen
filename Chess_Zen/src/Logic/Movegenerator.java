package Logic;

import java.util.ArrayList;

import staticValues.Colour;
import staticValues.Piece;
import staticValues.PieceType;

public class Movegenerator {

	private Gamestate currentGamestate;
	private ArrayList<Move> moves;

	public Movegenerator(Gamestate currentGamestate) {
		this.currentGamestate = currentGamestate;
		moves = new ArrayList<Move>();
	}

	public ArrayList<Move> generateMoves(){

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
//				System.out.println("i=" + i + " j=" + j + " PieceType=" + currentGamestate.getBoard()[i][j].getType() + " PieceColour=" + currentGamestate.getBoard()[i][j].getColour());
				if(currentGamestate.getBoard()[i][j].getType() != PieceType.EMPTY && 
						currentGamestate.getBoard()[i][j].getColour() == currentGamestate.getTurnColour()) {		//TODO implement king-in-check method before each move.add() call

					switch(currentGamestate.getBoard()[i][j].getType()) {	
					case PAWN:
						pawnMoves(currentGamestate, i, j, currentGamestate.getTurnColour());
						break;
					case KNIGHT:
						staticMoves(currentGamestate, i, j, 2, 1, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, 1, 2, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, -1, 2, currentGamestate.getTurnColour());	
						staticMoves(currentGamestate, i, j, -2, 1, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, -2, -1, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, -1, -2, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, 1, -2, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, 2, -1, currentGamestate.getTurnColour());
						break;
					case BISHOP:
						coverDirection(currentGamestate, i, j, i, j, 1, 1, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, -1, 1, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, 1, -1, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, -1, -1, currentGamestate.getTurnColour());
						break;
					case ROOK:
						coverDirection(currentGamestate, i, j, i, j, 0, 1, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, 1, 0, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, 0, -1, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, -1, 0, currentGamestate.getTurnColour());
						break;
					case QUEEN:
						coverDirection(currentGamestate, i, j, i, j, 1, 1, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, -1, 1, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, 1, -1, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, -1, -1, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, 0, 1, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, 1, 0, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, 0, -1, currentGamestate.getTurnColour());
						coverDirection(currentGamestate, i, j, i, j, -1, 0, currentGamestate.getTurnColour());
						break;
					case KING:
						staticMoves(currentGamestate, i, j, 1, 1, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, -1, 1, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, 1, -1, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, -1, -1, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, 0, 1, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, 1, 0, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, 0, -1, currentGamestate.getTurnColour());
						staticMoves(currentGamestate, i, j, -1, 0, currentGamestate.getTurnColour());
						break;
					case EMPTY: 
						break;
					default: System.err.println("Invalid PieceType");
					}
				}
//				System.out.println("moves.size()=" + moves.size());
			}
		}

		//TODO implement castling
		return moves;
	}

	//method used for knight and king moves
	private void staticMoves(Gamestate currentGamestate, int startX, int startY, int dirX, int dirY, Colour playerColour) {
		if(startX+dirX < 8 && startX+dirX >= 0 && startY+dirY < 8 && startY+dirY >= 0 &&
				currentGamestate.getBoard()[startX+dirX][startY+dirY].getColour() != playerColour) {
			moves.add(new Move(startX, startY, startX+dirX, startY+dirY));
		}
	}

	private void pawnMoves(Gamestate currentGamestate, int startX, int startY, Colour playerColour) {
		int direction = 0;
		if(playerColour.equals(Colour.BLACK)) {
			direction = 1;

			//Special move from starting position
			if(startY == 1 &&
					currentGamestate.getBoard()[startX][startY+1].getColour() == Colour.NONE &&
					currentGamestate.getBoard()[startX][startY+2].getColour() == Colour.NONE) {
				moves.add(new Move(startX, startY, startX, startY+2));
			}
			//Diagonal captures
//			System.out.println("startY+direction=" + (startY+direction) + " startX+1=" + (startX+1));
//			System.out.println("startX=" + startX + " startY=" + startY + " direction=" + direction);
			if(startX+1 < 8 && 
					currentGamestate.getBoard()[startX+1][startY+direction].getColour() == Colour.WHITE) {
				moves.add(new Move(startX, startY, startX+1, startY+direction));
			}
			if(startX-1 >= 0 && currentGamestate.getBoard()[startX-1][startY+direction].getColour() == Colour.WHITE) {
				moves.add(new Move(startX, startY, startX-1, startY+direction));
			}
		} else if(playerColour.equals(Colour.WHITE)) {
			direction = -1;

			//Special move from starting position
			if(startY == 6 &&
					currentGamestate.getBoard()[startX][startY-1].getColour() == Colour.NONE &&
					currentGamestate.getBoard()[startX][startY-2].getColour() == Colour.NONE) {
				moves.add(new Move(startX, startY, startX, startY-2));
			}
			//Diagonal captures
			//			System.out.println("startX=" + startX + " startY=" + startY + " direction=" + direction + " playerColour=" + playerColour);
			//			System.out.println("startX=" + (startX+1));
			if(startX+1 < 8 && currentGamestate.getBoard()[startX+1][startY+direction].getColour() == Colour.BLACK) {
				moves.add(new Move(startX, startY, startX+1, startY+direction));
			}
			if(startX-1 >= 0 && currentGamestate.getBoard()[startX-1][startY+direction].getColour() == Colour.BLACK) {
				moves.add(new Move(startX, startY, startX-1, startY+direction));
			}
		} else {
			System.err.println("playerColour not black/white in class Movegenerator");
		}

		//One move forward
		if(currentGamestate.getBoard()[startX][startY+direction].getColour() == Colour.NONE) {
			moves.add(new Move(startX, startY, startX, startY+direction));
		}
	}

	//method used to cover directions for bishops, rooks and queens
	private void coverDirection(Gamestate currentGamestate, int startX, int startY, int nextX, int nextY, int dirX, int dirY, Colour playerColour) {
		if(nextX+dirX < 8 && nextX+dirX >= 0 && nextY+dirY < 8 && nextY+dirY >= 0 &&
				currentGamestate.getBoard()[nextX+dirX][nextY+dirY].getColour() == Colour.NONE) {
			moves.add(new Move(startX, startY, nextX+dirX, nextY+dirY));
			coverDirection(currentGamestate, startX, startY, nextX+dirX, nextY+dirY, dirX, dirY, playerColour);

		} else if(nextX+dirX < 8 && nextX+dirX >= 0 && nextY+dirY < 8 && nextY+dirY >= 0 &&
				currentGamestate.getBoard()[nextX+dirX][nextY+dirY].getColour() != currentGamestate.getBoard()[startX][startY].getColour()) {
			moves.add(new Move(startX, startY, nextX+dirX, nextY+dirY));
		}
	}

}












