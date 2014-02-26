package model;

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
	
	public static viewcontrol.InfoUpdater infoUpdater;
	
	/**
	 * Usage: java 
	 * @param args
	 */
	public static void main(String args[]) {
		board = new Board();
		
		curPlayer = true;
		viewcontrol.GameFrame gameFrame = new viewcontrol.GameFrame();
		viewcontrol.BoardPanel boardPanel = new viewcontrol.BoardPanel();
		gameFrame.add(boardPanel);
		
		gameFrame.pack();
		gameFrame.setVisible(true);
		
		infoUpdater = new viewcontrol.InfoUpdater(board);
	}
	
	
	
}
