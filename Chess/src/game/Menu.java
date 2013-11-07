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
public class Menu extends JFrame {
	
	public static Player whitePlayer;
	public static Player blackPlayer;
	public static GameFrame gameFrame;
	
	/**
	 * Initializes as the main menu for the game.
	 */
	public Menu() {
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
				int sideResponse = JOptionPane.showOptionDialog(Menu.this,
						"Choose your side!", "Pick a Side",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, new String[]{"White","Black"},"White");
				/*
				 * If the user picks white, sets white to human and black to
				 * computer, else vice versa
				 * Why nested ternary? Because nested ternarys are cool!
				 */
				blackPlayer = (whitePlayer = (sideResponse == 1) ?
					new HumanPlayer() : new ComputerPlayer())
						instanceof HumanPlayer ? new ComputerPlayer() :
							new HumanPlayer();
								
				Menu.this.setVisible(false);
				gameFrame = new GameFrame();
				gameFrame.pack();
				gameFrame.setVisible(true);
				gameFrame.repaint();
			}
		});
		
		//This button starts the game with two (human) players
		JButton twoPlayers = new JButton("2 Players");
		twoPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Initializes both players as human
				blackPlayer = new HumanPlayer();
				whitePlayer = new HumanPlayer();
				
				Menu.this.setVisible(false);
				gameFrame = new GameFrame();
				gameFrame.pack();
				gameFrame.setVisible(true);
				gameFrame.repaint();
			}
		});
		
		add(onePlayer);
		add(twoPlayers);
		
	}
	
	public static void main(String args[]) {
		Menu menu = new Menu();
		menu.pack();
		menu.setVisible(true);
	}
	
}
