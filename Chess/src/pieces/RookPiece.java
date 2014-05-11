package pieces;

import model.Piece;
import model.Player;

import java.util.HashMap;

import control.Chess;

public class RookPiece extends Piece {
	
	
	/**
	 * Constructs a Rook with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public RookPiece(Player player) {
		super(player);
	}
	
	public String toString() {
		String myplayer = (getPlayer() == Chess.whitePlayer) ? "White" : "Black";
		return myplayer + " Rook";
	}
	
	/**
	 * The Rook can move any number of spaces in any direction along an axis
	 * 	(x*90)
	 */
	@Override
	protected void defineMoves() {
		this.moveMap = new HashMap<Integer,Integer>();
		moveMap.put(0,8);
		moveMap.put(90, 8);
		moveMap.put(180, 8);
		moveMap.put(270, 8);
	}

	@Override
	protected void defineCaptures() {
		this.captureMap = new HashMap<Integer,Integer>();
		captureMap.put(0,8);
		captureMap.put(90, 8);
		captureMap.put(180, 8);
		captureMap.put(270, 8);
	}

	
}
