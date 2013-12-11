package model;

import java.util.HashMap;

/**
 * The abstract class used to define each of the chess pieces
 * Each piece inherits from this class
 * 
 * @author Kevin Hannigan
 */
public abstract class Piece {

	private Player player;
	
	/**
	 * false until the player moves the piece
	 * Used for castling and such
	 */
	protected boolean hasMoved;

	/**
	 * This hash map keeps key value pairs for the angle and distance at which
	 *  the piece may travel
	 *  
	 * Key = degree; Value = radius
	 */
	protected HashMap<Integer,Integer> moveMap;

	/**
	 * This hash map keeps key value pairs for the angle and distance at which
	 *  the piece may travel to capture
	 *  
	 * Key = degree; Value = radius
	 */
	protected HashMap<Integer,Integer> captureMap;

	/**
	 * Constructs a piece with the passed player
	 * 
	 * @param player the player of the piece
	 */
	public Piece(Player player) {
		this.player = player;
		this.hasMoved = false;
		//Set up the hash map to define how the pawn moves/captures
		this.defineMoves();
		this.defineCaptures();
	}

	public boolean didMove() {
		return hasMoved;
	}
	
	public void moved() {
		hasMoved = true;
	}
	
	/**
	 * Returns legal moves for the piece
	 * Describes HOW the piece moves, doesn't check the actual spaces
	 * @return A list of legal moves.
	 */
	public HashMap<Integer,Integer> getMoves() {
		return moveMap;
	}

	/**
	 * Returns legal captures for the piece
	 * Describes HOW the piece moves, doesn't check the actual spaces
	 * @return A list of legal moves.
	 */
	public HashMap<Integer,Integer> getCaptures() {
		return captureMap;
	}

	/**
	 * Sets up "moveMap"
	 * Called by constructor.
	 * 
	 * The keys in the map are the angles while the values are the radius
	 *  along that axis at which the piece can travel
	 * The angles are defined as in the polar coordinate system
	 * For example, a bishop can move any number of spaces (radius = 8) at
	 *  45, 135, 225, and 315 degrees
	 */
	protected abstract void defineMoves();

	/**
	 * Sets up "captureMap"
	 * Called by constructor.
	 * 
	 * The keys in the map are the angles while the values are the radius
	 *  along that axis at which the piece can travel
	 * The angles are defined as in the polar coordinate system
	 * For example, a bishop can move any number of spaces (radius = 8) at
	 *  45, 135, 225, and 315 degrees
	 */
	protected abstract void defineCaptures();

	public Player getPlayer() {
		return player;
	}

	public String toString() {
		return "Piece";
	}
}
