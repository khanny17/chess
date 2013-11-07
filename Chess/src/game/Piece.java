package game;
import java.awt.image.BufferedImage;
import java.util.TreeMap;

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
	 * This tree map keeps key value pairs for the angle and distance at which
	 *  the piece may travel
	 *  
	 * Key = distance; Value = radius
	 */
	protected TreeMap<Integer,Integer> moveMap;
	
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
	 * Returns legal moves for the piece
	 * Describes HOW the piece moves, doesn't check the actual spaces
	 * @return A list of legal moves.
	 */
	public TreeMap<Integer,Integer> getMoves() {
		return moveMap;
	}
	
	/**
	 * Sets up "moveMap"
	 * Called by constructor.
	 * 
	 * The keys in the map are the angles while the values are the radius
	 *  along that axis at which the piece can travel
	 * The angles are defined as in the polar coordinate system
	 * For example, a bishop can move any number of spaces (radius = 8) at
	 *  45, 135, 225, and 315 degrees
	 */
	protected abstract void defineMoves();
	
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
