package pieces;

import model.Piece;
import model.Player;

import java.util.HashMap;

import control.Chess;

public class BishopPiece extends Piece {

	/**
	 * Constructs a Bishop with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public BishopPiece(Player player) {
		super(player);
	}
	
	public String toString() {
		String myplayer = (getPlayer() == Chess.whitePlayer) ? "White" : "Black";
		return myplayer + " Bishop";
	}
	
	/**
	 * The bishop can move diagonally in any direction as far as desired
	 */
	@Override
	protected void defineMoves() {
		this.moveMap = new HashMap<Integer,Integer>();
		moveMap.put(45, 8);
		moveMap.put(135, 8);
		moveMap.put(225, 8);
		moveMap.put(315, 8);
	}

	@Override
	protected void defineCaptures() {
		this.captureMap = new HashMap<Integer,Integer>();
		captureMap.put(45, 8);
		captureMap.put(135, 8);
		captureMap.put(225, 8);
		captureMap.put(315, 8);
	}

	
}
