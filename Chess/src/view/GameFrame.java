package view;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
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

	
	public BoardPanel boardPanel;
	
	/**
	 *	Default constructor for the main game window
	 */
	public GameFrame(control.SpaceClickListener selector, model.Board board) {
		setTitle("Chess");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		
		setResizable(false);
		//If you want to change it to be resizable, try component listener
		boardPanel = new view.BoardPanel(selector, board);
		this.add(boardPanel, java.awt.BorderLayout.CENTER);
		
		repaint();
	}

	/**
	 * Gets called by the checkmate checker to disable the window
	 */
	@Override
	public void update(Observable caller, Object winner) {
		if(caller instanceof control.CheckmateChecker) {
			//disable the components somehow
		}
	}
	
	
	
}
