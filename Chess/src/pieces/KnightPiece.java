package pieces;

import model.Piece;
import model.Player;

import java.util.HashMap;

import control.Chess;

public class KnightPiece extends Piece {
	
	/**
	 * Constructs a Knight with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public KnightPiece(Player player) {
		super(player);
	}
	
	public String toString() {
		String myplayer = (getPlayer() == Chess.whitePlayer) ? "White" : "Black";
		return myplayer + " Knight";
	}

	/**
	 * The Knight can move two spaces in one direction, then one space
	 *  perpendicular (or one space in one direction, then two spaces
	 *  perpendicular)
	 * We signify this with angles (x*30) and radius one
	 */
	@Override
	protected void defineMoves() {
		this.moveMap = new HashMap<Integer,Integer>();
		for(int dir = 0; dir < 360; dir += 30) {
			if(dir%90 != 0) {
				moveMap.put(dir, 1);
			}
		}
	}

	@Override
	protected void defineCaptures() {
		this.captureMap = new HashMap<Integer,Integer>();
		for(int dir = 0; dir < 360; dir += 30) {
			if(dir%90 != 0) {
				captureMap.put(dir, 1);
			}
		}
	}
	
	
}
