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
	 * Moves a white pawn three spaces forward
	 */
	@Test
	public void testIllegalPawnMove() throws Exception {
	    this.board = new Board();
	    boolean success = board.move(board.getSpaceAtXY(6, 0),board.getSpaceAtXY(3, 0));    
	    assertEquals(success,false);
	}
	
	/**
	 * Moves a white pawn two spaces forward on the first move
	 */
	@Test
	public void testLegalPawnMove() throws Exception {
	    this.board = new Board();
	    boolean success = board.move(board.getSpaceAtXY(6, 0),board.getSpaceAtXY(4, 0));    
	    assertEquals(success,true);
	}
	
	/**
	 * Moves a white pawn two spaces forward on the second move
	 */
	@Test
	public void testPawnMovesUpdated() throws Exception {
		this.board = new Board();
	    board.move(board.getSpaceAtXY(6, 0),board.getSpaceAtXY(4, 0));    
	    assertEquals(board.move(board.getSpaceAtXY(4, 0),board.getSpaceAtXY(2, 0)),false);
	}
	
	@Test
	public void testIllegalPawnCapture() throws Exception {
		this.board = new Board();
		board.move(board.getSpaceAtXY(6, 0),board.getSpaceAtXY(4, 0));
		board.move(board.getSpaceAtXY(4, 0),board.getSpaceAtXY(3, 0));
		board.move(board.getSpaceAtXY(3, 0),board.getSpaceAtXY(2, 0));
		//try to capture forward, like a move
		assertEquals(board.move(board.getSpaceAtXY(2, 0),board.getSpaceAtXY(1, 0)),false);
	}
	
	@Test
	public void testLegalPawnCapture() throws Exception {
		this.board = new Board();
		board.move(board.getSpaceAtXY(6, 0),board.getSpaceAtXY(4, 0));
		board.move(board.getSpaceAtXY(4, 0),board.getSpaceAtXY(3, 0));
		board.move(board.getSpaceAtXY(3, 0),board.getSpaceAtXY(2, 0));
		
		assertEquals(board.move(board.getSpaceAtXY(2, 0),board.getSpaceAtXY(1, 1)),true);
	}
}
