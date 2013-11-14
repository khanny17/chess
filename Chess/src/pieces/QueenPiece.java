package pieces;

import game.Piece;
import game.Player;
import game.Menu;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class QueenPiece extends Piece {
	
	private BufferedImage image;
	
	/**
	 * Constructs a Queen with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public QueenPiece(Player player) {
		super(player);
		
		if(player == Menu.blackPlayer) {
			try {                
				image = ImageIO.read(new File("src/images/queen_black.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		} else if(player.equals(Menu.whitePlayer)) {
			try {                
				image = ImageIO.read(new File("src/images/queen_white.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		}
		
		setImage(image);
	}
	
	public String toString() {
		String myplayer = (getPlayer() == Menu.whitePlayer) ? "White" : "Black";
		return myplayer + " Queen";
	}

	/**
	 * The Queen can move in any direction (x*45) for any number of spaces
	 */
	@Override
	protected void defineMoves() {
		moveMap = new HashMap<Integer,Integer>();
		for(int dir = 0; dir < 360; dir += 45) {
			moveMap.put(dir, 8);
		}
	}

	@Override
	protected void defineCaptures() {
		// TODO Auto-generated method stub
		
	}	
}
