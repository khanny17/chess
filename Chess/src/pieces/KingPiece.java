package pieces;

import model.Piece;
import model.Player;

import java.util.HashMap;

import view.GameFrame;

public class KingPiece extends Piece {

	/**
	 * Constructs a King with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public KingPiece(Player player) {
		super(player);
	}
	
	public String toString() {
		String myplayer = (getPlayer() == GameFrame.getInstance().whitePlayer) ? "White" : "Black";
		return myplayer + " King";
	}
	
	/**
	 * The king can move one space in any direction (x*45)
	 * It can also castle if it has not moved yet and the rook in question
	 *  has not moved yet. This must be handled further upstream
	 */
	@Override
	protected void defineMoves() {
		this.moveMap = new HashMap<Integer,Integer>();
		for(int dir = 0; dir < 360; dir+=45) {
			moveMap.put(dir, 1);
		}
	}

	@Override
	protected void defineCaptures() {
		this.captureMap = new HashMap<Integer,Integer>();
		for(int dir = 0; dir < 360; dir+=45) {
			captureMap.put(dir, 1);
		}
	}

}
