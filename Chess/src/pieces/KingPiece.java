package pieces;

import game.Piece;
import game.Player;
import game.Game;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class KingPiece extends Piece {
	/**
	 * boolean value to keep track of if the king has moved.
	 * Used to incorporate castling
	 */
	private boolean hasMoved = false;
	
	private BufferedImage image;
	
	/**
	 * Constructs a King with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public KingPiece(Player player) {
		super(player);
		
		if(player == Game.blackPlayer) {
			try {                
				image = ImageIO.read(new File("src/images/king_black.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		} else if(player.equals(Game.whitePlayer)) {
			try {                
				image = ImageIO.read(new File("src/images/king_white.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		}
		
		setImage(image);
	}
	
	/**
	 * returns value of hasMoved
	 * @return true of the piece has moved
	 */
	public boolean didMove() {
		return hasMoved;
	}
	
	public void moved() {
		hasMoved = true;
	}
	
	public String toString() {
		String myplayer = (getPlayer() == Game.whitePlayer) ? "White" : "Black";
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
