package view;

import java.util.Observable;

import javax.swing.JFrame;

import control.Chess;


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
		setLayout(new java.awt.BorderLayout());
		
		setResizable(false);
		//If you want to change it to be resizable, try component listener
		
		
		repaint();
	}

	/**
	 * Gets called by the checkmate checker to disable the window
	 */
	@Override
	public void update(Observable arg0, Object winner) {
		
		
	}
	
	
	
}
