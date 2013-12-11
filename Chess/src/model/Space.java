package model;


/**
 * The model class used to represent the spaces on the board
 * The space may be empty or it may have a piece on it.
 *  
 * @author Kevin Hannigan
 */
public class Space extends java.util.Observable{
	
	/**
	 * The piece which is currently on the space.
	 */
	private Piece piece;

	
	/**
	 * Initializes an empty space
	 */
	public Space() {
		this.piece = null;
	}

	/**
	 * Initializes a space with a piece image in the middle
	 */
	public Space(Piece piece) {
		this.piece = piece;
	}

	/**
	 * Returns true is there is no piece on this space
	 * @return
	 */
	public boolean isEmpty() {
		return (this.piece == null);
	}

	/**
	 * Returns the piece on the space
	 */
	public Piece getPiece() {
		return piece;
	}
	
	/**
	 * Sets the piece
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
		setChanged();
		notifyObservers();
		clearChanged();
	}
		
	/**
	 * Prints the information about the space - its' coordinate
	 *  value and its piece
	 */
	@Override
	public String toString() {
		if(piece == null) {
			return "Coordinate: " + /*getcoordinate+ */ "\nPiece: NONE";
		}
		return "Coordinate: " + /*getCoordinate()+*/ "\n" + piece.toString();
	}
	
}