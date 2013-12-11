package model;

import pieces.*;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;

/**
 * This class defines the chess board on which the game is played.
 * It keeps track of the spaces with a two dimensional array.
 * 
 * @author Kevin Hannigan
 */
public class Board {



	/**
	 * The array used to represent the board. The board is defined such that
	 *  (0,0) = H1, the top left hand corner for the player playing as white
	 *  
	 *  The board is defined (row,col) or (y,x)
	 */
	private Space[][] boardArray;

	public static final int ROWS = 8;
	public static final int COLS = 8;

	/**
	 * Constructs the default game board set up for the start of the game
	 */
	public Board() {
		//Place the pieces
		boardArray = new Space[ROWS][COLS];

		//set up black's players
		boardArray[0][0] = new Space(new RookPiece(Game.blackPlayer));
		boardArray[0][1] = new Space(new KnightPiece(Game.blackPlayer));
		boardArray[0][2] = new Space(new BishopPiece(Game.blackPlayer));
		boardArray[0][3] = new Space(new QueenPiece(Game.blackPlayer));
		boardArray[0][4] = new Space(new KingPiece(Game.blackPlayer));
		boardArray[0][5] = new Space(new BishopPiece(Game.blackPlayer));
		boardArray[0][6] = new Space(new KnightPiece(Game.blackPlayer));
		boardArray[0][7] = new Space(new RookPiece(Game.blackPlayer));
		for(int col = 0; col < COLS; col++) {
			boardArray[1][col] = new Space(new PawnPiece(Game.blackPlayer));
		}

		//set up white's players
		boardArray[7][0] = new Space(new RookPiece(Game.whitePlayer));
		boardArray[7][1] = new Space(new KnightPiece(Game.whitePlayer));
		boardArray[7][2] = new Space(new BishopPiece(Game.whitePlayer));
		boardArray[7][3] = new Space(new QueenPiece(Game.whitePlayer));
		boardArray[7][4] = new Space(new KingPiece(Game.whitePlayer));
		boardArray[7][5] = new Space(new BishopPiece(Game.whitePlayer));
		boardArray[7][6] = new Space(new KnightPiece(Game.whitePlayer));
		boardArray[7][7] = new Space(new RookPiece(Game.whitePlayer));
		for(int col = 0; col < COLS; col++) {
			boardArray[6][col] = new Space(new PawnPiece(Game.whitePlayer));
		}

		//set up empty spaces
		for(int row = 2; row < ROWS-2; row++) {
			for(int col = 0; col < COLS; col++) {
				boardArray[row][col] = new Space();
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
	public Space getSpaceAtXY(int y, int x) {
		return isOnBoard(y, x) ?
				boardArray[y][x] : null;
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
	public boolean isOnBoard(int y, int x) {
		return (x < ROWS && x >= 0 && y < COLS && y >= 0);
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
		case 0:		return getSpaceAtXY(startY,startX+radius);		

		case 30:	return getSpaceAtXY(startY-1,startX+2);

		case 45:	return getSpaceAtXY(startY-radius,startX+radius);

		case 60:	return getSpaceAtXY(startY-2,startX+1);

		case 90:	return getSpaceAtXY(startY-radius,startX);

		case 120:	return getSpaceAtXY(startY-2,startX-1);

		case 135:	return getSpaceAtXY(startY-radius,startX-radius);

		case 150:	return getSpaceAtXY(startY-1,startX-2);

		case 180:	return getSpaceAtXY(startY,startX-radius);

		case 210:	return getSpaceAtXY(startY+1,startX-2);

		case 225:	return getSpaceAtXY(startY+radius,startX-radius);

		case 240:	return getSpaceAtXY(startY+2,startX-1);

		case 270:	return getSpaceAtXY(startY+radius,startX);

		case 300:	return getSpaceAtXY(startY+2,startX+1);

		case 315:	return getSpaceAtXY(startY+radius,startX+radius);

		case 330:	return getSpaceAtXY(startY+1,startX+2);

		default: 	System.err.println("Invalid Direction!");
					return null;
		}
	}

	/**
	 * Method to determine if the given Space (to) is able to be
	 *  reached according to the moves defined in (moves)
	 * @param moves The dir/radius map defined by the piece. Could be the
	 * 				 capture map or the move map
	 * @param fromX The X coordinate we are starting from
	 * @param fromY The Y coordinate we are starting from
	 * @param to The space we are searching for
	 * @return true if the space is able to be reached according to the map
	 */
	public boolean spaceIsInMap(HashMap<Integer, Integer> moves, int fromX, int fromY, Space to) {

		//Go through each possible move and test to see if "to" is one of them
		Space possibleSpace;
		Iterator<Entry<Integer,Integer>> entries = moves.entrySet().iterator();
		while(entries.hasNext()) {
			Entry<Integer,Integer> entry = (Entry<Integer, Integer>) entries.next();
			int dir = (int) entry.getKey();
			int radius = (int) entry.getValue();

			/**
			 * We loop backwards from the maximum radius to see if the space
			 *  that was clicked is within moving range
			 */
			while(radius > 0) {

				possibleSpace = getSpaceFromMove(fromY,fromX,dir,radius);
				if(possibleSpace != null) {
					if(possibleSpace.equals(to)) {
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

				radius--;

			}
		}
		return false;
	}
}


