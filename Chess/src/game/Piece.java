package game;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * The abstract class used to define each of the chess pieces
 * Each piece inherits from this class
 * 
 * @author Kevin Hannigan
 */
public abstract class Piece {

	private Player player;
	private BufferedImage image;
	
	/**
	 * Constructs a piece with the passed player
	 * 
	 * @param player the player of the piece
	 */
	public Piece(Player player) {
		this.player = player;
	}
	
	/**
	 * Returns the BufferedImage as an ImageIcon
	 * @return the new ImageIcon
	 */
	public ImageIcon getImageIcon() {
		try {
			return new ImageIcon(image);
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Returns all legal moves for that piece, regardless of whether or not
	 *  that move is blocked or on the board
	 * @return A list of legal moves.
	 */
	public abstract ArrayList<Space> getMoves();
	
	
	
	
	public void setPlayer(Player p) {
		player = p;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public String toString() {
		return "Piece";
	}
}
