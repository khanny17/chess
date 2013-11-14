package pieces;

import game.Piece;
import game.Player;
import game.Menu;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RookPiece extends Piece {
	
	/**
	 * boolean value to keep track of if the rook has moved.
	 * Used to incorporate castling
	 */
	private boolean hasMoved;
	
	private BufferedImage image;
	
	/**
	 * Constructs a Rook with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public RookPiece(Player player) {
		super(player);
		
		if(player == Menu.blackPlayer) {
			try {                
				image = ImageIO.read(new File("src/images/rook_black.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		} else if(player.equals(Menu.whitePlayer)) {
			try {                
				image = ImageIO.read(new File("src/images/rook_white.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		}
		
		setImage(image);
	}
	
	public String toString() {
		String myplayer = (getPlayer() == Menu.whitePlayer) ? "White" : "Black";
		return myplayer + " Rook";
	}
	
	/**
	 * returns value of hasMoved
	 * @return true of the piece has moved
	 */
	public boolean didMove() {
		return hasMoved;
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
		// TODO Auto-generated method stub
		
	}

	
}
