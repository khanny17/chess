package model;

import pieces.*;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;

import control.Chess;

/**
 * This class defines the chess board on which the game is played.
 * It keeps track of the spaces with a two dimensional array.
 * 
 * @author Kevin Hannigan
 */
public class Board {

	/**
	 * A list of each piece that has been captured so far
	 */
	private java.util.ArrayList<Piece> capturedPieces;

	/**
	 * A list of each piece on the board
	 */
	private java.util.ArrayList<Piece> activePieces;
	
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
		capturedPieces = new java.util.ArrayList<Piece>();
		activePieces = new java.util.ArrayList<Piece>();
		
		//Place the pieces
		boardArray = new Space[ROWS][COLS];

		//set up black's players
		boardArray[0][0] = new Space(new RookPiece(Chess.blackPlayer));
		boardArray[0][1] = new Space(new KnightPiece(Chess.blackPlayer));
		boardArray[0][2] = new Space(new BishopPiece(Chess.blackPlayer));
		boardArray[0][3] = new Space(new QueenPiece(Chess.blackPlayer));
		boardArray[0][4] = new Space(new KingPiece(Chess.blackPlayer));
		boardArray[0][5] = new Space(new BishopPiece(Chess.blackPlayer));
		boardArray[0][6] = new Space(new KnightPiece(Chess.blackPlayer));
		boardArray[0][7] = new Space(new RookPiece(Chess.blackPlayer));
		for(int col = 0; col < COLS; col++) {
			boardArray[1][col] = new Space(new PawnPiece(Chess.blackPlayer));
		}

		//set up white's players
		boardArray[7][0] = new Space(new RookPiece(Chess.whitePlayer));
		boardArray[7][1] = new Space(new KnightPiece(Chess.whitePlayer));
		boardArray[7][2] = new Space(new BishopPiece(Chess.whitePlayer));
		boardArray[7][3] = new Space(new QueenPiece(Chess.whitePlayer));
		boardArray[7][4] = new Space(new KingPiece(Chess.whitePlayer));
		boardArray[7][5] = new Space(new BishopPiece(Chess.whitePlayer));
		boardArray[7][6] = new Space(new KnightPiece(Chess.whitePlayer));
		boardArray[7][7] = new Space(new RookPiece(Chess.whitePlayer));
		for(int col = 0; col < COLS; col++) {
			boardArray[6][col] = new Space(new PawnPiece(Chess.whitePlayer));
		}

		//set up empty spaces
		for(int row = 2; row < ROWS-2; row++) {
			for(int col = 0; col < COLS; col++) {
				boardArray[row][col] = new Space();
			}
		}

		//add pieces to active pieces
		for(Space[] array: boardArray) {
			for(Space s: array) {
				if(!s.isEmpty()) {
					activePieces.add(s.getPiece());
				}
			}
		}
	}

	//Returns the wrapped 2D Space array
	public Space[][] getBoardArray() {
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
	 * Tries to move a piece from "from" to "to"
	 * 
	 * @param from - The space of the piece moving
	 * @param to - The space the piece wants to move to
	 */
	public boolean move(Space from, Space to) {
		Piece fromPiece = from.getPiece();


		//If the space clicked on is empty...
		if(to.isEmpty()) {

			//Test for legal move
			if(isLegalMove(from, to)) {
				//test if puts self in check
				if(!this.movePutsSelfInCheck(from, to)) {
					//move "from" piece to "to" space
					from.setPiece(null);
					to.setPiece(fromPiece);
					fromPiece.moved();
					//flip player's turns
					Chess.curPlayer = !Chess.curPlayer;
					Chess.infoUpdater.updateTurn();
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} else { 
			//If the space clicked on is occupied...


			//Test for legal capture
			Piece captured = isLegalCapture(from, to);
			if(captured != null) {
				//test if puts self in check
				if(!this.movePutsSelfInCheck(from, to)) {
					//add captured piece to array
					this.capturedPieces.add(captured);
					Chess.infoUpdater.updateCaptured();
					//move "from" piece to "to" space
					from.setPiece(null);
					to.setPiece(fromPiece);
					fromPiece.moved();
					//flip player's turns
					Chess.curPlayer = !Chess.curPlayer;
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		}
	}

	/**
	 * Tests if it is legal for the piece on "from" to capture the piece on "to"
	 * @param from the space where the piece is moving from
	 * @param to the space the piece is moving to
	 * @return null if it is not a legal capture, otherwise the captured piece
	 */
	public Piece isLegalCapture(Space from, Space to) {
		//If its the same player just return null. Otherwise
		if(to.getPiece().getPlayer().equals(from.getPiece().getPlayer())) {
			return null;
		} else {
			int[] xy = Chess.board.getXYofSpace(from);
			int fromX = xy[0];
			int fromY = xy[1];

			//get the defined possible moves for the from piece
			HashMap<Integer,Integer> moves = from.getPiece().getCaptures();

			if(Chess.board.spaceIsInMap(moves, fromX, fromY, to)) {
				return to.getPiece();
			} else {
				return null;
			}
		}
	}

	/**
	 * Tests if it is legal for the piece on "from" to move to "to"
	 * @param from the space where the piece is moving from
	 * @param to the space the piece is moving to
	 * @return the piece it will capture, null if it is illegal
	 */
	public boolean isLegalMove(Space from, Space to) {
		Piece movingPiece = from.getPiece();
		Piece otherPiece = to.getPiece();

		//If the other space isn't empty, its a CAPTURE not a MOVE so its false
		if(otherPiece != null) {
			return false;
		}

		int[] xy = Chess.board.getXYofSpace(from);
		int fromX = xy[0];
		int fromY = xy[1];

		//get the defined possible moves for the from piece
		HashMap<Integer,Integer> moves = movingPiece.getMoves();

		return Chess.board.spaceIsInMap(moves, fromX, fromY, to);

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
	
	/**
	 * Returns the arraylist of captured pieces
	 */
	public java.util.ArrayList<Piece> getCapturedPieces() {
		return this.capturedPieces;
	}
	
	/**
	 * Returns the arraylist of active pieces
	 */
	public java.util.ArrayList<Piece> getActivePieces() {
		return this.activePieces;
	}
	
	/**
	 * Checks if the white king is in check
	 * @return true if white's king is in danger
	 */
	public boolean whiteInCheck() {
		int[] curXY;
		//find king
		Piece king = null;
		for(Piece p: activePieces) {
			if(p instanceof KingPiece && p.getPlayer() == Chess.whitePlayer) {
				king = p;
			}
		}
		int[] kingXY = this.getXYofPiece(king);
		Space kingSpace = this.getSpaceAtXY(kingXY[1], kingXY[0]);
		//find if king in danger
		for(Piece cur: activePieces) {
			//check only black pieces
			if(cur.getPlayer() == Chess.blackPlayer) {
				curXY = this.getXYofPiece(cur);
				if(curXY != null) {
					Space curSpace = this.getSpaceAtXY(curXY[1], curXY[0]);
					if(this.isLegalCapture(curSpace, kingSpace) != null) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if the black king is in check
	 * @return true if black's king is in danger
	 */
	public boolean blackInCheck() {
		int[] curXY;
		//find king
		Piece king = null;
		for(Piece p: activePieces) {
			if(p instanceof KingPiece && p.getPlayer() == Chess.blackPlayer) {
				king = p;
			}
		}
		int[] kingXY = this.getXYofPiece(king);
		Space kingSpace = this.getSpaceAtXY(kingXY[1], kingXY[0]);
		//find if king in danger
		for(Piece cur: activePieces) {
			//check only white pieces
			if(cur.getPlayer() == Chess.whitePlayer) {
				curXY = this.getXYofPiece(cur);
				if(curXY != null) {
					Space curSpace = this.getSpaceAtXY(curXY[1], curXY[0]);
					if(this.isLegalCapture(curSpace, kingSpace) != null) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * tests if a move will place the mover in check
	 * DOES NOT test if the move itself is valid
	 * @param from the space the piece is moving from
	 * @param to the space the piece is moving to
	 * @return True if the player of the moving piece will put themselves in danger
	 */
	public boolean movePutsSelfInCheck(Space from, Space to) {
		//move pieces without any regard for safety
		Piece tempIgnore = to.getPiece();
		to.setPiece(from.getPiece());
		from.setPiece(null);
		
		if(to.getPiece().getPlayer() == Chess.whitePlayer) {
			boolean result = whiteInCheck();
			from.setPiece(to.getPiece());
			to.setPiece(tempIgnore);
			return result;
		} else {
			boolean result = blackInCheck();
			from.setPiece(to.getPiece());
			to.setPiece(tempIgnore);
			return result;
		}
	}
	
	/**
	 * Returns the XY values of the passed space
	 * @param space the space to locate
	 * @return the XY values of the space
	 */
	public int[] getXYofPiece(Piece piece) {
		for(int y = 0; y < ROWS; y++) {
			for(int x = 0; x < COLS; x++) {
				if(boardArray[y][x].getPiece() == piece) {
					return new int[] {x,y}; 
				}
			}
		}
		//Should not be reached
		return null;
	}
}


