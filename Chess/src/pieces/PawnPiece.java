package pieces;

import game.Piece;
import game.Player;
import game.Menu;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class PawnPiece extends Piece {
	
	private BufferedImage image;
	
	/**
	 * Constructs a Pawn with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public PawnPiece(Player player) {
		super(player);
		
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
		return "Pawn";
	}
}
