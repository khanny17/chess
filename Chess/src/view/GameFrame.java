package view;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.Board;

/**
 * The GUI window for the game.
 * 
 * @author Kevin Hannigan
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	
	
	/**
	 *	Default constructor for the main game window
	 */
	public GameFrame() {
		setTitle("Chess");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		this.setJMenuBar(new GameFrameMenuBar());
		
		setResizable(false);
		//If you want to change it to be resizable, try component listener
		
		
		repaint();
	}
	
}
