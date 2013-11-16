package game;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
		
		//set up File tab
		JMenu file = new JMenu("File");
		JMenuItem quit = new JMenuItem("Exit");
		quit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			GameFrame.this.dispatchEvent(
					new WindowEvent(GameFrame.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		file.add(quit);
		
		//set up View tab
		JMenu view = new JMenu("View");
		JMenuItem chatSelect = new JMenuItem("Chat");
		
		chatSelect.addMouseListener(new MouseAdapter() {
			/**
			 * Handles the user input via mouse
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Chat Window would open");
			}
		});
		
		view.add(chatSelect);
		menuBar.add(file);
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
