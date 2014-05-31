package model;
import org.junit.After;
import org.junit.Test;

import junit.framework.TestCase;
import model.Board;


public class BoardTest extends TestCase {
	
	private Board board;

	@After
	public void tearDown() {
		board = null;
	}
	
	/**
	 * Tries to move a white and a black pawn three spaces forward
	 */
	@Test
	public void testIllegalPawnMove() throws Exception {
	    this.board = new Board();
	    //white
	    assertFalse(board.move(board.getSpaceAtXY(6, 0),board.getSpaceAtXY(3, 0)));
	    //black
	    assertFalse(board.move(board.getSpaceAtXY(1, 0),board.getSpaceAtXY(4, 0)));
	    
	}
	
	/**
	 * Moves a white pawn two spaces forward on the first move
	 */
	@Test
	public void testLegalPawnMove() throws Exception {
	    this.board = new Board(); 
	    assertTrue(board.move(board.getSpaceAtXY(6, 0),board.getSpaceAtXY(4, 0)));
	}
	
	/**
	 * Moves a white pawn two spaces forward on the second move
	 */
	@Test
	public void testPawnMovesUpdated() throws Exception {
		this.board = new Board();
	    board.move(board.getSpaceAtXY(6, 0),board.getSpaceAtXY(4, 0));    
	    assertFalse(board.move(board.getSpaceAtXY(4, 0),board.getSpaceAtXY(2, 0)));
	}
	
	@Test
	public void testIllegalPawnCapture() throws Exception {
		this.board = new Board();
		board.move(board.getSpaceAtXY(6, 0),board.getSpaceAtXY(4, 0));
		board.move(board.getSpaceAtXY(4, 0),board.getSpaceAtXY(3, 0));
		board.move(board.getSpaceAtXY(3, 0),board.getSpaceAtXY(2, 0));
		//try to capture forward, like a move
		assertFalse(board.move(board.getSpaceAtXY(2, 0),board.getSpaceAtXY(1, 0)));
	}
	
	@Test
	public void testLegalPawnCapture() throws Exception {
		this.board = new Board();
		board.move(board.getSpaceAtXY(6, 0),board.getSpaceAtXY(4, 0));
		board.move(board.getSpaceAtXY(4, 0),board.getSpaceAtXY(3, 0));
		board.move(board.getSpaceAtXY(3, 0),board.getSpaceAtXY(2, 0));
		
		assertTrue(board.move(board.getSpaceAtXY(2, 0),board.getSpaceAtXY(1, 1)));
	}
	
	/**
	 * Tests that a legal castling move works
	 * @throws Exception
	 */
	@Test
	public void testLegalLeftCastling() throws Exception {
		boolean success = false;
		this.board = new Board();
		
		/** White Test */
		
		//move knight
		board.move(board.getSpaceAtXY(7, 1),board.getSpaceAtXY(5, 0));
		//move pawn
		board.move(board.getSpaceAtXY(6, 3),board.getSpaceAtXY(5, 3));
		//move bishop
		board.move(board.getSpaceAtXY(7, 2),board.getSpaceAtXY(5, 4));
		//move queen
		board.move(board.getSpaceAtXY(7, 3),board.getSpaceAtXY(6, 3));
		
		//try to castle
		if(board.move(board.getSpaceAtXY(7, 4),board.getSpaceAtXY(7, 2))) {
			//test that pieces are in the right places
			if(board.getSpaceAtXY(7, 2).getPiece() instanceof pieces.KingPiece) {
				if(board.getSpaceAtXY(7, 3).getPiece() instanceof pieces.RookPiece) {
					success = true;
				}
			}
		}	
		assertTrue(success);
		
		/** Black Test */
		success = false;
		//move knight
		board.move(board.getSpaceAtXY(0, 1),board.getSpaceAtXY(2, 0));
		//move pawn
		board.move(board.getSpaceAtXY(1, 3),board.getSpaceAtXY(2, 3));
		//move bishop
		board.move(board.getSpaceAtXY(0, 2),board.getSpaceAtXY(2, 4));
		//move queen
		board.move(board.getSpaceAtXY(0, 3),board.getSpaceAtXY(1, 3));
		
		//try to castle
		if(board.move(board.getSpaceAtXY(0, 4),board.getSpaceAtXY(0, 2))) {
			//test that pieces are in the right places
			if(board.getSpaceAtXY(0, 2).getPiece() instanceof pieces.KingPiece) {
				if(board.getSpaceAtXY(0, 3).getPiece() instanceof pieces.RookPiece) {
					success = true;
				}
			}
		}	
		assertTrue(success);
	}
	
	/**
	 * Tests that a legal castling move works
	 * @throws Exception
	 */
	@Test
	public void testLegalRightCastling() throws Exception {
		boolean success = false;
		this.board = new Board();
		
		/** White Test */

		//move knight
		board.move(board.getSpaceAtXY(7, 6),board.getSpaceAtXY(5, 7));
		//move pawn
		board.move(board.getSpaceAtXY(6, 4),board.getSpaceAtXY(5, 4));
		//move bishop
		board.move(board.getSpaceAtXY(7, 5),board.getSpaceAtXY(5, 3));
		
		//try to castle
		if(board.move(board.getSpaceAtXY(7, 4),board.getSpaceAtXY(7, 6))) {
			//test that pieces are in the right places
			if(board.getSpaceAtXY(7, 6).getPiece() instanceof pieces.KingPiece) {
				if(board.getSpaceAtXY(7, 5).getPiece() instanceof pieces.RookPiece) {
					success = true;
				}
			}
		}
		assertTrue(success);
		
		
		/** Black Test */
		
		success = false;
		
		//move knight
		board.move(board.getSpaceAtXY(0, 6),board.getSpaceAtXY(2, 7));
		//move pawn
		board.move(board.getSpaceAtXY(1, 4),board.getSpaceAtXY(2, 4));
		//move bishop
		board.move(board.getSpaceAtXY(0, 5),board.getSpaceAtXY(2, 3));
		
		//try to castle
		if(board.move(board.getSpaceAtXY(0, 4),board.getSpaceAtXY(0, 6))) {
			//test that pieces are in the right places
			if(board.getSpaceAtXY(0, 6).getPiece() instanceof pieces.KingPiece) {
				if(board.getSpaceAtXY(0, 5).getPiece() instanceof pieces.RookPiece) {
					success = true;
				}
			}
		}
		
		assertTrue(success);
	}
	
	/**
	 * Tests that castling fails if a piece is in the way
	 * @throws Exception
	 */
	@Test
	public void testLeftCastlingBlocked() throws Exception {
		this.board = new Board();
		
		/** White Test*/
		
		//move knight
		board.move(board.getSpaceAtXY(7, 1),board.getSpaceAtXY(5, 0));
		//move pawn
		board.move(board.getSpaceAtXY(6, 3),board.getSpaceAtXY(5, 3));
		//move bishop
		board.move(board.getSpaceAtXY(7, 2),board.getSpaceAtXY(5, 4));
		//leave queen in the way!
		
		//try to castle
		assertFalse(board.move(board.getSpaceAtXY(7, 4),board.getSpaceAtXY(7, 2)));
		
		/** Black Test*/
		
		//move knight
		board.move(board.getSpaceAtXY(0, 1),board.getSpaceAtXY(2, 0));
		//move pawn
		board.move(board.getSpaceAtXY(1, 3),board.getSpaceAtXY(2, 3));
		//move bishop
		board.move(board.getSpaceAtXY(0, 2),board.getSpaceAtXY(2, 4));
		//leave queen in the way!
			
		//try to castle
		assertFalse(board.move(board.getSpaceAtXY(0, 4),board.getSpaceAtXY(0, 2)));	
	}
	
	/**
	 * Tests that castling fails if a piece is in the way
	 * @throws Exception
	 */
	@Test
	public void testRightCastlingBlocked() throws Exception {
		this.board = new Board();
		
		/** White Test */
		
		//move knight
		board.move(board.getSpaceAtXY(7, 6),board.getSpaceAtXY(5, 7));
		//move pawn
		board.move(board.getSpaceAtXY(6, 4),board.getSpaceAtXY(5, 4));
		//leave bishop
		
		//try to castle
		assertFalse(board.move(board.getSpaceAtXY(7, 4),board.getSpaceAtXY(7, 6)));		
		
		
		/** Black Test */
		
		//move knight
		board.move(board.getSpaceAtXY(0, 6),board.getSpaceAtXY(2, 7));
		//move pawn
		board.move(board.getSpaceAtXY(1, 4),board.getSpaceAtXY(2, 4));
		//leave bishop
		
		//try to castle
		assertFalse(board.move(board.getSpaceAtXY(0, 4),board.getSpaceAtXY(0, 6)));	
	}
}
