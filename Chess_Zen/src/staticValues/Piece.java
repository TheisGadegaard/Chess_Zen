package staticValues;
public class Piece {

	Colour colour;
	PieceType Type;
	boolean specialMoveUsed;
	
	public Piece(Colour colour, PieceType type) {
		this.colour = colour;
		this.Type = type;
		this.specialMoveUsed = false;
	}
	
	public Piece(Piece originalPiece) {
		this.colour = originalPiece.colour;
		this.Type = originalPiece.Type;
		this.specialMoveUsed = originalPiece.specialMoveUsed;
	}

	public PieceType getType() {
		return Type;
	}

	public void setType(PieceType type) {
		Type = type;
	}

	public boolean isSpecialMoveUsed() {
		return specialMoveUsed;
	}

	public void setSpecialMoveUsed(boolean specialMoveUsed) {
		this.specialMoveUsed = specialMoveUsed;
	}

	public Colour getColour() {
		return colour;
	}
}
