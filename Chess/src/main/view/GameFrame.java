package view;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.Board;
import control.CheckmateChecker;
import control.SpaceClickListener;


/**
 * The Singleton GUI window for the game.
 * 
 * @author Kevin Hannigan
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame implements java.util.Observer {

	/**
	 * The main window of the game
	 */
	private static GameFrame gfMain;
	
	
	
	/**
	 * The panel which contains the gameboard
	 */
	public BoardPanel boardPanel;

	private view.InfoPanel infoPanel;

	/**
	 * The game board played upon
	 */
	private Board board;

	/**
	 * The listener for player control
	 */
	public SpaceClickListener selector;

	/**
	 * The two threads for checking if a player is in checkmate
	 */
	public CheckmateChecker whiteChecker;
	public CheckmateChecker blackChecker;

	public static GameFrame getInstance() {
		return gfMain;
	}

	private GameFrame() {
		setTitle("Chess");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		this.setJMenuBar(makeMenuBar());
		setResizable(true);
	}

	
	/**
	 * Factory method to create the menu for the game frame
	 * @return The created menu bar
	 */
	private JMenuBar makeMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		//set up File tab
		JMenu file = new JMenu("File");
		
		//Button to start a new game
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				GameFrame.gfMain.startGame();
			}
		});

		file.add(newGame);
		
		//the exit button
		JMenuItem exit = new JMenuItem("Exit");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				GameFrame.this.dispatchEvent(new WindowEvent(GameFrame.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		file.add(exit);
		
		//set up View tab
		JMenu view = new JMenu("View");
		JMenuItem chatSelect = new JMenuItem("Chat");

		chatSelect.addMouseListener(new MouseAdapter() {
			private boolean removing = true;
			
			/**
			 * Handles the user input via mouse
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				if(removing) {
					GameFrame.this.remove(GameFrame.this.infoPanel);
				} else {
					GameFrame.this.add(GameFrame.this.infoPanel, java.awt.BorderLayout.EAST);
				}
				removing = !removing;
				GameFrame.this.repaint();
				GameFrame.this.pack();
			}
		});

		view.add(chatSelect);
		menuBar.add(file);
		menuBar.add(view);
		
		return menuBar;
	}

	/**
	 * Gets called by the checkmate checker to disable the window
	 */
	@Override
	public void update(Observable caller, Object winner) {
		if(caller instanceof control.CheckmateChecker) {
			//remove the space click listener
			for(Component c: boardPanel.getComponents()) {
				if(c instanceof JPanel) {
					((JPanel)c).removeMouseListener(GameFrame.getInstance().selector);
				}
			}
		}
	}
	
	
	public static void main(String args[]) {
		/** Initialize classes */
		gfMain = new GameFrame();
		gfMain.pack();
		gfMain.setVisible(true);
		gfMain.startGame();
	}
	
	/**
	 * Starts a new two player game on the calling board
	 */
	private void startGame() {
		this.clearFrame();
		
		this.board = new Board();
		this.selector = new SpaceClickListener();

		this.getBoard().curPlayer = true;
		//If you want to change it to be resizable, try component listener
		this.boardPanel = new view.BoardPanel(this.selector, this.getBoard());
		this.add(boardPanel, java.awt.BorderLayout.CENTER);
		
		this.whiteChecker = new CheckmateChecker(this.getBoard().whitePlayer);
		this.blackChecker = new CheckmateChecker(this.getBoard().blackPlayer);
		
		this.whiteChecker.addObserver(this);
		this.blackChecker.addObserver(this);
		
		this.infoPanel = new view.InfoPanel();
		this.getBoard().addObserver(this.infoPanel);
		this.whiteChecker.addObserver(this.infoPanel);
		this.blackChecker.addObserver(this.infoPanel);
		this.add(this.infoPanel, java.awt.BorderLayout.EAST);
		
		this.repaint();
		this.pack();
		this.setVisible(true);
		this.setResizable(false);
	}
	
	private void clearFrame() {
		this.getContentPane().removeAll();
	}

	/* Getters 'n' Setters */
	public Board getBoard() { return board;	}
}
