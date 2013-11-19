package model;

import view.GameFrame;
import view.LocalGameMenu;

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
	public static Board board = new Board();

	public static void main(String args[]) {
		
		LocalGameMenu localMenu = new LocalGameMenu();
		localMenu.pack();
		localMenu.setVisible(true);
		curPlayer = true;
	}
	
}
