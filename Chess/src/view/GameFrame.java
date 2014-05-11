package view;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Board;
import model.Player;
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
		this.setJMenuBar(menuBar());
		setResizable(false);
		//If you want to change it to be resizable, try component listener
		boardPanel = new view.BoardPanel(selector, board);
		this.add(boardPanel, java.awt.BorderLayout.CENTER);
		
		repaint();
	}
	
	/**
	 * Factory method to create the menu for the game frame
	 * @return The created menu bar
	 */
	private JMenuBar menuBar() {
		JMenuBar menuBar = new JMenuBar();
		//set up File tab
		JMenu file = new JMenu("File");
		
		//the exit button
		JMenuItem exit = new JMenuItem("Exit");
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				GameFrame.this.dispatchEvent(new WindowEvent(GameFrame.this, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		file.add(exit);
		
		/**
		 * A button for new game, does not work
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int numPlayerResponse = JOptionPane.showOptionDialog(null,
		                      "Choose your side!", "Pick a Side",
		                      JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                      null, new String[]{"White","Black"},"White");
				if(numPlayerResponse != 56) {
					Chess.blackPlayer = new Player("Black Player");
					Chess.whitePlayer = new Player("White Player");
				}
				Chess.board = new Board();
				GameFrame.this.boardPanel = new BoardPanel()
			}
		});

		file.add(newGame);
		*/

		//set up View tab
		JMenu view = new JMenu("View");
		JMenuItem chatSelect = new JMenuItem("Chat");

		chatSelect.addMouseListener(new MouseAdapter() {
			/**
			 * Handles the user input via mouse
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Chat Window would open");
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
					((JPanel)c).removeMouseListener(Chess.selector);
				}
			}
		}
	}
	
}
