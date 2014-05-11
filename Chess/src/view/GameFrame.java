package view;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.Chess;
import model.Board;

/**
 * The GUI window for the game.
 * 
 * @author Kevin Hannigan
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame implements java.util.Observer {

	
	
	/**
	 *	Default constructor for the main game window
	 */
	public GameFrame() {
		setTitle("Chess");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		setResizable(false);
		//If you want to change it to be resizable, try component listener
		
		
		repaint();
	}

	/**
	 * Gets called by the checkmate checker to disable the window
	 */
	@Override
	public void update(Observable arg0, Object winner) {
		if(winner.equals(Chess.whitePlayer)) {
				System.out.println("white wins!");
		} else {
			System.out.println("black wins!");
		}
		
	}
	
	
	
}
