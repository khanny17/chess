package pieces;

import game.Piece;
import game.Player;
import game.Menu;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PawnPiece extends Piece {

	/**
	 * boolean value to keep track of if the pawn has moved.
	 * Used to incorporate the initial double space move
	 */
	private boolean hasMoved = false;

	private BufferedImage image;

	/**
	 * Constructs a Pawn with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public PawnPiece(Player player) {
		super(player);

		

		//Set image to black or white pawn depending on the player
		if(player == Menu.blackPlayer) {
			try {                
				image = ImageIO.read(new File("src/images/pawn_black.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		} else if(player.equals(Menu.whitePlayer)) {
			try {                
				image = ImageIO.read(new File("src/images/pawn_white.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		}

		setImage(image);
	}

	public String toString() {
		String myplayer = (getPlayer() == Menu.whitePlayer) ? "White" : "Black";
		return myplayer + " Pawn";
	}

	/**
	 * Tells whether or not the pawn has moved yet
	 * @return false if the pawn has not moved yet
	 */
	public boolean didMove() {
		return hasMoved;
	}

	/**
	 * Called after the pawn moves. Changes hasMoved to true and calls
	 *  defineMoves again.
	 */
	public void moved() {
		this.hasMoved = true;
		this.defineMoves();
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
		if(getPlayer().equals(Menu.whitePlayer)) {
			if(!hasMoved) {
				moveMap.put(90, 2);
			} else {
				moveMap.put(90, 1);
			}
		} else if(getPlayer().equals(Menu.blackPlayer)) {
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
		if(getPlayer().equals(Menu.whitePlayer)) {
			captureMap.put(45,1);
			captureMap.put(135,1);
		} else if(getPlayer().equals(Menu.blackPlayer)) {
			captureMap.put(225,1);
			captureMap.put(315,1);
		}
	}
}
