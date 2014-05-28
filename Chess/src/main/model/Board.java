package model;

import pieces.*;
import view.GameFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;

/**
 * This class defines the chess board on which the game is played.
 * It keeps track of the spaces with a two dimensional array.
 * 
 * @author Kevin Hannigan
 */
public class Board extends java.util.Observable{

	private ArrayList<Move> moves = new ArrayList<Move>();
	
	/**
	 * A list of each piece that has been captured so far
	 */
	private ArrayList<Piece> capturedPieces;

	/**
	 * A list of each piece on the board
	 */
	private ArrayList<Piece> activePieces;
	
	/**
	 * The array used to represent the board. The board is defined such that
	 *  (0,0) = H1, the top left hand corner for the player playing as white
	 *  
	 *  The board is defined (row,col) or (y,x)
	 */
	private Space[][] boardArray;

	/**
	 * curPlayeris true if white is going, false if black is going
	 */
	public boolean curPlayer;

	/**
	 * The 2 players of the current game
	 */
	public Player whitePlayer = new Player("White Player");

	public Player blackPlayer = new Player("Black Player");

	public static final int ROWS = 8;
	public static final int COLS = 8;

	/**
	 * Constructs the default game board set up for the start of the game
	 */
	public Board() {
		capturedPieces = new ArrayList<Piece>();
		activePieces = new ArrayList<Piece>();
		
		//Place the pieces
		boardArray = new Space[ROWS][COLS];

		//set up black's players
		boardArray[0][0] = new Space(new RookPiece(this.blackPlayer), "A8");
		boardArray[0][1] = new Space(new KnightPiece(this.blackPlayer), "B8");
		boardArray[0][2] = new Space(new BishopPiece(this.blackPlayer), "C8");
		boardArray[0][3] = new Space(new QueenPiece(this.blackPlayer), "D8");
		boardArray[0][4] = new Space(new KingPiece(this.blackPlayer), "E8");
		boardArray[0][5] = new Space(new BishopPiece(this.blackPlayer), "F8");
		boardArray[0][6] = new Space(new KnightPiece(this.blackPlayer), "G8");
		boardArray[0][7] = new Space(new RookPiece(this.blackPlayer), "H8");
		for(int col = 0; col < COLS; col++) {
			String c = ((char)(65+col)) + "7";
			boardArray[1][col] = new Space(new PawnPiece(this.blackPlayer), c);
		}

		//set up white's players
		boardArray[7][0] = new Space(new RookPiece(this.whitePlayer), "A1");
		boardArray[7][1] = new Space(new KnightPiece(this.whitePlayer), "B1");
		boardArray[7][2] = new Space(new BishopPiece(this.whitePlayer), "C1");
		boardArray[7][3] = new Space(new QueenPiece(this.whitePlayer), "D1");
		boardArray[7][4] = new Space(new KingPiece(this.whitePlayer), "E1");
		boardArray[7][5] = new Space(new BishopPiece(this.whitePlayer), "F1");
		boardArray[7][6] = new Space(new KnightPiece(this.whitePlayer), "G1");
		boardArray[7][7] = new Space(new RookPiece(this.whitePlayer), "H1");
		for(int col = 0; col < COLS; col++) {
			String c = ((char)(65+col)) + "2";
			boardArray[6][col] = new Space(new PawnPiece(this.whitePlayer), c);
		}

		//set up empty spaces
		for(int row = 2; row < ROWS-2; row++) {
			for(int col = 0; col < COLS; col++) {
				String c = ((char)(65+col)) + "" + (row);
				boardArray[row][col] = new Space(c);
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

	/**
	 * returns the latest move
	 * @return the move object at the end of the arraylist
	 */
	public Move getLastMove() {
		return this.moves.get(moves.size()-1);
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
					curPlayer = !curPlayer;
					this.moves.add(new Move(fromPiece,null,from,to));
					this.setChanged();
					this.notifyObservers();
					this.clearChanged();
					return true;
				} else {
					return false;
				}
			} else {
				return castle(from,to);
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
					//move "from" piece to "to" space
					from.setPiece(null);
					to.setPiece(fromPiece);
					fromPiece.moved();
					//flip player's turns
					curPlayer = !curPlayer;
					this.moves.add(new Move(fromPiece,captured,from,to));
					this.setChanged();
					this.notifyObservers();
					this.clearChanged();
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
	 * SPECIAL CASE
	 * Checks if the move is a castle and performs the move if so
	 * @param from the space moving
	 * @param to the space moving to
	 * @return
	 */
	private boolean castle(Space from, Space to) {
		boolean success = false;
		Space other = null;
		Piece otherPiece = null;
		Space rookdest = null;
		//if piece is a king and hasnt moved
		if(from.getPiece() instanceof KingPiece && !(from.getPiece().didMove())) {
			//test if puts self in check
			if(!this.movePutsSelfInCheck(from, to)) {
				if(from.getPiece().getPlayer() == this.whitePlayer) {
					//test which space was clicked
					if(to == getSpaceAtXY(7,2)) {
						//the left side
						other = getSpaceAtXY(7,0);
						otherPiece = other.getPiece();
						if(!otherPiece.didMove()) {
							//if other didnt move, will be rook anywyas dont bother checking
							rookdest = getSpaceAtXY(7,3);
							if(getSpaceAtXY(7,1).isEmpty() && to.isEmpty() && rookdest.isEmpty()) {
								//everythings in order, do it!
								success = true;								
							}
						}
					} else if(to == getSpaceAtXY(7,6)) {
						//the right side
						other = getSpaceAtXY(7,7);
						otherPiece = other.getPiece();
						if(!otherPiece.didMove()) {
							//if other didnt move, will be rook anywyas dont bother checking
							rookdest = getSpaceAtXY(7,5);
							if(to.isEmpty() && rookdest.isEmpty()) {
								//everythings in order, do it!
								success = true;								
							}
						}
					}
				} else {
					//test which space was clicked
					if(to == getSpaceAtXY(0,2)) {
						//the left side
						other = getSpaceAtXY(0,0);
						otherPiece = other.getPiece();
						if(!otherPiece.didMove()) {
							//if other didnt move, will be rook anywyas dont bother checking
							rookdest = getSpaceAtXY(0,3);
							if(getSpaceAtXY(0,1).isEmpty() && to.isEmpty() && rookdest.isEmpty()) {
								//everythings in order, do it!
								success = true;								
							}
						}
					} else if(to == getSpaceAtXY(0,6)) {
						//the right side
						other = getSpaceAtXY(0,7);
						otherPiece = other.getPiece();
						if(!otherPiece.didMove()) {
							//if other didnt move, will be rook anywyas dont bother checking
							rookdest = getSpaceAtXY(0,5);
							if(to.isEmpty() && rookdest.isEmpty()) {
								//everythings in order, do it!
								success = true;								
							}
						}
					}
				}
			}
		}
		
		if(success) {
			//move "from" piece to "to" space
			to.setPiece(from.getPiece());
			to.getPiece().moved();
			from.setPiece(null);
			//move the rook
			other.setPiece(null);
			rookdest.setPiece(otherPiece);
			otherPiece.moved();
			//flip player's turns
			curPlayer = !curPlayer;
			this.moves.add(new Move(to.getPiece(),null,from,to));
			this.setChanged();
			this.notifyObservers();
			this.clearChanged();
			return true;
		} else {
			return false;
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
			int[] xy = GameFrame.getInstance().getBoard().getXYofSpace(from);
			int fromX = xy[0];
			int fromY = xy[1];

			//get the defined possible moves for the from piece
			HashMap<Integer,Integer> moves = from.getPiece().getCaptures();

			if(GameFrame.getInstance().getBoard().spaceIsInMap(moves, fromX, fromY, to)) {
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

		int[] xy = GameFrame.getInstance().getBoard().getXYofSpace(from);
		int fromX = xy[0];
		int fromY = xy[1];

		//get the defined possible moves for the from piece
		HashMap<Integer,Integer> moves = movingPiece.getMoves();

		return GameFrame.getInstance().getBoard().spaceIsInMap(moves, fromX, fromY, to);

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
	public ArrayList<Piece> getCapturedPieces() {
		return this.capturedPieces;
	}
	
	/**
	 * Returns the arraylist of active pieces
	 */
	public ArrayList<Piece> getActivePieces() {
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
			if(p instanceof KingPiece && p.getPlayer() == this.whitePlayer) {
				king = p;
			}
		}
		int[] kingXY = this.getXYofPiece(king);
		Space kingSpace = this.getSpaceAtXY(kingXY[1], kingXY[0]);
		//find if king in danger
		for(Piece cur: activePieces) {
			//check only black pieces
			if(cur.getPlayer() == this.blackPlayer) {
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
			if(p instanceof KingPiece && p.getPlayer() == this.blackPlayer) {
				king = p;
			}
		}
		int[] kingXY = this.getXYofPiece(king);
		Space kingSpace = this.getSpaceAtXY(kingXY[1], kingXY[0]);
		//find if king in danger
		for(Piece cur: activePieces) {
			//check only white pieces
			if(cur.getPlayer() == this.whitePlayer) {
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
		
		if(to.getPiece().getPlayer() == this.whitePlayer) {
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


