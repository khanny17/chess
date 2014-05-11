package control;

import java.io.IOException;

import model.Board;
import model.Player;

/**
 * The class which controls game flow. Most of the static definitions reside here
 * 
 * @author Nick Monteleone and Kevin Hannigan
 */
public class Chess {
	
	/**
	 * curPlayeris true if white is going, false if black is going
	 */
	public static boolean curPlayer;
	
	/**
	 * The 2 players of the current game
	 */
	public static Player whitePlayer = new Player("White Player");
	public static Player blackPlayer = new Player("Black Player");
	
	public static CheckmateChecker whiteChecker;
	public static CheckmateChecker blackChecker;
	
	/**
	 * The game board played upon
	 */
	public static Board board;
	
	/**
	 * Loads the images of the pieces
	 */
	public static images.Images pieceImages;
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		/** Initialize classes */
		
		//load the piece imagesshow()
		try {
			pieceImages = new images.Images();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		
		startNewTwoPlayerGame();
		
		
	}
	
	/**
	 * Sequence of events to start a new two player game
	 */
	private static void startNewTwoPlayerGame() {		
		board = new Board();
		whiteChecker = new CheckmateChecker(Chess.whitePlayer);
		blackChecker = new CheckmateChecker(Chess.blackPlayer);
		
		
		curPlayer = true;
		SpaceClickListener selector = new SpaceClickListener();
		view.GameFrame gameFrame = new view.GameFrame(selector,board);
		whiteChecker.addObserver(gameFrame);
		blackChecker.addObserver(gameFrame);
		
		view.InfoPanel info = new view.InfoPanel();
		board.addObserver(info);
		whiteChecker.addObserver(info);
		blackChecker.addObserver(info);
		gameFrame.add(info, java.awt.BorderLayout.EAST);
		
		gameFrame.pack();
		gameFrame.setVisible(true);
	}
	
}
