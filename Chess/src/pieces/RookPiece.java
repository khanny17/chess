package pieces;

import game.Piece;
import game.Player;
import game.Menu;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class RookPiece extends Piece {
	
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
		return "Rook";
	}
}
