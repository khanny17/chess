package pieces;

import model.Chess;
import model.Piece;
import model.Player;

import java.util.HashMap;

public class QueenPiece extends Piece {
	
	/**
	 * Constructs a Queen with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public QueenPiece(Player player) {
		super(player);
	}
	
	public String toString() {
		String myplayer = (getPlayer() == Chess.whitePlayer) ? "White" : "Black";
		return myplayer + " Queen";
	}

	/**
	 * The Queen can move in any direction (x*45) for any number of spaces
	 */
	@Override
	protected void defineMoves() {
		moveMap = new HashMap<Integer,Integer>();
		for(int dir = 0; dir < 360; dir += 45) {
			moveMap.put(dir, 8);
		}
	}

	@Override
	protected void defineCaptures() {
		captureMap = new HashMap<Integer,Integer>();
		for(int dir = 0; dir < 360; dir += 45) {
			captureMap.put(dir, 8);
		}
	}	
}
