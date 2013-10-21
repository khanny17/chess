package game;
import javax.swing.JFrame;

/**
 * The GUI window for the game.
 * 
 * @author Kevin Hannigan
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	/**
	 *
	 */
	public GameFrame() {
		setTitle("Chess");
		getContentPane().setSize(8*64,8*64);
		Board board = new Board();
		setLayout(board);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Add the spaces to the frame
		for(int row = 0; row < Board.ROWS; row++) {
			for(int col = 0; col < Board.COLS; col++) {
				add(board.getArray()[row][col]);
			}
		}
		repaint();
	}
	
}
