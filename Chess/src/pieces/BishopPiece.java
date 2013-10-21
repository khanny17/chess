package pieces;

import game.Piece;
import game.Player;
import game.Menu;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class BishopPiece extends Piece {
	
	private BufferedImage image;
	
	/**
	 * Constructs a Bishop with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public BishopPiece(Player player) {
		super(player);
		
		if(player == Menu.blackPlayer) {
			try {                
				image = ImageIO.read(new File("src/images/king_black.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		} else if(player.equals(Menu.whitePlayer)) {
			try {                
				image = ImageIO.read(new File("src/images/king_black.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		}
		
		setImage(image);
	}
	
}
