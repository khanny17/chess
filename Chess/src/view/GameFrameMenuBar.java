/**
 * 
 */
package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import control.Chess;
import model.Board;
import model.Player;


/**
 * @author Kevin
 *
 */
public class GameFrameMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;

	public GameFrameMenuBar() {
		//set up File tab
		JMenu file = new JMenu("File");
		JMenuItem newGame = new JMenuItem("Exit");
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
			}
		});

		file.add(newGame);

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
		this.add(file);
		this.add(view);
	}

}
