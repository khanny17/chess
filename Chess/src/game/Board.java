package game;

import pieces.*;

import java.awt.GridLayout;
import java.awt.Color;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Iterator;

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
	 *  
	 *  The board is defined (row,col) or (y,x)
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

	/**
	 * Returns the space located at the passed x and y
	 * @param x the x value
	 * @param y the y value
	 * @return the space object
	 */
	public Space getSpaceAtXY(int x, int y) {
		return isOnBoard(x, y) ?
				boardArray[x][y] : null;
	}

	/**
	 * Returns the XY values of the passed space
	 * @param space the space to locate
	 * @return the XY values of the space
	 */
	public int[] getXYofSpace(Space space) {
		for(int y = 0; y < ROWS; y++) {
			for(int x = 0; x < COLS; x++) {
				if(boardArray[y][x] == space) {
					return new int[] {x,y}; 
				}
			}
		}
		//Should not be reached
		return null;
	}

	/**
	 * Tests if the x y coordinates signify a space which is on the board
	 * @return true if the x/y pair is on the board
	 */
	public boolean isOnBoard(int x, int y) {
		return (x < ROWS && x >= 0 && y < COLS && y >= 0);
	}

	/**
	 * Tests if it is legal for the piece on "from" to move to "to"
	 * @param from the space where the piece is moving from
	 * @param to the space the piece is moving to
	 * @return true if it is a legal move
	 */
	public boolean isLegalMove(Space from, Space to) {
		Piece movingPiece = from.getPiece();
		System.out.println("moving piece: " + movingPiece);
		Piece otherPiece = to.getPiece();
		System.out.println("other piece: " + otherPiece);
		if(otherPiece != null) {
			if(movingPiece.getPlayer().equals(otherPiece.getPlayer())) {
				return false;
			}
		}

		int[] xy = getXYofSpace(from);
		int fromX = xy[0];
		int fromY = xy[1];

		//get the defined possible moves for the from piece
		TreeMap<Integer,Integer> moves = movingPiece.getMoves();

		//Go through each possible move and test to see if "to" is one of them
		Space possibleSpace;
		Iterator<Entry<Integer,Integer>> entries = moves.entrySet().iterator();
		while(entries.hasNext()) {
			Entry<Integer,Integer> entry = (Entry<Integer, Integer>) entries.next();
			int dir = (int) entry.getKey();
			int radius = (int) entry.getValue();
			System.out.println("dir: " + dir + "rad: " + radius);
			possibleSpace = getSpaceFromMove(fromY,fromX,dir,radius);
			if(possibleSpace != null) {
				System.out.println("the space is not null");
				System.out.println(this.getXYofSpace(possibleSpace)[0]);
				if(possibleSpace.equals(to)) {
					System.out.println("the space is a possible move");
					/*
					 * "to" is a possible space, but we have to check if it is
					 *  blocked along the way
					 */
					radius--;
					while(radius > 0) {
						possibleSpace = getSpaceFromMove(fromY,fromX,dir,radius);
						if(possibleSpace.getPiece() != null) {
							return false;
						}

						radius--;
					}
					//thus, if it is not blocked and the move is legal,
					return true;
				}
			}
		}


		//If "to" wasn't in the possible moves, check the special cases.

		//Test the Pawn's diagonal captures
		if(movingPiece instanceof PawnPiece) {
			return testPawnCapture(from,to,fromX,fromY);
		}
		//Test castling
		return false;

	}

	/**
	 * Tests for a legal capturing move. Calls "isLegalMove," but then
	 *  returns the piece it is capturing instead of a boolean value
	 * @param from
	 * @param to
	 * @return the captured piece (or null if not a valid move)
	 */
	public Piece isLegalCapture(Space from, Space to) {
		if(isLegalMove(from,to)) {
			return to.getPiece();
		} else {
			return null;
		}
	}

	/**
	 * Private method called by the isLegalMove method used to check for 
	 *  the pawn's capturing move
	 * @param from the space the piece is moving from
	 * @param to the space the piece is moving to
	 * @param fromX the x value of the space
	 * @param fromY the y value of the space
	 * @return true if the move from "from" to "to" is a legal capturing move
	 */
	private boolean testPawnCapture(Space from, Space to,int fromX, int fromY) {
		Piece movingPiece = from.getPiece();
		Piece otherPiece = to.getPiece();
		Space posSpace;

		if(movingPiece.getPlayer() == Menu.whitePlayer) {
			posSpace = getSpaceFromMove(fromX,fromY,45,1);
			if(posSpace != null) {
				if(posSpace.equals(to)) {
					if(otherPiece != null) {
						if(otherPiece.getPlayer() == Menu.blackPlayer){
							return true;
						}
					}
				}
			}

			posSpace = getSpaceFromMove(fromX,fromY,135,1);
			if(posSpace != null) {
				if(posSpace.equals(to)) {
					if(otherPiece != null) {
						if(otherPiece.getPlayer() == Menu.blackPlayer){
							return true;
						}
					}
				}
			}
		} else if(movingPiece.getPlayer() == Menu.blackPlayer) {
			posSpace = getSpaceFromMove(fromX,fromY,225,1);
			if(posSpace != null) {
				if(posSpace.equals(to)) {
					if(otherPiece != null) {
						if(otherPiece.getPlayer() == Menu.whitePlayer){
							return true;
						}
					}
				}
			}

			posSpace = getSpaceFromMove(fromX,fromY,315,1);
			if(posSpace != null) {
				if(posSpace.equals(to)) {
					if(otherPiece != null) {
						if(otherPiece.getPlayer() == Menu.whitePlayer){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns the space based on the direction and radius
	 * @param startY The Y coordinate of the space moving from
	 * @param startX The X coordinate of the space moving from
	 * @param dir The direction the piece wants to move
	 * @param radius how far the piece wants to move
	 * @return the space at that point
	 */
	public Space getSpaceFromMove(int startY, int startX,int dir, int radius) {
		switch(dir) {
		case 0:		return boardArray[startY][startX+radius];

		case 30:	return boardArray[startY-1][startX+2];

		case 45:	return boardArray[startY-radius][startX+radius];

		case 60:	return boardArray[startY-2][startX+1];

		case 90:	return boardArray[startY-radius][startX];

		case 120:	return boardArray[startY+2][startX];

		case 135:	return boardArray[startY-radius][startX-radius];

		case 150:	return boardArray[startY-1][startX-2];

		case 180:	return boardArray[startY][startX-radius];

		case 210:	return boardArray[startY+1][startX-2];

		case 225:	return boardArray[startY+radius][startX-radius];

		case 240:	return boardArray[startY+2][startX-1];

		case 270:	return boardArray[startY+radius][startX];

		case 300:	return boardArray[startY+2][startX+1];

		case 315:	return boardArray[startY+radius][startX+radius];

		case 330:	return boardArray[startY+1][startX+2];

		default: 	System.err.println("Invalid Direction!");
		return null;
		}
	}
}


