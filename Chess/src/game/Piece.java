package game;
import java.awt.image.BufferedImage;
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
