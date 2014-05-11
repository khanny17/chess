package control;

import model.Board;
import model.Player;

/**
 * The class which controls game flow
 * 
 * @author Nick Monteleone and Kevin Hannigan
 */
public class Chess {
	
	/**
	 * curPlayeris true if white is going, false if black is going
	 */
	public static boolean curPlayer;
	
	/**
	 * The 2 players.
	 */
	public static Player whitePlayer = Player.HumanPlayer;
	public static Player blackPlayer = Player.ComputerPlayer;
	
	/**
	 * The game board played upon
	 */
	public static Board board;
	
	public static view.InfoUpdater infoUpdater;
	
	/**
	 * Usage: java 
	 * @param args
	 */
	public static void main(String args[]) {
		board = new Board();
		
		curPlayer = true;
		view.GameFrame gameFrame = new view.GameFrame();
		view.BoardPanel boardPanel = new view.BoardPanel();
		gameFrame.add(boardPanel);
		
		gameFrame.pack();
		gameFrame.setVisible(true);
		
		infoUpdater = new view.InfoUpdater(board);
	}
	
	
	
}
