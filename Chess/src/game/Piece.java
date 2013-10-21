package game;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.io.IOException;
/**
 * The class used to define each of the chess pieces
 * 
 * @author Kevin Hannigan
 */
public abstract class Piece {

	private Player player;
	private BufferedImage image;
	
	public Piece(Player player) {
		this.player = player;
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
	
	public ImageIcon getImageIcon() {
		try {
			return new ImageIcon(image);
		} catch(Exception e) {
			return null;
		}
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
