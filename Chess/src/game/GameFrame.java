package game;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * The GUI window for the game.
 * 
 * @author Kevin Hannigan
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	/**
	 * The game board played upon
	 */
	public static Board board;
	
	/**
	 *	Default constructor for the main game window
	 */
	public GameFrame() {
		setTitle("Chess");
		getContentPane().setSize(Board.ROWS*64,Board.COLS*64);
		board = new Board();
		setLayout(board);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//set up menubar
		JMenuBar menuBar = new JMenuBar();
		JMenu view = new JMenu("view");
		menuBar.add(view);
		this.setJMenuBar(menuBar);
		
		setResizable(false);
		//If you want to change it to be resizable, try component listener
		
		//Add the spaces to the frame
		for(int row = 0; row < Board.ROWS; row++) {
			for(int col = 0; col < Board.COLS; col++) {
				add(board.getArray()[row][col]);
			}
		}
		repaint();
	}
	
}
