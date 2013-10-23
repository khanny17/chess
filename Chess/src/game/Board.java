package game;

import pieces.*;
import java.awt.GridLayout;
import java.awt.Color;

/**
 * This class defines the chess board on which the game is played.
 * It keeps track of the spaces with a two dimensional array.
 * The class works as the GridLayout for the GUI too.
 * 
 * @author Kevin Hannigan
 */
@SuppressWarnings("serial")
public class Board extends GridLayout {
	
	/**
	 * The listener used to control space selection throughout the game
	 */
	public static final SpaceClickListener selector = new SpaceClickListener();
	
	/**
	 * The array used to represent the board. The board is defined such that
	 *  (0,0) = H1, the top left hand corner for the player playing as white
	 */
	private Space[][] boardArray;
	
	protected static final byte ROWS = 8;
	protected static final byte COLS = 8;

	/**
	 * Constructs the default game board set up for the start of the game
	 */
	public Board() {
		//set up the GridLayout
		super(8,8,0,0);
		
		//Place the pieces
		boardArray = new Space[ROWS][COLS];
		
		//set up black's players
		boardArray[0][0] = new Space(new RookPiece(Menu.blackPlayer));
		boardArray[0][1] = new Space(new KnightPiece(Menu.blackPlayer));
		boardArray[0][2] = new Space(new BishopPiece(Menu.blackPlayer));
		boardArray[0][3] = new Space(new QueenPiece(Menu.blackPlayer));
		boardArray[0][4] = new Space(new KingPiece(Menu.blackPlayer));
		boardArray[0][5] = new Space(new BishopPiece(Menu.blackPlayer));
		boardArray[0][6] = new Space(new KnightPiece(Menu.blackPlayer));
		boardArray[0][7] = new Space(new RookPiece(Menu.blackPlayer));
		for(int col = 0; col < COLS; col++) {
			boardArray[1][col] = new Space(new PawnPiece(Menu.blackPlayer));
		}
		
		//set up white's players
		boardArray[7][0] = new Space(new RookPiece(Menu.whitePlayer));
		boardArray[7][1] = new Space(new KnightPiece(Menu.whitePlayer));
		boardArray[7][2] = new Space(new BishopPiece(Menu.whitePlayer));
		boardArray[7][3] = new Space(new QueenPiece(Menu.whitePlayer));
		boardArray[7][4] = new Space(new KingPiece(Menu.whitePlayer));
		boardArray[7][5] = new Space(new BishopPiece(Menu.whitePlayer));
		boardArray[7][6] = new Space(new KnightPiece(Menu.whitePlayer));
		boardArray[7][7] = new Space(new RookPiece(Menu.whitePlayer));
		for(int col = 0; col < COLS; col++) {
			boardArray[6][col] = new Space(new PawnPiece(Menu.whitePlayer));
		}
		
		//set up empty spaces
		for(int row = 2; row < ROWS-2; row++) {
			for(int col = 0; col < COLS; col++) {
				boardArray[row][col] = new Space();
			}
		}
		
		//color the panels black/white
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLS; col++) {
				if(row%2 == 0) {
					if(col%2 == 1) {
						boardArray[row][col].setBackground(Color.GRAY);
					} else {
						boardArray[row][col].setBackground(Color.WHITE);
					}
				} else {
					if(col%2 == 0) {
						boardArray[row][col].setBackground(Color.GRAY);
					} else {
						boardArray[row][col].setBackground(Color.WHITE);
					}
				}
			}
		}
	}
	
	/**
	 * Turns an X/Y pair into a Coordinate
	 * @param x the x value
	 * @param y the y value
	 * @return the Coordinate which matches the x/y pair
	 */
	public Coordinate toCoor(int x, int y) {
		for(Coordinate c: Coordinate.values()) {
			if(c.x == x && c.y == y) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * returns the X/Y coordinates of a Coordinate in an integer array
	 * @param c the coordinate
	 * @return an int[] with the x and y values
	 */
	public int[] toXY(Coordinate c) {
		return new int[] {c.x,c.y};
	}
	
	//Returns the wrapped 2D Space array
	public Space[][] getArray() {
		return boardArray;
	}
	
}


