package view;

import java.util.Observable;

/**
 * Displays output during the game
 * @author Nick Monteleone & Kevin Hannigan
 */
public class InfoPanel extends javax.swing.JPanel implements java.util.Observer {

	private static final long serialVersionUID = 1L;

	public InfoPanel() {
		this.setPreferredSize(new java.awt.Dimension(200,0));
	}

	/**
	 * Prints out what happens during the game
	 */
	@Override
	public void update(Observable caller, Object arg1) {
		if(caller instanceof model.Board) {
			System.out.println( ((model.Board)caller).getLastMove().toString() );
		}
	}
	
}
