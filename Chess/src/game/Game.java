package game;
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
	public static Player whitePlayer;
	public static Player blackPlayer;
	public static GameFrame gameFrame;

	public static void main(String args[]) {
		
		LocalGameMenu localMenu = new LocalGameMenu();
		localMenu.pack();
		localMenu.setVisible(true);
		curPlayer = true;
	}
	
}
