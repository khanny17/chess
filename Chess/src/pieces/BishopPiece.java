package pieces;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import model.Game;
import model.Piece;
import model.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class BishopPiece extends Piece {
	
	private BufferedImage image;
	
	/**
	 * Constructs a Bishop with the passed player and sets the image
	 * 
	 * @param player the player of the piece
	 */
	public BishopPiece(Player player) {
		super(player);
		
		if(player == Game.blackPlayer) {
			try {                
				image = ImageIO.read(new File("src/images/bishop_black.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		} else if(player.equals(Game.whitePlayer)) {
			try {                
				image = ImageIO.read(new File("src/images/bishop_white.png"));
			} catch (IOException ex) {
				System.out.println("File Not Found!");
			}
		}
		
		setImage(image);
	}
	
	public String toString() {
		String myplayer = (getPlayer() == Game.whitePlayer) ? "White" : "Black";
		return myplayer + " Bishop";
	}
	
	/**
	 * The bishop can move diagonally in any direction as far as desired
	 */
	@Override
	protected void defineMoves() {
		this.moveMap = new HashMap<Integer,Integer>();
		moveMap.put(45, 8);
		moveMap.put(135, 8);
		moveMap.put(225, 8);
		moveMap.put(315, 8);
	}

	@Override
	protected void defineCaptures() {
		this.captureMap = new HashMap<Integer,Integer>();
		captureMap.put(45, 8);
		captureMap.put(135, 8);
		captureMap.put(225, 8);
		captureMap.put(315, 8);
	}

	
}
