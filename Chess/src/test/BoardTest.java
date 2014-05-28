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
	
	@Test
	public void testIllegalPawnMove() throws Exception {
	    this.board = new Board();
	    boolean success = board.move(board.getSpaceAtXY(6, 0),board.getSpaceAtXY(3, 0));    
	    assertEquals(success,false);
	    
	}
}
