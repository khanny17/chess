package model;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.BoardPanel;
import view.GameFrame;

/**
 * The class which controls game flow
 * 
 * @author Kevin Hannigan
 */
public class Game {
	
	/**
	 * curPlayeris true if white is going, false if black is going
	 */
	public static boolean curPlayer;
	
	/**
	 * The 2 players.
	 */
	public static Player whitePlayer;
	public static Player blackPlayer;
	
	/**
	 * The game board played upon
	 */
	public static Board board;
	
	public static void main(String args[]) {
		
		curPlayer = true;
		GameFrame gameFrame = new GameFrame();
		BoardPanel boardPanel = new BoardPanel();
		gameFrame.add(boardPanel);
		
		gameFrame.pack();
		gameFrame.setVisible(true);
	}
	
	
	
}
