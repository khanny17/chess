package pieces;

import game.Piece;
import game.Player;
import game.Menu;
import game.Space;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
		
		if(player == Menu.blackPlayer) {
			try {                
				image = ImageIO.read(new File("src/images/king_black.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		} else if(player.equals(Menu.whitePlayer)) {
			try {                
				image = ImageIO.read(new File("src/images/king_white.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		}
		
		setImage(image);
	}
	
	public boolean didMove() {
		return hasMoved;
	}
	
	public String toString() {
		return "King";
	}

	@Override
	public ArrayList<Space> getMoves() {
		// TODO Auto-generated method stub
		return null;
	}
}
