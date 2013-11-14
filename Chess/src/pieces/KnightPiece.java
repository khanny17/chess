package pieces;

import game.Piece;
import game.Player;
import game.Menu;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class KnightPiece extends Piece {
	
	private BufferedImage image;
	
	/**
	 * Constructs a Knight with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public KnightPiece(Player player) {
		super(player);
		
		if(player == Menu.blackPlayer) {
			try {                
				image = ImageIO.read(new File("src/images/knight_black.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		} else if(player.equals(Menu.whitePlayer)) {
			try {                
				image = ImageIO.read(new File("src/images/knight_white.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		}
		
		setImage(image);
	}
	
	public String toString() {
		String myplayer = (getPlayer() == Menu.whitePlayer) ? "White" : "Black";
		return myplayer + " Knight";
	}

	/**
	 * The Knight can move two spaces in one direction, then one space
	 *  perpendicular (or one space in one direction, then two spaces
	 *  perpendicular)
	 * We signify this with angles (x*30) and radius one
	 */
	@Override
	protected void defineMoves() {
		this.moveMap = new HashMap<Integer,Integer>();
		for(int dir = 0; dir < 360; dir += 30) {
			if(dir%90 != 0) {
				moveMap.put(dir, 1);
			}
		}
	}

	@Override
	protected void defineCaptures() {
		// TODO Auto-generated method stub
		
	}
	
	
}
