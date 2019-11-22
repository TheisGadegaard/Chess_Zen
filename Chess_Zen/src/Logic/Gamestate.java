package Logic;

import staticValues.Colour;
import staticValues.Piece;
import staticValues.PieceType;

public class Gamestate {

	private Piece[][] board = new Piece[8][8];
	private Colour colour;

	public Gamestate() {

		colour = Colour.WHITE;

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = new Piece(Colour.NONE, PieceType.EMPTY);	//Setting all spaces to empty
			}
		}

		for(int i = 0; i < 8; i++) {
			board[i][1] = new Piece(Colour.BLACK, PieceType.PAWN);		//Filling second row with black pawns
			board[i][6] = new Piece(Colour.WHITE, PieceType.PAWN);	
			//Filling seventh row with white pawns
		}


		// Inserting black pieces
		board[0][0] = new Piece(Colour.BLACK, PieceType.ROOK);			//Rook
		board[1][0] = new Piece(Colour.BLACK, PieceType.KNIGHT);		//Knight
		board[2][0] = new Piece(Colour.BLACK, PieceType.BISHOP);		//Bishop
		board[3][0] = new Piece(Colour.BLACK, PieceType.QUEEN);			//Queen
		board[4][0] = new Piece(Colour.BLACK, PieceType.KING);			//King
		board[5][0] = new Piece(Colour.BLACK, PieceType.BISHOP);		//Bishop
		board[6][0] = new Piece(Colour.BLACK, PieceType.KNIGHT);		//Knight
		board[7][0] = new Piece(Colour.BLACK, PieceType.ROOK);			//Rook

		//Inserting white pieces
		board[0][7] = new Piece(Colour.WHITE, PieceType.ROOK);			//Rook
		board[1][7] = new Piece(Colour.WHITE, PieceType.KNIGHT);		//Knight
		board[2][7] = new Piece(Colour.WHITE, PieceType.BISHOP);		//Bishop
		board[3][7] = new Piece(Colour.WHITE, PieceType.QUEEN);			//Queen
		board[4][7] = new Piece(Colour.WHITE, PieceType.KING);			//King
		board[5][7] = new Piece(Colour.WHITE, PieceType.BISHOP);		//Bishop
		board[6][7] = new Piece(Colour.WHITE, PieceType.KNIGHT);		//Knight
		board[7][7] = new Piece(Colour.WHITE, PieceType.ROOK);			//Rook
	}

	public Gamestate(Colour colour, String[][] textBoard) {
		this.colour = colour;
		this.board = stringToBoard(textBoard);

		//Sample array for creating custom boards quickly
		/*	
   String[][] sampleArray =	{
			{"--","--","--","--","--","--","--","--"},
			{"--","--","--","--","--","--","--","--"},
			{"--","--","--","--","--","--","--","--"},
			{"--","--","--","--","--","--","--","--"},
			{"--","--","--","--","--","--","--","--"},
			{"--","--","--","--","--","--","--","--"},
			{"--","--","--","--","--","--","--","--"},
			{"--","--","--","--","--","--","--","--"}
		};
		 */
	}
	
	//copy constructor
	public Gamestate(Gamestate originalGamestate) {
		this.colour = originalGamestate.colour;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = originalGamestate.board[i][j];
			}
		}
	}

	private Piece[][] stringToBoard(String[][] textBoard){
		Colour tempColour = Colour.NONE;
		PieceType tempType = PieceType.EMPTY;
		Piece[][] board = new Piece[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				switch(textBoard[j][i].charAt(0)) {
				case 'B': tempColour = Colour.BLACK;
				break;
				case 'W': tempColour = Colour.WHITE;
				break;
				case '-': tempColour = Colour.NONE;
				break;
				default:
					System.err.println("invalid pieceColour in class Gamestate");
				}
				switch(textBoard[j][i].charAt(1)) {
				case 'P': tempType = PieceType.PAWN;
				break;
				case 'T':  tempType = PieceType.KNIGHT;
				break;
				case 'B': tempType = PieceType.BISHOP;
				break;
				case 'R': tempType = PieceType.ROOK;
				break;
				case 'Q': tempType = PieceType.QUEEN;
				break;
				case 'K': tempType = PieceType.KING;
				break;
				case '-': tempType = PieceType.EMPTY;
				break;
				default:
					System.err.println("invalid pieceType in class Gamestate");
				}
				board[i][j] = new Piece(tempColour, tempType);
			}
		}
		return board;
	}

	public void movePiece(Move move) {
		int fromX = move.getStartPosX();
		int fromY = move.getStartPosY();
		int toX = move.getEndPosX();
		int toY = move.getEndPosY();
		
		//pawn promotion
		if(board[fromX][fromY].getType().equals(PieceType.PAWN)) {
			if((board[fromX][fromY].getColour().equals(Colour.BLACK) && toY == 7) || 
					board[fromX][fromY].getColour().equals(Colour.WHITE) && toY == 0) {

				board[toX][toY] = new Piece(board[fromX][fromY].getColour(), PieceType.QUEEN);
				board[fromX][fromY] = new Piece(Colour.NONE, PieceType.EMPTY);

			} else {
				//TODO duplicate code is a little loose. Should tighten up
				board[toX][toY] = new Piece(board[fromX][fromY]);			
				board[fromX][fromY] = new Piece(Colour.NONE, PieceType.EMPTY);
			}
		} else {
			//Standard moves
			board[toX][toY] = new Piece(board[fromX][fromY]);				
			board[fromX][fromY] = new Piece(Colour.NONE, PieceType.EMPTY);
		}
		
		changeTurnColour();
	}


	public Colour getTurnColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}
	
	private void changeTurnColour() {
		switch(colour){
		case WHITE:
				setColour(Colour.BLACK);
			break;
		case BLACK:
				setColour(Colour.WHITE);
			break;
		case NONE:
				System.out.println("No one has turn in method changeTurnColour in class Gamestate");
			break;
		default:
				System.out.println("Invalid Colour in method changeTurnColour in class Gamestate");
		}
	}

	public void printBoard() {

		System.out.println("  0  1  2  3  4  5  6  7");		//showing index for X-axis

		for(int j = 0; j < 8; j++) {

			System.out.print(j + " ");						//showing index for Y-axis

			for(int i = 0; i < 8; i++) {


				switch(board[i][j].getColour()) {
				case WHITE:	System.out.print("W");
				break;
				case BLACK: System.out.print("B");
				break;
				case NONE: System.out.print("-");
				break;
				default: System.err.print("Invalid PieceColor");
				}

				switch(board[i][j].getType()) {
				case PAWN: 	System.out.print("P ");	
				break;
				case KNIGHT:System.out.print("T ");		//Knight is named "T" so it isn't confused with "King"
				break;
				case BISHOP:System.out.print("B ");
				break;
				case ROOK: 	System.out.print("R ");
				break;
				case QUEEN: System.out.print("Q ");
				break;
				case KING: 	System.out.print("K ");
				break;
				case EMPTY: System.out.print("- ");
				break;
				default: System.err.println("Invalid PieceType");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public Piece[][] getBoard(){
		return board;
	}

	public Colour getColour() {
		return colour;
	}

	public void setBoard(Piece[][] board) {
		this.board = board;
	}

}
