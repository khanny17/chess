package game;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

//MAKE IT USE VOICE RECOGNITION KNIGHT TO E5

//remember to incorporate castling and the special pawn capture rule

/**
 * The menu where the user can select the number of players, the difficulty,
 *  and other options.
 *  
 * This same menu gets called up again during gameplay but rearranged as an
 *  in-game menu.
 *  
 * @author Kevin Hannigan
 */
@SuppressWarnings("serial")
public class LocalGameMenu extends JFrame {
	
	
	
	/**
	 * Initializes as the main menu for the game.
	 */
	public LocalGameMenu() {
		super();
		setTitle("Main Menu");
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//This button starts the game with one player
		JButton onePlayer = new JButton("1 Player");
		onePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//prompts the user to pick a side
				int sideResponse = JOptionPane.showOptionDialog(LocalGameMenu.this,
						"Choose your side!", "Pick a Side",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, new String[]{"White","Black"},"White");
				/*
				 * If the user picks white, sets white to human and black to
				 * computer, else vice versa
				 * Why nested ternary? Because nested ternarys are cool!
				 */
				Game.blackPlayer = (Game.whitePlayer = (sideResponse == 1) ?
					new HumanPlayer() : new ComputerPlayer())
						instanceof HumanPlayer ? new ComputerPlayer() :
							new HumanPlayer();
								
				LocalGameMenu.this.setVisible(false);
				Game.gameFrame = new GameFrame();
				Game.gameFrame.pack();
				Game.gameFrame.setVisible(true);
				Game.gameFrame.repaint();
			}
		});
		
		//This button starts the game with two (human) players
		JButton twoPlayers = new JButton("2 Players");
		twoPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Initializes both players as human
				Game.blackPlayer = new HumanPlayer();
				Game.whitePlayer = new HumanPlayer();
				
				LocalGameMenu.this.setVisible(false);
				Game.gameFrame = new GameFrame();
				Game.gameFrame.pack();
				Game.gameFrame.setVisible(true);
				Game.gameFrame.repaint();
			}
		});
		
		add(onePlayer);
		add(twoPlayers);
		
	}
	
	
}
