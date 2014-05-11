package images;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Singleton which loads the images of the pieces for quick access
 * @author Kevin Hannigan
 */
public class Images {
	//the single instantiation of the class
	private static Images images = null;
	
	public  final  ImageIcon BlackBishop;
	public  final  ImageIcon BlackQueen;
	public  final  ImageIcon BlackRook;
	public  final  ImageIcon BlackPawn;
	public  final  ImageIcon BlackKnight;
	public  final  ImageIcon BlackKing;
	
	public  final  ImageIcon WhiteBishop;
	public  final  ImageIcon WhiteQueen;
	public  final  ImageIcon WhiteRook;
	public  final  ImageIcon WhitePawn;
	public  final  ImageIcon WhiteKnight;
	public  final  ImageIcon WhiteKing;
	
	private Images() throws java.io.IOException {
		              
			BlackQueen = new ImageIcon(ImageIO.read(new File("src/images/queen_black.png")));
			BlackBishop = new ImageIcon(ImageIO.read(new File("src/images/bishop_black.png")));
			BlackRook = new ImageIcon(ImageIO.read(new File("src/images/rook_black.png")));
			BlackPawn = new ImageIcon(ImageIO.read(new File("src/images/pawn_black.png")));
			BlackKnight = new ImageIcon(ImageIO.read(new File("src/images/knight_black.png")));
			BlackKing = new ImageIcon(ImageIO.read(new File("src/images/king_black.png")));

			WhiteQueen = new ImageIcon(ImageIO.read(new File("src/images/queen_white.png")));
			WhiteBishop = new ImageIcon(ImageIO.read(new File("src/images/bishop_white.png")));
			WhiteRook = new ImageIcon(ImageIO.read(new File("src/images/rook_white.png")));
			WhitePawn = new ImageIcon(ImageIO.read(new File("src/images/pawn_white.png")));
			WhiteKnight = new ImageIcon(ImageIO.read(new File("src/images/knight_white.png")));
			WhiteKing = new ImageIcon(ImageIO.read(new File("src/images/king_white.png")));
		
	}
	
	public static Images getInstance() {
		if(images == null) {
			try {
				images = new images.Images();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
		return images;
	}
	
}
