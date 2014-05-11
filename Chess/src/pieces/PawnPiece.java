package pieces;

import model.Piece;
import model.Player;

import java.util.HashMap;

import view.GameFrame;

public class PawnPiece extends Piece {

	/**
	 * Constructs a Pawn with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public PawnPiece(Player player) {
		super(player);
	}

	public String toString() {
		String myplayer = (getPlayer() == GameFrame.getInstance().whitePlayer) ? "White" : "Black";
		return myplayer + " Pawn";
	}

	/**
	 * The pawn can only move 45, 90, and 135 degrees if it is white or
	 *  225, 270, and 315 degrees if it is black. It has radius 2 for 90/270
	 *  degrees if it has not moved yet, and 1 for any other case.
	 *  
	 *  Does not include diagonal capture move.
	 */
	@Override
	protected void defineMoves() {
		this.moveMap = new HashMap<Integer,Integer>();
		if(getPlayer().equals(GameFrame.getInstance().whitePlayer)) {
			if(!hasMoved) {
				moveMap.put(90, 2);
			} else {
				moveMap.put(90, 1);
			}
		} else if(getPlayer().equals(GameFrame.getInstance().blackPlayer)) {
			if(!hasMoved) {
				moveMap.put(270, 2);
			} else {
				moveMap.put(270, 1);
			}

		}
	}

	/**
	 * The pawn can capture diagonally in either direction forward, radius of one
	 * (45,1) & (135,1)
	 */
	@Override
	protected void defineCaptures() {
		this.captureMap = new HashMap<Integer,Integer>();
		if(getPlayer().equals(GameFrame.getInstance().whitePlayer)) {
			captureMap.put(45,1);
			captureMap.put(135,1);
		} else if(getPlayer().equals(GameFrame.getInstance().blackPlayer)) {
			captureMap.put(225,1);
			captureMap.put(315,1);
		}
	}
}
